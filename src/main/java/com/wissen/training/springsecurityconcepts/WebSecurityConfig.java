package com.wissen.training.springsecurityconcepts;

import com.wissen.training.springsecurityconcepts.filters.JwtFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *  AuthenticationManager calls all the AuthenticationProvider implementation till the user is successfully authenticated
 *  or all the implementation has been called and then throw the exception.
 *
 *
 * AuthenticationProvider:
 *      The AuthenticationProvider in spring security takes care of the authentication logic.
 *      The default implementation of AuthenticationProvider delegates the responsibility of authentication to UserDetailsService
 *      and PasswordEncoder for password validation.
 *      But if we have some custom requirements we can build our own logic by implementing AuthenticationProvider interface.
 *
 *
 *
 *
 *
 *
 *
 *
 *  Authentication: Who the user is? (Username and password)
 *
 *  Integration With Jpa (UserDetailsService and UserDetails Interface)
 *  Steps:
 *    1) Create a class extending WebSecurityConfigurerAdapter.
 *    2) Override the method configure(AuthenticationManagerBuilder builder)
 *    3) Using the AuthenticationManagerBuilder class Instance we can configure how we want to authenticate the users.
 *    4)  Options Provided By AuthenticationManagerBuilder:
 *                    4.1) auth.inMemoryAuthentication() -> For in memory User
 *                    4.2) auth.jdbcAuthentication(); -> For Jdbc Authentication
 *                    4.3) auth.userDetailsService(userDetailsService); -> Integration with Spring JPA
 *     5) UserDetailsService:
 *               5.1) An Interface (UserDAOAuthentication)
 *               5.2) Create a class that implements UserDetailsService's method loadUserByUsername()
 *               5.3) Implementing loadUserByUsername():
 *                     5.3.1) Returns an instance of UserDetails class if a user has been found successfully.
 *                     5.3.2) This is then stored as a Principle (Current Logged-In User) in Spring Security Context.
 *
 *
 *  Authorization: What the user is trying to access.
 *   Internal Flow
 *   When a client makes a request , the AuthenticationFilter intercepts and validates the user if they are whom they are claiming.
 *   Post authentication, the filter stores the UserDetails in applications security context. An instance of UserDetails stores name,
 *   password, authorities.
 *   Now the AuthorizationFilter intercepts and checks if the user has access to the particular path they are requesting based on the
 *   authorities stored in UserDetails instance in security context.
 *
 *
 *  Steps:
 *      1) Override the method configure(HttpSecurity security)
 *
 *
 *
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.jdbcAuthentication();
//        auth.inMemoryAuthentication()
//                .withUser("new_user")
//                .password("new_password")
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password("admin")
//                .roles("ADMIN");


        // Authentication With JPA

        auth.userDetailsService(userDetailsService);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // For Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/authenticate/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
