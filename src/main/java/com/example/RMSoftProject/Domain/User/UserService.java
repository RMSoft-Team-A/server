package com.example.RMSoftProject.Domain.User;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    public void signUp(UserSignupDto userSignupDTO) {
        User user = new User();
        user.setEmail(userSignupDTO.getEmail());
        user.setPassword(userSignupDTO.getPassword());


        userRepository.save(user);
    }
}
