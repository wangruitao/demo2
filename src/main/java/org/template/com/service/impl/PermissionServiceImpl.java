package org.template.com.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.template.com.mapper.PermissionMapper;
import org.template.com.model.Permission;
import org.template.com.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Override
	public Set<Permission> loadPermissionsByRolesId(String roleids) {
		List<Permission> permissions = permissionMapper.loadPermissionsByRolesId(roleids);
		Set<Permission> permissionSet = new HashSet<Permission>();
		permissionSet.addAll(permissions);
		return permissionSet;
	}

	@Override
	public Set<String> loadStrPermissionsByRolesId(String roleids) {
		Set<Permission> permissions = loadPermissionsByRolesId(roleids);
		Set<String> permissionSet = new HashSet<String>();
		if(permissions != null && permissions.size() > 0) {
			for(Permission p : permissions) {
				permissionSet.add(p.getPermissionCode());
			}
		}
		return permissionSet;
	}

}
