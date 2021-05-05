package com.jumshudivanych.simplecrm.config;

//import com.jumshudivanych.shitter.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Autowired
    private DataSource dataSource;
    */
    /*
    @Autowired
    private UserService userService;
    */
    /*
    @Autowired
    private UserSevice userSevice;
    */

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/", "/registration", "/login", "/welcome", "img/**", "/css/**", "/static/**", "/images/**").permitAll()
                .antMatchers(HttpMethod.POST, "/", "/registration", "/login", "/welcome", "/storage/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/storage/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));;

                 /*
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/home","/").permitAll()
                .antMatchers(HttpMethod.POST, "/home").permitAll()
                */

                /*
                .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                */
                /*
                .and()
                .csrf().disable()
                //.formLogin().disable();
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
                */
    }

    // @Override
    //protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    //  auth.userDetailsService(userService)
    //    .passwordEncoder(NoOpPasswordEncoder.getInstance());
        /*
        auth.jdbcAuthentication()
           .dataSource(dataSource)
           .passwordEncoder(NoOpPasswordEncoder.getInstance())
           .usersByUsernameQuery("select username, password, active from usr where username=?")
           .authoritiesByUsernameQuery("select u.username, ur.roles from usr u inner join user_role ur on u.id= ur.user_id where u.username=?");
        */
    // }
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSevice)
                .passwordEncoder(passwordEncoder);
    }
    */

    /*  spring guides убрать */

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }


}

