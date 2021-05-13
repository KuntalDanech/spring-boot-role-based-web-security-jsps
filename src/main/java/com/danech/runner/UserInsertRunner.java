package com.danech.runner;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.danech.enums.UserRole;
import com.danech.model.User;
import com.danech.repository.UserRepository;

/**
 * Register the users through Runners.
 * 
 * @author dev77
 *
 */
@Component
public class UserInsertRunner implements CommandLineRunner {

	private static final Logger LOGGER = Logger.getLogger(UserInsertRunner.class.getName());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {

		String password = "password";
		
		Set<String> rolesFirst = new HashSet<>();
		rolesFirst.add(UserRole.ROLE_AGENT.getType());
		rolesFirst.add(UserRole.ROLE_ADMIN.getType());
		rolesFirst.add(UserRole.ROLE_MANAGER.getType());
		User newUserFirst = new User("kuntal",passwordEncoder.encode(password),rolesFirst, LocalDateTime.now());
		
		Set<String> rolesSecond = new HashSet<>();
		rolesSecond.add(UserRole.ROLE_AGENT.getType());
		rolesSecond.add(UserRole.ROLE_MANAGER.getType());
		User newUserSecond = new User("sumit",passwordEncoder.encode(password),rolesSecond, LocalDateTime.now());
		
		Set<String> rolesThird = new HashSet<>();
		rolesThird.add(UserRole.ROLE_AGENT.getType());
		User newUseThird = new User("shubham",passwordEncoder.encode(password),rolesThird, LocalDateTime.now());
		
		userRepository.save(newUserFirst);
		userRepository.save(newUserSecond);
		userRepository.save(newUseThird);
		
		LOGGER.info("Users have been inserted");
	}

}