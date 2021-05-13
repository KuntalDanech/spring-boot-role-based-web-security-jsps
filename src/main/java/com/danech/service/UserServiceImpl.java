package com.danech.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.danech.model.User;
import com.danech.repository.UserRepository;

/**
 * 
 * implements IUserService, UserDetailsService
 * 
 *loadUserByUsername - loads Spring Security User with roles otherwiser it will
 *throw UsernameNotFoundException
 */
@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
	@Override
	public User insertUser(User user) {
		return userRepository.save(user);
	}
	
	/**
	 * Spring Security User details with Roles
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if(user!=null) {
			return new org.springframework.security.core.userdetails.User(
					user.getUserName(), 
					user.getPassword(), 
					user.getRoles()
					.stream()
					.map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
		}else {
			throw new UsernameNotFoundException("User does not exist");
		}
	}
}