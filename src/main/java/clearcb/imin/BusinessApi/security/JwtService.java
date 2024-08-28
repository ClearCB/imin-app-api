package clearcb.imin.BusinessApi.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {

    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Value("${app.security.jwt.expiration}")
    private Long jwtDurationSeconds;

    public String getToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtDurationSeconds * 1000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }


    public boolean isValidToken(String token, UserDetails userDetails) {

        String username = getUsernameFromToken(token);
        boolean isValidToken = username.equals(userDetails.getUsername());

        if (!StringUtils.hasLength(token)) {
            return false;
        }

        return isValidToken;

    }

    public boolean ensureTokenIsWellFormed(String token) {
        try {
            getAllClaims(token);
        } catch (SignatureException e) {
            log.error(SecurityMessageEnum.INVALID_JWT_SIGNATURE.getMessage(), e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.error(SecurityMessageEnum.INVALID_JWT_TOKEN.getMessage(), e.getMessage());
            return false;
        } catch (ExpiredJwtException e) {
            log.error(SecurityMessageEnum.INVALID_JWT_EXPIRED.getMessage(), e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            log.error(SecurityMessageEnum.INVALID_JWT_UNSUPPORTED.getMessage(), e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.error(SecurityMessageEnum.INVALID_JWT_EMPTY.getMessage(), e.getMessage());
            return false;
        } catch (Exception e) {
            log.error(SecurityMessageEnum.INVALID_JWT_EXCEPTION.getMessage(), e.getMessage());
            return false;
        }
        return true;
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

}