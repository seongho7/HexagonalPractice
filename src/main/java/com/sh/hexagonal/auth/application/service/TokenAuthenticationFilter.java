package com.sh.hexagonal.auth.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sh.hexagonal.auth.application.port.out.LoadAccessTokenPort;
import com.sh.hexagonal.auth.domain.AccessToken;
import com.sh.hexagonal.common.UserRole;
import com.sh.hexagonal.common.response.CommonResponse;
import com.sh.hexagonal.common.response.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public final class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final LoadAccessTokenPort loadAccessTokenPort;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            var jwt = getJwtFromRequest(request);
            if (Objects.nonNull(jwt)) {
                var oAccessToken = loadAccessTokenPort.loadByToken(jwt);
                var accessToken = validateToken(oAccessToken);
                var authToken = new UsernamePasswordAuthenticationToken(
                        accessToken.getAccount(), jwt, Collections.singletonList(new SimpleGrantedAuthority(UserRole.ROLE_USER))
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(request, response);
            SecurityContextHolder.getContext().setAuthentication(null);
        } catch (AuthenticationProcessingException e) {
            responseAuthFail(response, e);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        var bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private AccessToken validateToken(Optional<AccessToken> oAccessToken) {
        if(oAccessToken.isEmpty()) {
            throw new AuthenticationProcessingException(ErrorCode.AUTH_INVALID_TOKEN, null, null);
        }
        var accessToken = oAccessToken.get();
        if(accessToken.isExpired()) {
            throw new TokenExpiredException();
        }
        return accessToken;
    }

    private void responseAuthFail(HttpServletResponse response, AuthenticationProcessingException e) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().print(objectMapper.writeValueAsString(CommonResponse.fail(e.getErrorCode())));
        response.flushBuffer();
        response.getClass();
    }
}
