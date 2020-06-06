package com.systig.systigmaster.contable.servicios.servicios;

import com.systig.base.objetos.ResultadoTransaccion;
import com.systig.base.repositorios.contable.entidades.Documento;
import com.systig.base.repositorios.contable.oad.IDocumentoDao;
import com.systig.base.repositorios.nominas.entidades.EmpresaXPersona;
import com.systig.base.repositorios.nominas.entidades.Persona;
import com.systig.base.repositorios.nominas.oad.IEmpresaXPersonaDao;
import com.systig.base.repositorios.nominas.oad.IPersonaDao;
import com.systig.systigmaster.contable.servicios.interfaces.IDocumentosServ;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentoServ implements IDocumentosServ {

    private final IPersonaDao iPersonaDao;
    private final IDocumentoDao iDocumentoDao;
    private final IEmpresaXPersonaDao iEmpresaXPersonaDao;

    public DocumentoServ(IPersonaDao iPersonaDao, IDocumentoDao iDocumentoDao, IEmpresaXPersonaDao iEmpresaXPersonaDao) {
        this.iPersonaDao = iPersonaDao;
        this.iDocumentoDao = iDocumentoDao;
        this.iEmpresaXPersonaDao = iEmpresaXPersonaDao;
    }

    @Override
    public ResponseEntity<?> getListaDocumentos(HttpHeaders headers, HttpSession session, TIPO_DOCUMENTO tipoDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                Optional<EmpresaXPersona> empresaXPersona = iEmpresaXPersonaDao.findAll().stream()
                        .filter(empresaXPersona1 -> empresaXPersona1.getIdPersona().getIdPersona().equals(usuario.getIdPersona()))
                        .findFirst();

                List<Documento> list = this.iDocumentoDao.findAll().stream()
                        .filter(documento -> documento.getTipoDocumento().equals(tipoDocumento.getTipoDocumento()))
                        .collect(Collectors.toList());

                resultadoTransaccion.setResultado(list);
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<>("Acceso denegado", HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error Interno, Contacte al administrador del sistema", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getDocumento(HttpHeaders headers, HttpSession session, Long idDocumento) {
        try{
            ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
            Persona usuario = iPersonaDao.statusSession(headers);
            if(usuario!=null){
                resultadoTransaccion.setToken(iPersonaDao.retornoToken(usuario));
                resultadoTransaccion.setResultado(this.iDocumentoDao.getOne(idDocumento));
                return new ResponseEntity<>(resultadoTransaccion, HttpStatus.OK);
            }
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<List>(new ArrayList(), HttpStatus.UNAUTHORIZED);
        }
    }

}
