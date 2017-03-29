package org.template.com.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.template.com.model.User;


@Service
public interface UserService  {

	User loadUserByLoginName(String userName);

	List<User> findAll();

	User save(User user);

	User findByUserId(Long id);

	boolean update(User user);

	boolean delete(Long id);

	
}
