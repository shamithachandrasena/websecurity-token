package com.github.shamithachandrasena.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

public class AuthProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    public boolean supports(Class<?> authentication) {
        return (TokenBasedAuthentication.class.isAssignableFrom(authentication));
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        TokenBasedAuthentication authenticationToken = (TokenBasedAuthentication) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationToken.getName());
        if (userDetails == null){

        } else
            authenticationToken = new TokenBasedAuthentication(userDetails);

        return authenticationToken;
    }
}
