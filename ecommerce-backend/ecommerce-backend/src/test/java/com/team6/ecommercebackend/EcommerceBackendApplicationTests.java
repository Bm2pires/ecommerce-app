package com.team6.ecommercebackend;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.team6.ecommercebackend.entities.User;
import com.team6.ecommercebackend.repositries.UserRepository;

@SpringBootTest
class EcommerceBackendApplicationTests {
	
	@Autowired
	UserRepository userRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testAddUser() {
		LocalDate date =  LocalDate.of(2019, 2, 4);
		User user = new User("tesId@email.com", "testIDPass", "Mr", "testIDFname", "testLname", date, "1234567899", "testAddress", false);
		userRepo.save(user);
	}

}
