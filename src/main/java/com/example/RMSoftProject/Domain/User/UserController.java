package com.example.RMSoftProject.Domain.User;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public void signUp(@RequestBody UserSignupDto userSignupDTO) {
        userService.signUp(userSignupDTO);
    }




}
