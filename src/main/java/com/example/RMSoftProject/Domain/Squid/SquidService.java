package com.example.RMSoftProject.Domain.Squid;

import com.example.RMSoftProject.Domain.User.User;
import com.example.RMSoftProject.Domain.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SquidService {
    private final UserRepository userRepository;
    private final SquidRepository squidRepository;
    public void createSquidNameAndInitializeLevel(String email, SquidnameDto squidnameDto) {
        User user = userRepository.findByEmail(email);


        Squid squid = new Squid();
        squid.setSquidname(squidnameDto.getSquidname());
        squid.setLevel(1);
        squid.setUser(user);

        user.setSquid(squid);

        userRepository.save(user);
        squidRepository.save(squid);
    }

    public void updateSquidName(String email, SquidnameDto squidnameDto) {
        User user = userRepository.findByEmail(email);
        Squid squid = user.getSquid();

        if (squid != null) {
            squid.setSquidname(squidnameDto.getSquidname());
            squidRepository.save(squid);
        } else {
            throw new NoSuchElementException("문어가 존재하지 않습니다.");
        }
    }

}
