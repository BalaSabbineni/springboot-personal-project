package com.personalProject.personalProject.auth.jwt;

import com.personalProject.personalProject.auth.dto.UserLogInDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    // private final String secretKey = "a9faaf771707993bb79aa6962e3a44deffb3a6f9d3979c7c7958a4f34200a66ec0799945bf5498c01484521814f74c3d63117244f37ec1f40fe100148a4953cd323e06bca0c9e4e50f10c1cd06021a26428630c41d193247ea3b8c4763af6fd2f35129deff7e843837815693ec0f5909acbf065875653dfb68875dd898d47fc72bf3e417a2a193b9a714d5cb80fc123d04790004c890487e0d018a0b1936c0c615b999867132157878f777d2af86293ac059aaea731e2a618fa3e5835e0038a231ff01b5a4207e8887da236a4f66f12478dbea34a69d9de5ab6076dd440f06ea819ed81bcb87bed85c757070da07453a6f7491c2ec5f9f328dfab6f1b793bec0";
    private final String secretKey = "RqxPOuVfHoBA8Uq40MhJvfY6qEHOOWWvg6N9W9vt23s=";

    /*
     *To generate token, we need to pass three details like Header & Payload & Signature.
     *  we need details like secretKey, userName, expiration and issuer etc details.
     */
    public String generateToken(UserLogInDto userLoginDetails) {
        // Map<String, Object> claims = new HashMap<>(); // claims mean data like username, expiryDate etc.

        return Jwts.builder()
                .claims()
                //.add(claims)
                .subject(userLoginDetails.getUserName())
                .issuer("BALA")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 10 * 1000))
                .and()
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decode = Decoders.BASE64.decode(getSecretKey());
        return Keys.hmacShaKeyFor(decode);
    }

    public String getSecretKey() {
        return secretKey; // Inject this key in secured way like ENV variable.
    }

    /*
     * Check username from token and userDetails are same
     * Next check token expiry time
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpire(token));
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject); // taking function
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        Claims claims = extractClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpire(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
