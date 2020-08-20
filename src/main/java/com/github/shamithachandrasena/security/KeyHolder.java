package com.github.shamithachandrasena.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class KeyHolder {
    private static KeyHolder keyHolder;
    private Key key;
    private KeyHolder(){

    }
    public static KeyHolder getInstance(){
        if(keyHolder == null){
            keyHolder = new KeyHolder();
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            keyHolder.setKey(key);
        }
        return keyHolder;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
}
