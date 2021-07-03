package de.christianporsch.backend.controller;

import de.christianporsch.backend.controller.dto.LoginDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public String login(@RequestBody LoginDataDto loginDataDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDataDto.getUsername(), loginDataDto.getPassword()));
        return "some jwt";
    }
}
