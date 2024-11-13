package com.mohamed.blogball.services;

import com.mohamed.blogball.DTO.UserDto;
import com.mohamed.blogball.payload.AuthenticationResponse;
import com.mohamed.blogball.payload.LoginRequest;
import com.mohamed.blogball.payload.RegisterRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    UserDto createUserAccount(RegisterRequest registerRequest);
    ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest);
    ResponseEntity<AuthenticationResponse> login(LoginRequest loginRequest, HttpServletResponse httpServletResponse);

}
