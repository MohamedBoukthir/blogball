package com.mohamed.blogball.controller;

import com.mohamed.blogball.payload.AuthenticationResponse;
import com.mohamed.blogball.payload.LoginRequest;
import com.mohamed.blogball.payload.RegisterRequest;
import com.mohamed.blogball.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
}
