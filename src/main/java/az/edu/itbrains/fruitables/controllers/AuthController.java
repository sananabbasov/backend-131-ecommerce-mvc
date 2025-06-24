package az.edu.itbrains.fruitables.controllers;

import az.edu.itbrains.fruitables.dtos.auth.RegisterDto;
import az.edu.itbrains.fruitables.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){

        return "auth/login.html";
    }



    @GetMapping("/register")
    public String register(){
        return "auth/register.html";
    }


    @PostMapping("/register")
    public String register(RegisterDto registerDto){
        boolean result = userService.registerUser(registerDto);
        if (result){
            return "redirect:/login";
        }
        return "auth/register.html";
    }

    @GetMapping("/forgot")
    public String forgot(){

        return "auth/forgot.html";
    }
}
