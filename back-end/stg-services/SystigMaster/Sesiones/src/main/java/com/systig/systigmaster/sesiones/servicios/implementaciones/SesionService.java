package com.systig.systigmaster.sesiones.servicios.implementaciones;

import com.google.gson.Gson;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.systig.base.sesiones.repositorio.modelo.entidades.Propietario;
import com.systig.base.sesiones.repositorio.modelo.entidades.Rol;
import com.systig.base.sesiones.repositorio.modelo.entidades.Usuario;
import com.systig.base.sesiones.repositorio.modelo.objetos.GeoIP;
import com.systig.base.sesiones.repositorio.modelo.objetos.ResultadoTransaccion;
import com.systig.base.sesiones.repositorio.oad.IConfiguracionDao;
import com.systig.base.sesiones.repositorio.oad.IPropietarioDao;
import com.systig.base.sesiones.repositorio.oad.IRole;
import com.systig.base.sesiones.repositorio.oad.IUsuarioDao;
import com.systig.systigmaster.sesiones.servicios.interfaces.ISesionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
public class SesionService implements ISesionService {
    private final IUsuarioDao iUsuarioDao;
    private final GeoIPLocationService locationService;
    private final IRole iRole;
    private final IPropietarioDao iPropietarioDao;
    private final IConfiguracionDao iConfiguracionDao;

    private final JavaMailSender sender;


    public SesionService(IUsuarioDao iUsuarioDao, GeoIPLocationService locationService, IRole iRole, IPropietarioDao iPropietarioDao, IConfiguracionDao iConfiguracionDao, JavaMailSender sender) {
        this.iUsuarioDao = iUsuarioDao;
        this.locationService = locationService;
        this.iRole = iRole;
        this.iPropietarioDao = iPropietarioDao;
        this.iConfiguracionDao = iConfiguracionDao;
        this.sender = sender;
    }

    @Override
    public ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        Usuario usuario = iUsuarioDao.getByUsernameEquals(principal.getName());

        String tokenEnc = iUsuarioDao.retornoToken(usuario);
        Usuario tokenDesc = iUsuarioDao.retornoUsuario(tokenEnc);
        System.out.println("Token --> " + tokenEnc);
        System.out.println("Usuario --> " + (new Gson()).toJson(tokenDesc));

        resultadoTransaccion.setToken(tokenEnc);
        resultadoTransaccion.setResultado("TOKEN DE ACCESO VALIDO");
        return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addUserSystig(HttpServletRequest request, Propietario propietario) {
        try{
            Propietario finalPropietario = propietario;
            Optional<Usuario> opUsuario = iUsuarioDao.findAll().stream().filter(usuario -> usuario.getUsername().equals(finalPropietario.getEmail())).findFirst();

            if (opUsuario.isPresent()){
                return new ResponseEntity<>("El Usuario ya se encuentra Registrado", HttpStatus.CONFLICT);
            }

            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            String unClave =iUsuarioDao.getClaveAleatoria();

            try {
                GeoIP geoIP = this.locationService.getLocation(request.getRemoteAddr());
                propietario.setPais(geoIP.getCountry());
                propietario.setProvincia(geoIP.getCity());
            }catch (AddressNotFoundException e){
                propietario.setPais("NP");
                propietario.setProvincia("NP");
            }

            Usuario usuario = new Usuario();
            usuario.setUsername(propietario.getEmail());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuario.setPassword(passwordEncoder.encode(unClave));
            usuario.setEnabled(true);
            usuario.setPropietario(propietario);
            usuario.setIpRemota(request.getRemoteAddr());
            usuario.setFecha((new Date()).getTime());

            Optional<Rol> rol = iRole.findAll().stream()
                    .filter(rol1 -> rol1.getRole().equals("CLIENTE"))
                    .findFirst();
            if (rol.isPresent()){
                usuario.setRol(rol.get());
            }else{
                Rol nuevoRol = new Rol();
                nuevoRol.setDescripcion("CLIENTE");
                nuevoRol.setRole("CLIENTE");
                usuario.setRol(this.iRole.save(nuevoRol));
            }

            propietario.getUsuarios().add(usuario);
            propietario = iPropietarioDao.save(propietario);
            //propietario.setConfiguracion((new ConfiguracionDefaultServ(iConfiguracionDao)).getConfiguracion(propietario.getIdPropietario()));

            propietario = iPropietarioDao.save(propietario);
            try {
                enviaCorreo("Nuevo Usuario",
                        IUsuarioDao.FORMATOS_CORREO.EMAIL_USARIO_CREADO.getStrFormato()
                                .replace("{usuario}", propietario.getEmail())
                                .replace("{clave}", unClave),
                        propietario.getEmail());
                resultadoTransaccion.setResultado(propietario);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }catch (Exception emailEx){
                System.out.println("No se pudo enviar el Email");
                System.out.println("Usuario: " + propietario.getEmail() + "\nClave: " + unClave );
                resultadoTransaccion.setResultado("No Se pudo crear el Usuario en el Sistema");
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> restoreUserSystig(String email) {
        Usuario usuario = iUsuarioDao.getByUsernameEquals(email);
        String unClave =iUsuarioDao.getClaveAleatoria();

        if(usuario!=null){
            try {
                enviaCorreo("Reemplazo de clave",
                        IUsuarioDao.FORMATOS_CORREO.EMAIL_USARIO_RESTAURADO.getStrFormato()
                                .replace("{usuario}",usuario.getUsername())
                                .replace("{clave}",unClave),
                        usuario.getUsername());
            } catch (Exception e) {
                e.printStackTrace();
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuario.setPassword(passwordEncoder.encode(unClave));
            iUsuarioDao.save(usuario);
            return new ResponseEntity<String>("Existe", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Fallido", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public void enviaCorreo(String titulo, String contenido, String destinatario) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(destinatario);
        helper.setText(contenido,true);
        helper.setSubject(titulo);

        sender.send(message);
    }


}
