package com.pi.PoslovnaInformatika.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.pi.PoslovnaInformatika.security.RestAuthenticationEntryPoint;
import com.pi.PoslovnaInformatika.security.TokenAuthenticationFilter;
import com.pi.PoslovnaInformatika.security.TokenHelper;
import com.pi.PoslovnaInformatika.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;

    //Neautorizovani pristup zastcenim resursima
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //Definisemo nacin autentifikacije
    //Svaki
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
        auth.userDetailsService( jwtUserDetailsService )
                .passwordEncoder( passwordEncoder() );
    }

    @Autowired
    TokenHelper tokenHelper;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //komunikacija izmedju klijenta i servera je stateless
                .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS ).and()
                //za neautorizovane zahteve posalji 401 gresku
                .exceptionHandling().authenticationEntryPoint( restAuthenticationEntryPoint ).and()
                .authorizeRequests()
                //svim korisnicima dopusti da pristupe putanjama /auth/**
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/roba/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/narudzbenice/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/fakture/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/otpremnice/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/narudzbenice/stavkeNarudzbenice/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/fakture/stavkeFakture/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/otpremnice/stavkeOtpremnice/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/cenovnik/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/gruparobe/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/kupac/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/mesto/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/pdv/**").hasAuthority("ADMIN")
                
                .antMatchers("/api/preduzece/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/prevoznik/**").hasAnyAuthority("ADMIN","REGULAR")
                .antMatchers("/api/stavkacenovnika/**").hasAuthority("ADMIN")
                .antMatchers("/api/stopapdv/**").hasAuthority("ADMIN")
                .antMatchers("/api/users/**").hasAuthority("ADMIN")
                //svaki zahtev mora biti autorizovan
                .anyRequest().authenticated().and()
                //presretni svaki zahtev filterom
                .addFilterBefore(new TokenAuthenticationFilter(tokenHelper, jwtUserDetailsService), BasicAuthenticationFilter.class)

        .csrf().disable();
    }



    //Generalna bezbednost aplikacije
    @Override
    public void configure(WebSecurity web) throws Exception {
        // TokenAuthenticationFilter ce ignorisati sve ispod navedene putanje
        web.ignoring().antMatchers(
                HttpMethod.POST,
                "/auth/login"
                
                
        );
        web.ignoring().antMatchers(
                HttpMethod.GET,
                "/",
                "/webjars/**",
                "/*.html",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js",
                "/**/*.jpg",
                "/**/*.png",
                "/api/poslovnagodina/**"
                
            );
//        "/**/*.jpg"
    }

}
