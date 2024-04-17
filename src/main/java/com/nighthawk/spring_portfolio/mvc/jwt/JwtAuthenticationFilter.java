package com.nighthawk.spring_portfolio.mvc.jwt;

import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Implement the logic for JWT authentication here

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract and validate JWT token
        // Set Authentication in the context
        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }
}