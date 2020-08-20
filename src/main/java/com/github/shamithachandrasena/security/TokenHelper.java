package com.github.shamithachandrasena.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.spec.SecretKeySpec;
import java.security.KeyStore;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenHelper {

    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_AUDIENCE = "audience";
    static final String CLAIM_KEY_CREATED = "created";

    public String getUsernameFromToken(String authToken){
        Jws<Claims> jws = null;

        try {
            jws = Jwts.parser()
                    .setSigningKey(KeyHolder.getInstance().getKey())
                    .parseClaimsJws(authToken);
            // we can safely trust the JWT
        }
        catch (JwtException ex) {
            // we *cannot* use the JWT as intended by its creator
            }
        return jws.getBody().getId();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
//        final String username = getUsernameFromToken(token);
//        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
//        return (
//                username.equals(user.getUsername())
//                        && !isTokenExpired(token));
//                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
        return true;
    }


//    public String getUsernameFromToken(String token) {
//        String username;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            if(claims != null){
//                username = claims.getSubject();
//            }else {
//                username = null;
//            }
//        } catch (Exception e) {
//            username = null;
//        }
//        return username;
//    }
//
//    public Date getCreatedDateFromToken(String token) {
//        Date created;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            if(claims != null){
//                created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
//            }else {
//                created = null;
//            }
//        } catch (Exception e) {
//            created = null;
//        }
//        return created;
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        Date expiration;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            if(claims != null){
//                expiration = claims.getExpiration();
//            }else {
//                expiration = null;
//            }
//        } catch (Exception e) {
//            expiration = null;
//        }
//        return expiration;
//    }
//
//    public String getAudienceFromToken(String token) {
//        String audience;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            if(claims != null){
//                audience = (String) claims.get(CLAIM_KEY_AUDIENCE);
//            }else {
//                audience = null;
//            }
//        } catch (Exception e) {
//            audience = null;
//        }
//        return audience;
//    }
//
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey("S22031761074399972255565179375810739204315868837865395611896995123488899465680044159745813873556234904892690981615687419484363940631889730233057909278938681768384476909488427304457211613870121639795377004056895481158237103532161940092272910215764477176153143924102167635619187789430391426536200713579767766743225901468438856095292224222109611217950497421030396052643789495345549118719349410697704538413765442285369601470930465385820864048373537098056649098020758988855073402595545917467569995754889642103237961018071947072291223611996807625497409159661954227365743855299056093552720743314636215671749801188664586282453")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }
//
//    private Date generateExpirationDate() {
//        return new Date(System.currentTimeMillis() + expiration * 1000);
//    }
//
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
//        return (lastPasswordReset != null && created.before(lastPasswordReset));
//    }
//
//    private String generateAudience(Device device) {
//        String audience = AUDIENCE_UNKNOWN;
//        if (device.isNormal()) {
//            audience = AUDIENCE_WEB;
//        } else if (device.isTablet()) {
//            audience = AUDIENCE_TABLET;
//        } else if (device.isMobile()) {
//            audience = AUDIENCE_MOBILE;
//        }
//        return audience;
//    }
//
//    private Boolean ignoreTokenExpiration(String token) {
//        String audience = getAudienceFromToken(token);
//        return (AUDIENCE_TABLET.equals(audience) || AUDIENCE_MOBILE.equals(audience));
//    }
//
//    public String generateToken(UserDetails userDetails, Device device) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
//        claims.put(CLAIM_KEY_AUDIENCE, generateAudience(device));
//        claims.put(CLAIM_KEY_CREATED, new Date());
//        return generateToken(claims);
//    }
//
//    String generateToken(Map<String, Object> claims) {
//        return Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(generateExpirationDate())
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
//
//    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
//        final Date created = getCreatedDateFromToken(token);
//        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
//                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
//    }
//
//    public String refreshToken(String token) {
//        String refreshedToken;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            if(claims != null){
//                claims.put(CLAIM_KEY_CREATED, new Date());
//                refreshedToken = generateToken(claims);
//            }else {
//                refreshedToken = null;
//            }
//        } catch (Exception e) {
//            refreshedToken = null;
//        }
//        return refreshedToken;
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        JwtUser user = (JwtUser) userDetails;
//        final String username = getUsernameFromToken(token);
//        final Date created = getCreatedDateFromToken(token);
//        //final Date expiration = getExpirationDateFromToken(token);
//        return (
//                username.equals(user.getUsername())
//                        && !isTokenExpired(token));
////                        && !isCreatedBeforeLastPasswordReset(created, user.getLastPasswordResetDate()));
//    }
}
