package com.systig.systigmaster.sesiones.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;

import javax.sql.DataSource;

import static com.systig.systigmaster.sesiones.repositorios.interfaces.IUsuarioDao.QUERIES_ORACLE.QUERY.SQL_USUARIO;
import static com.systig.systigmaster.sesiones.repositorios.interfaces.IUsuarioDao.QUERIES_ORACLE.QUERY.SQL_USUARIO_ROLE;

@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    public SeguridadConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    CorsFilter corsFilter() {
        CorsFilter filter = new CorsFilter();
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers( HttpMethod.POST,"/api/registro**").permitAll()
                .antMatchers( HttpMethod.POST,"/api/recuperar**").permitAll()
                .antMatchers( HttpMethod.GET,"/api/test**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery(SQL_USUARIO.getSQL())
                .authoritiesByUsernameQuery(SQL_USUARIO_ROLE.getSQL());
    }
}
