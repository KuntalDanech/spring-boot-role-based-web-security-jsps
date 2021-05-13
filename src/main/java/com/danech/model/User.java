package com.danech.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user_tab")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NonNull
	private String userName;
	@NonNull
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles_tab", joinColumns = @JoinColumn(name="id"))
	@Column(name = "role")
	@NonNull
	private Set<String> roles;
	@NonNull
	private LocalDateTime createdAt;
}