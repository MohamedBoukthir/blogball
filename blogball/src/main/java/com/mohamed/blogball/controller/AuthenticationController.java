package com.mohamed.blogball.controller;

import com.mohamed.blogball.payload.AuthenticationResponse;
import com.mohamed.blogball.payload.LoginRequest;
import com.mohamed.blogball.payload.RegisterRequest;
import com.mohamed.blogball.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "authentication")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/sign-up")
  public AuthenticationResponse register(@RequestBody RegisterRequest registerRequest) {
    return authenticationService.register(registerRequest).getBody();
  }

  @PostMapping("/sign-in")
  public AuthenticationResponse login(
      @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
    return authenticationService.login(loginRequest, httpServletResponse).getBody();
  }

  @PostMapping("/logout")
  public ResponseEntity<Void> logout(HttpServletResponse response) {
    // Clear the JWT cookie
    ResponseCookie clearCookie =
        ResponseCookie.from("accessToken", "")
            .httpOnly(true)
            .secure(false) // Set to true in production (HTTPS)
            .path("/")
            .maxAge(0) // Expire immediately
            .sameSite("Lax")
            .build();
    response.addHeader(HttpHeaders.SET_COOKIE, clearCookie.toString());
    return ResponseEntity.noContent().build();
    }
}
