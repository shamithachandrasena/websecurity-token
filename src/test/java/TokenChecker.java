import com.github.shamithachandrasena.security.KeyHolder;
import com.github.shamithachandrasena.security.TokenHelper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.Test;

import java.security.Key;
import java.util.UUID;

public class TokenChecker {

    @Test
    public void generateKey(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        KeyHolder.getInstance().setKey(key);
        System.out.println(key.toString());
    }

    @Test
    public void generateToken(){
        String jws = Jwts.builder().setSubject("AuthKey")
                .setId("Shamitha Chandrasena")
                .signWith(KeyHolder.getInstance().getKey(),SignatureAlgorithm.HS256).compact();
        System.out.println(jws);
    }

    public String getToken(){
        String jws = Jwts.builder().setSubject("AuthKey")
                .setId("Shamitha Chandrasena")
                .signWith(KeyHolder.getInstance().getKey(),SignatureAlgorithm.HS256).compact();
        return jws;
    }

    @Test
    public void verifyToken(){
        generateToken();
        System.out.println(new TokenHelper().getUsernameFromToken(getToken()));
    }

    @Test
    public void generateUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
    }
}
