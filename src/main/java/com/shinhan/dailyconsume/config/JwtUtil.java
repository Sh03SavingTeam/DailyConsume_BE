package com.shinhan.dailyconsume.config;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.shinhan.dailyconsume.domain.MemberEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {
    
    private final Key key;
    private final long accessTokenExpTime;

    // 생성자에서 시크릿 키와 만료 시간 설정
    public JwtUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration_time}") long accessTokenExpTime) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);  // 시크릿 키를 디코딩
        this.key = Keys.hmacShaKeyFor(keyBytes);  // 시크릿 키로 HMAC 암호화
        this.accessTokenExpTime = accessTokenExpTime;
    }

    /**
     * Access Token 생성
     * 
     * @param member MemberEntity 객체
     * @return Access Token 문자열
     */
    public String createAccessToken(MemberEntity member) {
        return createToken(member, accessTokenExpTime);
    }

    /**
     * JWT 토큰 생성
     * 
     * @param member MemberEntity 객체
     * @param expireTime 만료 시간 (밀리초)
     * @return JWT 토큰 문자열
     */
    private String createToken(MemberEntity member, long expireTime) {
        Claims claims = Jwts.claims();  // 클레임 생성
        claims.put("memberId", member.getMemberId());  // 사용자 정보 설정
        claims.put("memberName", member.getMemberName());

        ZonedDateTime now = ZonedDateTime.now();  // 현재 시간
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);  // 토큰 만료 시간 설정

        return Jwts.builder()
                .setClaims(claims)  // 클레임 설정
                .setIssuedAt(Date.from(now.toInstant()))  // 발행 시간
                .setExpiration(Date.from(tokenValidity.toInstant()))  // 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)  // 서명 알고리즘 및 키 설정
                .compact();  // JWT 생성
    }

    /**
     * 토큰에서 사용자 ID 추출
     * 
     * @param token JWT 토큰 문자열
     * @return 사용자 ID
     */
    public String getUserId(String token) {
        return parseClaims(token).get("memberId", String.class);  // 클레임에서 사용자 ID 추출
    }

    /**
     * JWT 토큰 유효성 검사
     * 
     * @param token JWT 토큰 문자열
     * @return 유효 여부
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);  // 토큰 검증
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);  // 유효하지 않은 토큰
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);  // 만료된 토큰
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);  // 지원되지 않는 토큰
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);  // 비어 있는 클레임
        }
        return false;
    }

    /**
     * JWT 클레임 파싱
     * 
     * @param accessToken JWT 토큰 문자열
     * @return JWT 클레임 객체
     */
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();  // 유효한 토큰의 클레임 반환
        } catch (ExpiredJwtException e) {
            return e.getClaims();  // 만료된 토큰의 클레임 반환
        }
    }
}