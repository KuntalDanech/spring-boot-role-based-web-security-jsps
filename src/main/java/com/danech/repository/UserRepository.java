package com.danech.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.danech.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserName(String userName);
}