package com.example.RMSoftProject.Domain.User;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public void signUp(@RequestBody UserSignupDto userSignupDTO) {
        log.info("회원가입");
        userService.signUp(userSignupDTO);
    }




}
