package com.examServer;

import com.examServer.entity.Role;
import com.examServer.entity.User;
import com.examServer.entity.UserRole;
import com.examServer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner {
	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting code");
//		User user = new User();
//		user.setFirstName("Mohammad");
//		user.setLastName("Shohag");
//		user.setUserName("shohag07");
//		user.setAbout("about me");
//		user.setPassword("abc123");
//		user.setEmail("shohag@gmail.com");
//		user.setImage("default.png");
//
//		Role role1 = new Role();
//		role1.setId(44L);
//		role1.setRoleName("ADMIN");
//
//		UserRole userRole = new UserRole();
//		userRole.setRole(role1);
//		userRole.setUser(user);
//
//		Set<UserRole> userRoleSet = new HashSet<>();
//		userRoleSet.add(userRole);
//
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1);
	}
}
