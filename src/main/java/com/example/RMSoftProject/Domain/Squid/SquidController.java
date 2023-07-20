package com.example.RMSoftProject.Domain.Squid;

import com.example.RMSoftProject.Domain.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/squids")
@RequiredArgsConstructor
@Slf4j
public class SquidController {

    private final UserService userService;
    private final SquidService squidService;


    @PostMapping("/{email}/squid")
    public ResponseEntity<Object> createSquid(@PathVariable String  userId, @RequestBody SquidnameDto squidnameDto) {
        try {
            squidService.createSquidNameAndInitializeLevel(userId, squidnameDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("문어 생성 및 초기화에 성공했습니다.");
        } catch (NoSuchElementException e) {
            Map<String, String> response;
            response = new HashMap<>();
            response.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


    @PutMapping("/{email}/squid")
    public ResponseEntity<Object> updateSquidName(@PathVariable String email, @RequestBody SquidnameDto squidnameDto) {
        try {
            squidService.updateSquidName(email, squidnameDto);
            return ResponseEntity.ok("문어 이름 변경에 성공했습니다.");
        } catch (NoSuchElementException e) {
            Map<String, String> response = new HashMap<>();
            response.put("Error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
