package com.treinamentojava.treinamentojava.usuario.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfigV2 {

    private final JwtAuthenticationFilter jwtAuthFilter; //tem que ser final para que a anotação RequiredArgsConstructor consiga instanciar juntamente com a classe
    private final AuthenticationProvider authenticationProvider;

    /**
     * Durante a inicialização da aplicação o Spring Security irá procurar pela implementação deste método que retorna SecurityFilterChain
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll() //todos os GET permitidos
                .antMatchers("/autenticacao/**").permitAll()  //não será necessário autenticação
                .anyRequest().authenticated() //demais solicitações vão precisar de autenticação
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //não irá guardar o estado de "autenticado", sendo necessário passar o token sempre
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //onde configuramos para que o filtro seja usado

        return http.build();
    }


    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

}
