package securityfeatures.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import securityfeatures.entity.UserEntity;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JWTService {


    @Value("${jwt.secretKey}")
    private String secreteKey;

    public SecretKey getSecreteKey(){
        return Keys.hmacShaKeyFor(secreteKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateJwtToken(UserEntity user){
       return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("role", Set.of("ADMIN,USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
               .signWith(getSecreteKey())
               .compact();

    }
    public Long getIdFromTheToken(String token){
        Claims claims=Jwts.parser()
                .verifyWith(getSecreteKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }

}
