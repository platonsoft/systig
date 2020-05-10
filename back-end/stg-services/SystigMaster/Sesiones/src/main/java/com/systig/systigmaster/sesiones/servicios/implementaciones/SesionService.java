package com.systig.systigmaster.sesiones.servicios.implementaciones;

import com.google.gson.Gson;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.systig.base.repositorios.nominas.entidades.Cargo;
import com.systig.base.repositorios.nominas.entidades.Empresa;
import com.systig.base.repositorios.nominas.entidades.EmpresaXPersona;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.nominas.oad.IEmpresaXPersonaDao;
import com.systig.base.repositorios.nominas.oad.IEmpresaDao;
import com.systig.base.repositorios.sesiones.entidades.*;
import com.systig.base.objetos.GeoIP;
import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.sesiones.oad.*;
import com.systig.systigmaster.sesiones.servicios.interfaces.ISesionService;
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
import java.math.BigInteger;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SesionService implements ISesionService {
    private final IPersonaDao iPersonaDao;
    private final GeoIPLocationService locationService;
    private final IEmpresaDao iEmpresaDao;
    private final IEmpresaXPersonaDao iEmpresaXPersonaDao;
    private final IConfiguracionDao iConfiguracionDao;
    private final IConfiguracionDetalleDao iConfiguracionDetalleDao;
    private final IFormatoDocumentoDao iFormatoDocumentoDao;

    private final JavaMailSender sender;

    public SesionService(IPersonaDao iPersonaDao, GeoIPLocationService locationService, IEmpresaDao iEmpresaDao, IEmpresaXPersonaDao iEmpresaXPersonaDao, IConfiguracionDao iConfiguracionDao, IConfiguracionDetalleDao iConfiguracionDetalleDao, IFormatoDocumentoDao iFormatoDocumentoDao, JavaMailSender sender) {
        this.iPersonaDao = iPersonaDao;
        this.locationService = locationService;
        this.iEmpresaDao = iEmpresaDao;
        this.iEmpresaXPersonaDao = iEmpresaXPersonaDao;
        this.iConfiguracionDao = iConfiguracionDao;
        this.iConfiguracionDetalleDao = iConfiguracionDetalleDao;
        this.iFormatoDocumentoDao = iFormatoDocumentoDao;
        this.sender = sender;
    }

    @Override
    public ResponseEntity<?> getTokenSession(Principal principal, HttpServletRequest headers, HttpSession session) {
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        Persona usuario = iPersonaDao.getByUsernameEquals(principal.getName());

        String tokenEnc = iPersonaDao.retornoToken(usuario);
        Persona tokenDesc = iPersonaDao.retornoPersona(tokenEnc);
        System.out.println("Token --> " + tokenEnc);
        System.out.println("Usuario --> " + (new Gson()).toJson(tokenDesc));

        resultadoTransaccion.setToken(tokenEnc);
        resultadoTransaccion.setResultado("TOKEN DE ACCESO VALIDO");
        return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getPersona(HttpHeaders headers) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(usuario);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addEmpresa(HttpHeaders headers, Empresa empresa, Cargo cargo) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                if (usuario.getRol()==0){
                    empresa = iEmpresaDao.save(empresa);

                    EmpresaXPersona empresaXPersona = new EmpresaXPersona();
                    empresaXPersona.setIdPersona(usuario);
                    empresaXPersona.setIdEmpresa(empresa);
                    empresaXPersona.setIdCargo(cargo);

                    resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                    resultadoTransaccion.setResultado(iEmpresaXPersonaDao.save(empresaXPersona));
                    return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<?> addPersona(HttpServletRequest request, Persona persona) {
        try{
            Persona finalPersona = persona;

            Optional<Empresa> opEmpresa = iEmpresaDao.findAll().stream().filter(empresa1 -> empresa1.getEmail().equals(finalPersona.getEmail())).findFirst();
            if (opEmpresa.isPresent()){
                return new ResponseEntity<>("El Usuario ya se encuentra Registrado", HttpStatus.CONFLICT);
            }

            Optional<Persona> opUsuario = iPersonaDao.findAll().stream().filter(usuario -> usuario.getEmail().equals(finalPersona.getEmail())).findFirst();
            if (opUsuario.isPresent()){
                return new ResponseEntity<>("El Usuario ya se encuentra Registrado", HttpStatus.CONFLICT);
            }

            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            String unClave = iPersonaDao.getClaveAleatoria();

            try {
                GeoIP geoIP = this.locationService.getLocation(request.getRemoteAddr());
                persona.setPais(geoIP.getCountry());
                persona.setProvincia(geoIP.getCity());
            }catch (AddressNotFoundException e){
                persona.setPais("NP");
                persona.setProvincia("NP");
            }

            persona.setUsername(persona.getEmail());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            persona.setPassword(passwordEncoder.encode(unClave));
            persona.setEnabled(true);
            persona.setIpRemota(request.getRemoteAddr());
            persona.setFecha((new Date()).getTime());
            persona.setRol(0L);

            persona = iPersonaDao.save(persona);
            // persona.setConfiguracion(crearConfiguracionPorDefecto(persona.getIdPersona()));
            try {
                enviaCorreo("Nuevo Usuario",
                        IPersonaDao.FORMATOS_CORREO.EMAIL_USARIO_CREADO.getStrFormato()
                                .replace("{usuario}", persona.getEmail())
                                .replace("{clave}", unClave),
                        persona.getEmail());
                resultadoTransaccion.setResultado(persona);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }catch (Exception emailEx){
                System.out.println("No se pudo enviar el Email");
                System.out.println("Usuario: " + persona.getEmail() + "\nClave: " + unClave );
                resultadoTransaccion.setResultado("No Se pudo crear el Usuario en el Sistema");
                emailEx.printStackTrace();
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("Insersion Fallida", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> restoreUserSystig(String email) {
        Persona usuario = iPersonaDao.getByUsernameEquals(email);
        String unClave = iPersonaDao.getClaveAleatoria();

        if(usuario!=null){
            try {
                enviaCorreo("Reemplazo de clave",
                        IPersonaDao.FORMATOS_CORREO.EMAIL_USARIO_RESTAURADO.getStrFormato()
                                .replace("{usuario}",usuario.getUsername())
                                .replace("{clave}",unClave),
                        usuario.getUsername());
            } catch (Exception e) {
                e.printStackTrace();
            }
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            usuario.setPassword(passwordEncoder.encode(unClave));
            iPersonaDao.save(usuario);
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


    private Configuracion crearConfiguracionPorDefecto(Long idPropietario){
        LocalDateTime fechaActual = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(fechaActual, ZoneId.systemDefault());

        ConfiguracionDetalle configuracion = new ConfiguracionDetalle();
        configuracion.setFechaRegistro(zdt.toInstant().toEpochMilli());
        configuracion.setIsRetentor(false);
        configuracion.setNumeroTerminales(1L);

        ConfiguracionDetalle configuracionFinal = iConfiguracionDetalleDao.save(configuracion);

        String[] documentosNombres = new String[]{
                "NOTA DE PEDIDO",
                "NOTA DE RECIBIDO",
                "FACTURA DE VENTA",
                "FACTURA DE COMPRA",
                "NOTA DE CREDITO",
                "NOTA DE DEBITO"
        };

        List<FormatoDocumento> listado = new ArrayList<>();
        for (Long i = 0L; i < documentosNombres.length; i++) {
            FormatoDocumento documento = new FormatoDocumento();
            documento.setDenominacionDocumento(documentosNombres[i.intValue()]);
            documento.setNroControlBase(BigInteger.ZERO);
            documento.setNroControlFin(BigInteger.valueOf(999999));
            documento.setNroFormato(i);
            documento.setTipoDocumento(i);
            documento.setConfiguracionDetalle(configuracionFinal);
            listado.add(documento);
        }
        iFormatoDocumentoDao.saveAll(listado);

        Configuracion configuracionDefault = new Configuracion();
        configuracionDefault.setIdPropietario(idPropietario);
        configuracionDefault.setUrlInventario("http://localhost:8090");
        configuracionDefault.setUrlContable("http://localhost:8093");
        configuracionDefault.setUrlClientes("http://localhost:8091");
        configuracionDefault.setUrlProveedores("http://localhost:8092");
        configuracionDefault.setUrlSesiones("http://localhost:8096");
        configuracionDefault.setUrlTablero("http://localhost:4201/inicio/");
        configuracionDefault.setJsonConfiguracion(configuracionFinal);
        return iConfiguracionDao.save(configuracionDefault);
    }
}
