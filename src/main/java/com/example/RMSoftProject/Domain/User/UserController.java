package com.example.RMSoftProject.Domain.User;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{email}")
    public ResponseEntity<Object> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("Error", "사용자를 찾을 수 없습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // UserDto로 변환 또는 필요한 응답 형태로 데이터 가공

        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginDto userLoginDto) {
        log.info("로그인");
        User user = userService.login(userLoginDto);
        if (user == null) {
            Map<String, String> response = new HashMap<>();
            response.put("Error", "로그인에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // 로그인 성공 처리

        Map<String, String> response = new HashMap<>();
        response.put("Success", "로그인에 성공했습니다.");
        return ResponseEntity.ok(response);
    }





}
