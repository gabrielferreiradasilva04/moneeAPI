package br.com.monee.api.controller;

import br.com.monee.api.repository.UserRepository;
import br.com.monee.api.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private TokenService tokenService;


}
