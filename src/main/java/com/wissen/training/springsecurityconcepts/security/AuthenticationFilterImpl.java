package com.wissen.training.springsecurityconcepts.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationFilterImpl extends AuthenticationFilter {

    public AuthenticationFilterImpl(AuthenticationManager authenticationManager, AuthenticationConverter authenticationConverter) {
        super(authenticationManager, authenticationConverter);
    }

    public AuthenticationFilterImpl(AuthenticationManagerResolver<HttpServletRequest> authenticationManagerResolver, AuthenticationConverter authenticationConverter) {
        super(authenticationManagerResolver, authenticationConverter);
    }
}
