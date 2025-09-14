package br.com.monee.api.controller;

import br.com.monee.api.controller.mapper.UserRequestMapper;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.UserLoginDTO;
import br.com.monee.api.entity.dto.UserRequestDTO;
import br.com.monee.api.service.TokenService;
import br.com.monee.api.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService
            , UserRequestMapper userRequestMapper, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRequestMapper = userRequestMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequestDTO dto){
        UserEntity userEntity = this.userRequestMapper.toEntity(dto);
        String encryptedPassword = new BCryptPasswordEncoder().encode(userEntity.getPassword());
        userEntity.setPassword(encryptedPassword);
        this.userService.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO dto, HttpServletResponse response){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authenticate = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((UserEntity) authenticate.getPrincipal());
        this.tokenService.registerTokenInCookie(token, response);
        return  ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse httpServletResponse){
        this.tokenService.logout(httpServletResponse);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify-auth")
    public ResponseEntity<?> verifyLogin(Authentication authentication){
        if(authentication == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserData(Authentication authentication){
        if(authentication == null) {
            return ResponseEntity.status((HttpStatus.UNAUTHORIZED)).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                this.userService.getUserDefaultData(
                        (UserEntity) authentication.getPrincipal()
                )
        );
    }

}
