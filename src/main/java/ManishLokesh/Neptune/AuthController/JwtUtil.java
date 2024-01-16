package ManishLokesh.Neptune.AuthController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY = "LOHARMANISH201";
    private static final long EXPIRATION_TIME = 86400000;
    private Logger logger = LoggerFactory.getLogger("app.authorization");

    public String generateToken(String role, Long id){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setAudience(role)
                .setSubject(String.valueOf(id))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }


    public Claims extractTokenDetails(String token){
         return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }


    public String validateRole(String token) {
        try{
            Claims claims  = extractTokenDetails(token);
//            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            String  id = claims.getSubject();
            logger.info("id is {}",id);
            String role = claims.getAudience();
            logger.info("role is {}",role);
            logger.info("clams {}",claims);
            return role;
        }catch (Exception e){
            return "Not Authorize to access this";
        }
//            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Long validateId(String token){
//        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        Claims claims = extractTokenDetails(token);
        Long id = Long.valueOf(claims.getSubject());
        String role = claims.getAudience();
        logger.info("role is {}",role);
        logger.info("id is {}",id);
        return id;
    }
}
