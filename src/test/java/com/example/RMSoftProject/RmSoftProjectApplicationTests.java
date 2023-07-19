package com.example.RMSoftProject;

import com.example.RMSoftProject.Domain.User.User;
import com.example.RMSoftProject.Domain.User.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Transactional
@SpringBootTest
class RmSoftProjectApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	@Rollback(true)
	void 회원가입() {

		//given

		User user = new User();
		user.setEmail("hello");
		user.setPassword("hello1");


		//when




		//then


	}

}
