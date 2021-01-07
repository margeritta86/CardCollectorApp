package com.pokemon.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemon.app.service.common.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private LoginService userDetailsService;
    private final ObjectMapper objectMapper;

    public WebSecurityConfig(LoginService userDetailsService, ObjectMapper objectMapper) {
        this.userDetailsService = userDetailsService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);

    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable(); // to allow H2 console
        http.authorizeRequests()
                .antMatchers("/login-result").authenticated()
                .antMatchers("/my-account").hasRole("USER")
                .antMatchers("/booster").hasRole("USER")
                .antMatchers("/login-result").hasAuthority("ROLE_USER")
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/my-account") //the URL on which the clients should post the login information
                .usernameParameter("email") //the username parameter in the queryString, default is 'username'
                .passwordParameter("password"); //the password parameter in the queryString, default is 'password'


    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }

}
