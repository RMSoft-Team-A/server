package com.example.RMSoftProject.Domain.User;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public String signUp(UserSignupDto userSignupDTO) {
        User user = new User();
        user.setEmail(userSignupDTO.getEmail());
        user.setPassword(userSignupDTO.getPassword());


        return userRepository.save(user).getEmail();
    }
    public boolean checkIfEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User login(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        String password = userLoginDto.getPassword();

        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public void changeEmail(String currentEmail, String newEmail) {
        User user = userRepository.findByEmail(currentEmail);
        if (user == null) {
            throw new NoSuchElementException("해당 사용자를 찾을 수 없습니다.");
        }

        user.setEmail(newEmail);
        userRepository.save(user);
    }
}
