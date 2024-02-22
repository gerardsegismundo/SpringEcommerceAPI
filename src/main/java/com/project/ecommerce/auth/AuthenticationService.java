package com.project.ecommerce.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerce.config.JwtService;
import com.project.ecommerce.exception.UserAlreadyExistsExcemption;
import com.project.ecommerce.user.User;
import com.project.ecommerce.user.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @SuppressWarnings("null")
  public AuthenticationResponse register(RegisterRequest request) {

    if (repository.findByEmail(request.getEmail()).isPresent()) {
      throw new UserAlreadyExistsExcemption("User with email " + request.getEmail()
          + " already exists");
    }

    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole()).build();

    repository.save(user);

    var jwtToken = jwtService.generateToken(user);
    System.out.println("TOKEN: " + jwtToken);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    System.out.println(request);
    var toks = new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword());

    System.out.println("toks: " + toks);

    try {
      authenticationManager.authenticate(toks);
    } catch (AuthenticationException e) {
      e.printStackTrace();
      // Handle the exception as needed
    }

    System.out.println("AFTER TOKS?");

    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    System.out.println("USER: " + user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

}
