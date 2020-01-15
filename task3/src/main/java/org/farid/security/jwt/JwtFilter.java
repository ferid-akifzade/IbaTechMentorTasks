package org.farid.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.farid.security.userdetails.CustomUserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenService tokenService;
    private final CustomUserDetailsService userDetailsService;

    public JwtFilter(JwtTokenService tokenService, CustomUserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            extractTokenFromRequest(request)
                    .flatMap(tokenService::tokenToClaims)
                    .map(tokenService::getIdFromClaims)
                    .map(userDetailsService::loadUserById)
                    .map(ud -> new UsernamePasswordAuthenticationToken(ud, null, ud.getAuthorities()))
                    .ifPresent(auth -> {
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    });
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
    }

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        final String auth = request.getHeader(Const.AUTH_HEADER);
        if (StringUtils.hasText(auth) && auth.startsWith(Const.AUTH_BEARER)) {
            return Optional.of(auth.substring(Const.AUTH_BEARER.length()));
        }
        final String req_auth = request.getParameter(Const.AUTH_TOKEN);
        if (StringUtils.hasText(req_auth)) {
            return Optional.of(req_auth.substring(Const.AUTH_BEARER.length()));
        }
        return Optional.empty();
    }

}
