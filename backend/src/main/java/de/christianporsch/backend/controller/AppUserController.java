package de.christianporsch.backend.controller;

import de.christianporsch.backend.controller.dto.UserResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
public class AppUserController {

    @GetMapping("me")
    public UserResponseDto getLoggedInUser(Principal principal){
        return new UserResponseDto(principal.getName());
    }

}
