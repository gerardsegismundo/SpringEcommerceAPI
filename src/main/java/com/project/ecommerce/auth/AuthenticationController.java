package com.project.ecommerce.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @GetMapping
  public String test() {
    System.out.println("TESTS!");
    return "TEST";
  }

  @GetMapping("/authorize")
  public String authorize() {
    return "Authorized!";
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request) {
    System.out.println(request);
    return ResponseEntity.ok(service.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request) {

    return ResponseEntity.ok(service.authenticate(request));
  }

  // @PostMapping("/refresh-token")
  // public void refreshToken(
  // HttpServletRequest request,
  // HttpServletResponse response) throws IOException {
  // service.refreshToken(request, response);
  // }
}
