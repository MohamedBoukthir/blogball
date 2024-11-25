package com.mohamed.blogball.services.Impl;

import com.mohamed.blogball.DTO.UserDto;
import com.mohamed.blogball.Repositories.UserRepository;
import com.mohamed.blogball.model.User;
import com.mohamed.blogball.model.role.RoleName;
import com.mohamed.blogball.payload.AuthenticationResponse;
import com.mohamed.blogball.payload.LoginRequest;
import com.mohamed.blogball.payload.RegisterRequest;
import com.mohamed.blogball.security.JwtService;
import com.mohamed.blogball.services.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Value("${jwt.expiration}")
  private int expiration;

  @Override
  public UserDto createUserAccount(RegisterRequest registerRequest) {
    User newUser = new User();
    newUser.setFirstName(registerRequest.getFirstName());
    newUser.setLastName(registerRequest.getLastName());
    newUser.setUsername(registerRequest.getUsername());
    newUser.setEmail(registerRequest.getEmail());
    newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
    newUser.setRole(RoleName.ROLE_USER);
    User createdUser = userRepository.save(newUser);
    return UserDto.builder().id(createdUser.getId()).username(createdUser.getUsername()).build();
  }

  @Override
  public ResponseEntity<AuthenticationResponse> register(RegisterRequest registerRequest) {
    if (userRepository.existsByEmail(registerRequest.getEmail())
        || userRepository.existsByUsername(registerRequest.getUsername())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(
              AuthenticationResponse.builder()
                  .error("Username or Email already exists !!!")
                  .build());
    }
    UserDto userAccount = createUserAccount(registerRequest);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            AuthenticationResponse.builder()
                .userDto(userAccount)
                .message("New user added successfully...")
                .build());
  }

  @Override
  public ResponseEntity<AuthenticationResponse> login(
      LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
    try {

      // Log the login request
      System.out.println("Login request received: " + loginRequest);

      // Check if username is valid and print it for debugging
      System.out.println("Attempting to authenticate with username: " + loginRequest.getUsername());

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              loginRequest.getUsername(), loginRequest.getPassword()));
      // Check if login identifier is email or username
      User user;
      if (loginRequest.getUsername().contains("@")) {
        user =
            userRepository
                .findByEmail(loginRequest.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));
      } else {
        user =
            userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
      }

      // Log the user that was fetched from the database
      System.out.println("User retrieved: " + user);

      // Generate JWT and set it in the response cookie
      var accessToken = jwtService.generateToken(user);

      System.out.println("Generated JWT token: " + accessToken);

      ResponseCookie responseCookie =
          ResponseCookie.from("accessToken", accessToken)
              .httpOnly(true)
              .secure(false) // Set to true in production (HTTPS)
              .path("/") // TODO: Change to true in production
              .sameSite("Lax") // Prevent CSRF
              .maxAge(expiration)
              .build();
      httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
      System.out.println("Setting JWT token in cookie: " + responseCookie);

      UserDto userDto =
          UserDto.builder()
              .id(user.getId())
              .firstName(user.getFirstName())
              .lastName(user.getLastName())
              .username(user.getUsername())
              .email(user.getEmail())
              .roleName(user.getRole())
              .build();

      return ResponseEntity.status(HttpStatus.OK)
          .body(
              AuthenticationResponse.builder().message("Login succeeded").userDto(userDto).build());
    } catch (Exception exception) {

      // Log any exception that occurs
      System.out.println("Login failed: " + exception.getMessage());

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(AuthenticationResponse.builder().error("Invalid identifier or password").build());
    }
  }
}
