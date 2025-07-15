package br.com.monee.api.controller;

import br.com.monee.api.controller.mapper.UserEntityLoginMapper;
import br.com.monee.api.controller.mapper.UserRequestMapper;
import br.com.monee.api.entity.UserEntity;
import br.com.monee.api.entity.dto.LoginResponseDTO;
import br.com.monee.api.entity.dto.UserEntityLoginDTO;
import br.com.monee.api.entity.dto.UserEntityRequestDTO;
import br.com.monee.api.repository.UserRepository;
import br.com.monee.api.service.TokenService;
import br.com.monee.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final UserRequestMapper userRequestMapper;
    private final UserEntityLoginMapper userEntityLoginMapper;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository,
                                    TokenService tokenService, UserRequestMapper userRequestMapper, UserEntityLoginMapper userEntityLoginMapper, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.userRequestMapper = userRequestMapper;
        this.userEntityLoginMapper = userEntityLoginMapper;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntityRequestDTO dto){
        UserEntity userEntity = this.userRequestMapper.toEntity(dto);
        String encryptedPassword = new BCryptPasswordEncoder().encode(userEntity.getPassword());
        userEntity.setPassword(encryptedPassword);
        this.userService.save(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserEntityLoginDTO dto){
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var authenticate = this.authenticationManager.authenticate(usernamePassword);
        var token = this.tokenService.generateToken((UserEntity) authenticate.getPrincipal());
        return  ResponseEntity.ok().body(new LoginResponseDTO(token));
    }

}
