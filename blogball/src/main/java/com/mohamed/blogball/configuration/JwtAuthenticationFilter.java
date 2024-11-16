package com.mohamed.blogball.configuration;

import com.mohamed.blogball.security.JwtService;
import com.mohamed.blogball.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsServiceImpl userDetailsService;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    final String username;

    // Extract token from cookies
    String token = jwtService.getTokenFromCookie(request);
    // DEBUG : Log the cookie
    System.out.println("Extracted Token: " + token);

    // Proceed if token is missing
    if (token == null) {
      filterChain.doFilter(request, response);
      return;
    }

    // Extract username or email from token
    username = jwtService.extractUsername(token);
    // DEBUG : Log the extracted username (email or username)
    System.out.println("Extracted username from JWT: " + username);

    // Authenticate user if not already authenticated
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      // Load user details (either by username or email)
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (jwtService.isTokenValid(token, userDetails)) {
        // Create authentication token
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));
        // Set the authentication in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
