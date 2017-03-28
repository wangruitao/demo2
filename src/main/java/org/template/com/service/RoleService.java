package org.template.com.service;

import java.util.Set;

import org.template.com.model.Role;

public interface RoleService {

	Set<String> loadStrRolesByLoginName(String userName);

	Set<Role> loadRolesByLoginName(String userName);
	
}
