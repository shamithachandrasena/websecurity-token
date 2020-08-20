package com.github.shamithachandrasena.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private String AUTH_HEADER = "Authorization";

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private TokenHelper tokenHelper;

    private String getToken(HttpServletRequest request) {

        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String error = "";
        String authToken = getToken(request);
        if (authToken != null) {
            // Get username from token
            String userId = tokenHelper.getUsernameFromToken(authToken);
            if (userId != null) {
                // Get userDetails
                UserDetails userDetails = userDetailService.loadUserByUsername(userId);
                // Create authentication
                TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                authentication.setToken(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                error = "Username from token can't be found in DB.";
//                throw new AccessDeniedException("ACCESS DENIED"){};
                    /* Use these methods only if ExceptionHandler filter registered */
            }
        } else {
            error = "Authentication Failed - no Bearer Token Provided.";
//            throw new AccessDeniedException("ACCESS DENIED"){}; // Use these methods only if ExceptionHandler filter registered
                    /* Use these methods only if ExceptionHandler filter registered */
        }
        if (!error.equals("")) {
            System.out.println(error);
            SecurityContextHolder.getContext().setAuthentication(null);//prevent show login form...
        }
        chain.doFilter(request, response);
    }


}