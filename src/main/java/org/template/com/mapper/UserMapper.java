package org.template.com.mapper;

import java.util.List;

import org.template.com.model.User;

public interface UserMapper {

	List<User> findByUserParam(User user);

	User insert(User user);


}
