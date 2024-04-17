package com.example.controledeprodutos.view.controller;

import com.example.controledeprodutos.infra.security.TokenService;
import com.example.controledeprodutos.model.Users.User;
import com.example.controledeprodutos.model.repository.UserRepository;
import com.example.controledeprodutos.shared.AuthenticationDTO;
import com.example.controledeprodutos.shared.LoginResponseDTO;
import com.example.controledeprodutos.shared.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {
        var usarnamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usarnamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(this.userRepository.findByLogin(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        LocalDate createdDate = LocalDate.now();
        User user = new User(registerDTO.login(), encryptedPassword,registerDTO.first_name(), registerDTO.last_name(), registerDTO.role(), createdDate);

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
