package com.app.restaurantgit.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private AppUserSecurity appUserSecurity;

    public WebSecurity(@Qualifier("appUserSecurity") AppUserSecurity appUserSecurity) {
        this.appUserSecurity = appUserSecurity;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/users/**", "/menu/**", "/order/**").permitAll()
                .antMatchers("/css/**", "/img/**", "/js/**", "/images/**").permitAll()
                .antMatchers("/manager/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login/login").permitAll()
                .loginProcessingUrl("/app-login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/manager", true)
                .failureUrl("/login/login/error")

                .and()
                .logout().permitAll()
                .logoutUrl("/app-logout")
                .clearAuthentication(true)
                .logoutSuccessUrl("/") // gdzie ma mnie przekierowac po wylogowaniu

                .and()
                .exceptionHandling();


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*auth
                .inMemoryAuthentication()
                .withUser("u").password(passwordEncoder().encode("1234")).roles("USER")
                .and()
                .withUser("a").password(passwordEncoder().encode("1234")).roles("ADMIN")
                .and()
                .withUser("s").password(passwordEncoder().encode("1234")).roles("SUPER");*/

        auth
                .userDetailsService(appUserSecurity)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
