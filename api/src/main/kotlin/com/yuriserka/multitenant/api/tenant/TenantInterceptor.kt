package com.yuriserka.multitenant.api.tenant

import com.yuriserka.multitenant.api.auth.JwtService
import com.yuriserka.multitenant.api.exceptions.UnauthorizedException
import com.yuriserka.multitenant.core.datasources.TenantContextHolder
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

private val logger = KotlinLogging.logger {}

@Component
class TenantInterceptor(private val jwtService: JwtService) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val jwt = request.getHeader("Authorization") ?: throw UnauthorizedException()

        val (type, token) = jwt.split(" ", limit = 2)
        if (type != "Bearer") throw UnauthorizedException()
        if (jwtService.isTokenExpired(token)) throw UnauthorizedException()

        val tenantId = jwtService.getTenantIdFromToken(token)

        logger.info { "tenant: $tenantId - ${request.method} ${request.requestURI}" }
        TenantContextHolder.setTenantId(tenantId)
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        super.postHandle(request, response, handler, modelAndView)
        TenantContextHolder.clear()
    }
}