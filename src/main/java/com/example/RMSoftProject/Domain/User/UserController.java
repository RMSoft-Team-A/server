package com.example.RMSoftProject.Domain.User;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody UserSignupDto userSignupDTO) {
        log.info("회원가입");
        boolean isEmailExists = userService.checkIfEmailExists(userSignupDTO.getEmail());
        if (isEmailExists) {
            Map<String, String> response = new HashMap<>();
            response.put("Warning", "중복된 아이디 입니다.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        userService.signUp(userSignupDTO);

        Map<String, String> response = new HashMap<>();
        response.put("Success", "회원 가입에 성공했습니다.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



}
