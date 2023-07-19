package com.example.RMSoftProject.Domain.User;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
