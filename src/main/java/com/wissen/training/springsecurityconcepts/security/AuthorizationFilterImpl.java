package com.wissen.training.springsecurityconcepts.security;

import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationFilterImpl extends AuthorizationFilter {
    /**
     * Creates an instance.
     *
     * @param authorizationManager the {@link AuthorizationManager} to use
     */
    public AuthorizationFilterImpl(AuthorizationManager<HttpServletRequest> authorizationManager) {
        super(authorizationManager);
    }
}
