package com.systig.systigmaster.sesiones.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.sql.DataSource;

import static com.systig.systigmaster.sesiones.repositorios.interfaces.IUsuarioDao.QUERIES_ORACLE.QUERY.SQL_USUARIO;
import static com.systig.systigmaster.sesiones.repositorios.interfaces.IUsuarioDao.QUERIES_ORACLE.QUERY.SQL_USUARIO_ROLE;

@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers( HttpMethod.POST,"/api/registro**").permitAll()
                .antMatchers( HttpMethod.POST,"/api/recuperar**").permitAll()
                .antMatchers( HttpMethod.GET,"/api/test**").permitAll()
                .anyRequest().authenticated()
                //.antMatchers( HttpMethod.GET,"/api/test**").permitAll()
                //.antMatchers("/api/login").permitAll()
                //.antMatchers( HttpMethod.GET,"/api/inv**").hasRole("CLIENTE")
                //.antMatchers( HttpMethod.POST,"/api/inv**").hasRole("CLIENTE")
                //.antMatchers("/api/administrador/*").hasRole("ADMINISTRADOR")
                //.anyRequest().permitAll();
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                //.addFilterAfter(new TokenFiltro(), BasicAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(SQL_USUARIO.getSQL())
                .authoritiesByUsernameQuery(SQL_USUARIO_ROLE.getSQL());
    }
}
