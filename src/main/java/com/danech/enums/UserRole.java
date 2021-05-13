package com.danech.enums;

import lombok.Getter;

/**
 * 
 * Enum defined User roles
 *
 */
@Getter
public enum UserRole {
	ROLE_ADMIN("ROLE_ADMIN"), ROLE_AGENT("ROLE_AGENT"),ROLE_MANAGER("ROLE_MANAGER");	

	private String type;
	
	UserRole(String type){
		this.type = type;
	}
}