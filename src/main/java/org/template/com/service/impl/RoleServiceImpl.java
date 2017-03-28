package org.template.com.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.template.com.mapper.RoleMapper;
import org.template.com.model.Role;
import org.template.com.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Set<String> loadStrRolesByLoginName(String userName) {
		Set<Role> roleSet = loadRolesByLoginName(userName);
		Set<String> roles = new HashSet<String>();
		if(roleSet != null && roleSet.size() > 0) {
			for(Role r : roleSet) {
				roles.add(r.getRoleCode());
			}
		}
		return roles;
	}
	
	@Override
	public Set<Role> loadRolesByLoginName(String userName) {
		List<Role> roles = roleMapper.findByUserLoginName(userName);
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.addAll(roles);
		return roleSet;
	}

}
