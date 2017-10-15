package com.cds.groovy.dao;

import java.util.List;

import com.cds.groovy.domain.GroovyUser;

public interface UsersDaoIF {

	List<GroovyUser> getUsers()
	int insertUser(String name, String email)
	int insertUser(GroovyUser user)
	void deleteUsers()
	void deleteUser(Integer userId)
	GroovyUser findUser(Integer userId)
}