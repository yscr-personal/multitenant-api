package com.yuriserka.multitenant.api.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*


@Service
class JwtService(
    @Value("\${jwt.secret}") private val jwtSecret: String,
    @Value("\${jwt.token.expiration.seconds}") private val jwtTokenValidation: Long
) {
    fun getTenantIdFromToken(token: String): String {
        return getClaimFromToken(token, Claims::getSubject)
    }

    fun isTokenExpired(token: String): Boolean {
        val expiration = getClaimFromToken(token, Claims::getExpiration)
        return expiration.before(Date())
    }

    fun generateToken(tenantId: String): String {
        val claims: Map<String, Any?> = mapOf(
            "scopes" to listOf("read", "write"),
            "sub" to tenantId
        )
        return doGenerateToken(claims, tenantId)
    }

    private fun doGenerateToken(claims: Map<String, Any?>, subject: String): String {
        val now = System.currentTimeMillis()
        val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret))

        return Jwts
            .builder()
            .setSubject(subject)
            .setClaims(claims)
            .setIssuedAt(Date(now))
            .setExpiration(Date(now + jwtTokenValidation * 1000))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    private fun <T> getClaimFromToken(token: String, claimsResolver: (Claims) -> T): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver(claims)
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(jwtSecret)
            .build()
            .parseClaimsJws(token)
            .body
    }
}