package com.project.ecommerce.auth;

// import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.ecommerce.config.JwtService;
import com.project.ecommerce.exception.UserAlreadyExistsExcemption;
import com.project.ecommerce.token.Token;
import com.project.ecommerce.token.TokenRepository;
import com.project.ecommerce.token.TokenType;
import com.project.ecommerce.user.User;
import com.project.ecommerce.user.UserRepository;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
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

    var jwtToken = jwtService.generateToken(user);
    revokeAllUserTokens(user);
    User savedUser = repository.save(user);
    saveUserToken(savedUser, jwtToken);

    return AuthenticationResponse.builder().token(jwtToken).build();
  }

  @SuppressWarnings("null")
  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
        .user(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .revoked(false)
        .expired(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());

    if (validUserTokens.isEmpty())
      return;

    validUserTokens.forEach(t -> {
      t.setExpired(true);
      t.setRevoked(true);
    });

    tokenRepository.saveAll(validUserTokens);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getEmail(),
        request.getPassword()));

    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    var jwtToken = jwtService.generateToken(user);

    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }

}
