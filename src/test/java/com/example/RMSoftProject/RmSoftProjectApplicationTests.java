package com.example.RMSoftProject;

import com.example.RMSoftProject.Domain.User.User;
import com.example.RMSoftProject.Domain.User.UserRepository;
import com.example.RMSoftProject.Domain.User.UserService;
import com.example.RMSoftProject.Domain.User.UserSignupDto;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Transactional
@SpringBootTest
class RmSoftProjectApplicationTests {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

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
	@Test
	@Rollback(true)
	void search(){
		//given
		User user =
				User.builder()
						.email("donghun")
						.password("124").build();
		UserSignupDto userSignupDto = new UserSignupDto();
		userSignupDto.setEmail(user.getEmail());
		userSignupDto.setPassword(user.getPassword());
//when
		Assertions.assertThat(userService.signUp(userSignupDto)).isEqualTo("donghun");
		//then


	}

}
