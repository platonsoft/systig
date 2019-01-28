package com.systig.remesas.seguridad;

import com.systig.remesas.modelos.IDispositivo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;

import java.util.NoSuchElementException;


@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/apirem/usr/login").permitAll() //permitimos el acceso a /login a cualquiera
                .anyRequest().authenticated() //cualquier otra peticion requiere autenticacion
                .and()
                // Las peticiones /login pasaran previamente por este filtro
                .addFilterBefore(new AutenticacionFilter("/apirem/usr/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)

                // Las demás peticiones pasarán por este filtro para validar el token
                .addFilterBefore(new AutorizacionFilter(),
                        UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Creamos una cuenta de usuario por default
        auth.userDetailsService(userDetailsServiceBean());
    }

    @Autowired
    private IDispositivo iDispositivo;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return username -> {
            String password = null;
            try{
            password = iDispositivo.findAll().stream()
                        .filter(d -> d.getUsuarioId().getUsuario().equals(username))
                        .findFirst()
                        .get()
                        .getUsuarioId()
                        .getClave();
            }catch (NoSuchElementException e){
                throw new UsernameNotFoundException("No coinciden las credenciales");
            }

            if(password != null && !password.toUpperCase().contains("INEXISTENTE")) {
                password = "{noop}" + password;
                return new User(username, password,
                        AuthorityUtils.commaSeparatedStringToAuthorityList("USER"));
            } else {
                throw new UsernameNotFoundException("No existe el usuario '"
                        + username + "'");
            }


        };
    }
}
