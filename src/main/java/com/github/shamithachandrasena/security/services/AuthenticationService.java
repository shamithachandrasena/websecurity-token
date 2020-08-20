package com.github.shamithachandrasena.security.services;


import com.github.shamithachandrasena.security.PasswordMatcher;
import com.github.shamithachandrasena.security.KeyHolder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public class AuthenticationService {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordMatcher passwordMatcher;

    public String authenticate(User user){
        User  retrievedUser = (User)userDetailsService.loadUserByUsername(user.getUsername());
        if(user.isEnabled()){
            try{
                if(passwordMatcher.authenticate(user.getPassword(),retrievedUser.getPassword())){
                    String jws = Jwts.builder().setSubject("AuthKey")
                                .setId(retrievedUser.getUsername())
                                .signWith(KeyHolder.getInstance().getKey(),SignatureAlgorithm.HS256).compact();
                        return jws;
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
        }
        return "NOT AUTHENTICATED";
    }

}
