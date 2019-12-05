package com.systig.systigmaster.sesiones.servicios.servicios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.systig.systigmaster.sesiones.repositorios.interfaces.IPropietarioDao;
import com.systig.systigmaster.sesiones.repositorios.interfaces.IRole;
import com.systig.systigmaster.sesiones.repositorios.interfaces.IUsuarioDao;
import com.systig.systigmaster.sesiones.repositorios.modelos.*;
import com.systig.systigmaster.sesiones.servicios.interfaces.ISesionServ;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Base64;
import java.util.Date;

@Service
public class SesionServ implements ISesionServ {
    private final IUsuarioDao iUsuarioDao;
    private final GeoIPLocationServ locationService;
    private final IRole iRole;
    private final IPropietarioDao iPropietarioDao;
    private final JavaMailSender sender;


    public SesionServ(IUsuarioDao iUsuarioDao, GeoIPLocationServ locationService, IRole iRole, IPropietarioDao iPropietarioDao, JavaMailSender sender) {
        this.iUsuarioDao = iUsuarioDao;
        this.locationService = locationService;
        this.iRole = iRole;
        this.iPropietarioDao = iPropietarioDao;
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
    public ResponseEntity<?> addUserSystig(HttpServletRequest request, HttpHeaders headers, HttpSession session, Propietario propietario) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Rol rol = null;
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

            try{
                rol = iRole.findAll().stream()
                        .filter(rol1 -> rol1.getRole().equals("CLIENTE"))
                        .findFirst()
                        .get();
            }catch (Exception e){
                e.printStackTrace();
            }

            usuario.setRol(rol);
            propietario.getUsuarios().add(usuario);
            propietario = iPropietarioDao.save(propietario);

            Configuracion configuracion = new Configuracion();
            configuracion.setIdPropietario(propietario.getIdPropietario());
            configuracion.setJsonConfiguracion((new Gson()).toJson(getConfiguracionDefault()));
            propietario.setConfiguracion(configuracion);
            iPropietarioDao.save(propietario);
            enviaCorreo("Nuevo Usuario",
                    IUsuarioDao.FORMATOS_CORREO.EMAIL_USARIO_CREADO.getStrFormato()
                            .replace("{usuario}",propietario.getEmail())
                            .replace("{clave}",unClave),
                    propietario.getEmail());
            resultadoTransaccion.setResultado(propietario);
            return new ResponseEntity<>(propietario, HttpStatus.OK);
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
