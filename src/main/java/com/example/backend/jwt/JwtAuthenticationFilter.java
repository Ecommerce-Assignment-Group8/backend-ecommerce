package com.example.backend.jwt;

import jakarta.persistence.Column;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            System.out.println("Line 30");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("Line 34");
        String jwt = header.substring(7);
        String userEmail = jwtService.extractEmail(jwt);
        System.out.println("User email: " + userEmail);
        String role = jwtService.extractRole(jwt);
        Long userId = jwtService.extractUserId(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("Line 40");
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            if (jwtService.isTokenValid(jwt)) {
                System.out.println("Token is valid");
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userId, jwt, List.of(authority));
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                // Token expired - send 401 Unauthorized
                System.out.println("Line 50");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"error\":\"" + "Invalid Token!" + "\"}");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

}
