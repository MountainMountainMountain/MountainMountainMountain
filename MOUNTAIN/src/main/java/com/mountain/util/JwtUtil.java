package com.mountain.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import com.mountain.model.dto.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "SSAFY_NonMajor_JavaTrack_SecretKey";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

	public String createToken(User user) {
		Date exp = new Date(System.currentTimeMillis() + 1000 * 60 * 60); // 1시간
		return Jwts.builder().header().add("typ", "JWT").and().claim("serial", user.getSerial())
				.claim("id", user.getId()).claim("name", user.getName()).claim("birthDate", user.getBirthDate())
				.claim("gender", user.getGender()).claim("email", user.getEmail()).claim("regDate", user.getRegDate())
				.claim("point", user.getPoint())

				.expiration(exp).signWith(secretKey).compact();
	}

	// 실제로 확인하려고 하는 용도가 아니고 유효기간이 지났다면 알아서 에러를 발생시키려고 함.
	public Jws<Claims> validate(String token) {
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
	}

}
