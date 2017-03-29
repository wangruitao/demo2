package org.template.com.mapper;

import java.util.List;

import org.template.com.model.User;

public interface UserMapper {

	List<User> findByUserParam(User user);

	Long insert(User user);

	int update(User user);

	int delete(Long id);


}
