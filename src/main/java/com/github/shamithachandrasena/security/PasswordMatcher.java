package com.github.shamithachandrasena.security;

public interface PasswordMatcher {
    boolean authenticate(String password, String retrievedPassword);
}
