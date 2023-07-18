package com.example.RMSoftProject.Domain.User;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> signUp(@RequestBody UserSignupDto userSignupDTO) {
        log.info("회원가입");
        userService.signUp(userSignupDTO);
        boolean isEmailExists = userService.checkIfEmailExists(userSignupDTO.getEmail());
        if (isEmailExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 아이디 입니다.");
        }
        userService.signUp(userSignupDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원 가입에 성공했습니다.");
    }




}
