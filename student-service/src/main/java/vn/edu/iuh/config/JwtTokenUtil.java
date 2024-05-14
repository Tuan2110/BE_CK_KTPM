package vn.edu.iuh.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.edu.iuh.models.Student;

import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration;
    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(Student student) throws InvalidParameterException {
        Map<String,Object> claims = new HashMap<>();
        claims.put("studentId",student.getStudentId());
        try{
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(student.getStudentId().toString())
                    .setExpiration(new Date(System.currentTimeMillis()+expiration*1000L))
                    .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        }catch(Exception e){
            throw new InvalidParameterException("Cannot create jwt token,error "+e.getMessage());
        }
    }
    private Key getSigninKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims = extractClaims(token);
        return claimResolver.apply(claims);
    }
    public boolean isTokenExpired(String token){
        Date expirationDate = extractClaim(token,Claims::getExpiration);
        return expirationDate.before(new Date());
    }
    public String extractStudentId(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public boolean validateToken(String token, UserDetails user){
        final String studentID = extractStudentId(token);
        return studentID.equals(user.getUsername()) && !isTokenExpired(token);
    }
}
