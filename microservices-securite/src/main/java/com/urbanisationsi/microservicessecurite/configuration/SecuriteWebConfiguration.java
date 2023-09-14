package com.urbanisationsi.microservicessecurite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.urbanisationsi.microservicessecurite.service.GestionnairePrevoyanceService;

@Configuration
@EnableWebSecurity
public class SecuriteWebConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ConnexionAuthenticationSuccessHandler connexionAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoGestionnairePrevoyanceDetails() {
        return new GestionnairePrevoyanceService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoGestionnairePrevoyanceDetails();
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/connecter").permitAll()
                .antMatchers("/enregistrer").permitAll()
                .antMatchers("/afficher/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin().successHandler(connexionAuthenticationSuccessHandler)
                .loginPage("/connecter").failureUrl("/connecter?error=true")
                .usernameParameter("email")
                .passwordParameter("motdepasse")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/deconnecter"))
                .logoutSuccessUrl("/").and().exceptionHandling();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}