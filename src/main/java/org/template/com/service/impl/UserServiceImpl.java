package org.template.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.template.com.mapper.RoleMapper;
import org.template.com.mapper.UserMapper;
import org.template.com.model.User;
import org.template.com.model.enums.CommonDisabled;
import org.template.com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public User loadUserByLoginName(String userName) {
		
		User user = new User();
		user.setLoginName(userName);
		List<User> users = userMapper.findByUserParam(user);
		if(users != null && users.size() == 1) {
			user = users.get(0);
		}
		
		return user;
	}

	@Override
	public List<User> findAll() {
		
		User user = new User();
		user.setDisabled(CommonDisabled.DISABLE_FALSE.getFlag());
		return userMapper.findByUserParam(user);
	}

	@Override
	public User save(User user) {
		
		return userMapper.insert(user);
	}

}
