package app.core.security;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import app.core.models.CustomeUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtTokenProvider {

	private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
	private String encodedSecretKey = "this+is+my+key+and+it+must+be+at+least+256+bits+long";
	private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), signatureAlgorithm);

	public String generateToken(CustomeUserDetails user) {
		Instant now = Instant.now();
		String userId = Long.toString(user.getId());
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", userId);
		claims.put("username", user.getUsername());

		String token = Jwts.builder().setClaims(claims).setSubject(userId).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(10, ChronoUnit.HOURS))).signWith(decodedSecretKey).compact();

		System.out.println(token);
		return token;
	}

	public boolean validateToken(String token) {
		try {
			JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
			jwtParser.parseClaimsJws(token).getBody();
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT Signature");
		} catch (MalformedJwtException e) {
			System.out.println("Invalid JWT Token");
		} catch (ExpiredJwtException e) {
			System.out.println("Expired JWT Token");
		} catch (UnsupportedJwtException e) {
			System.out.println("Unsupported JWT Token");
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid JWT Claims String is Empty");
		}
		return false;
	}
	
	private Claims extractAllClaims(String token) throws ExpiredJwtException {
		JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(decodedSecretKey).build();
		return jwtParser.parseClaimsJws(token).getBody();
	}
	
	public Long getUserIdFromJWT(String token) {
		String userId = extractAllClaims(token).getSubject();
		return Long.parseLong(userId);
	}

}
