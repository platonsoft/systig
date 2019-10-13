package com.systig.systigmaster.inventario.repositorios.daos;

import com.systig.systigmaster.inventario.modelos.Propietario;
import com.systig.systigmaster.inventario.repositorios.interfaces.CrudUsuarioRepo;
import com.systig.systigmaster.inventario.modelos.UsuarioActivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

import static com.systig.systigmaster.inventario.utilidades.Utilidades.QUERIES_ORACLE.QUERY.*;

@Repository
public class UsuarioDao implements CrudUsuarioRepo {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UsuarioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UsuarioActivo getSession(Principal principal, HttpServletRequest headers, HttpSession session) {
        return this.jdbcTemplate.queryForObject(SQL_USUARIO_PERMISOS.getSQL(),
                new Object[]{principal.getName(),PRODUCTO_SYSTIG_CODIGO.getNumeros()},
                (rs, rwNumber) -> {
                    UsuarioActivo emp = new UsuarioActivo();
                    emp.setUsername(rs.getString("USERNAME"));
                    emp.setRol(rs.getString("ROLE"));
                    emp.setCodigoSystig(rs.getInt("PRODUCTO_SYSTIG_CODIGO"));
                    emp.setNivelAcceso(rs.getInt("NIVEL_ACCESO"));

                    String ipAddress = headers.getHeader("HTTP_X_FORWARDED_FOR");
                    if (ipAddress == null) {
                        ipAddress = headers.getRemoteAddr();
                    }

                    emp.setSessionId(session.getId());
                    emp.setIpRemota(ipAddress);

                    Propietario propietario = new Propietario();
                    propietario.setId(rs.getLong("ID_CLIENTE"));

                    emp.setPropietario(propietario);
                    return emp;
                });
    }
}
