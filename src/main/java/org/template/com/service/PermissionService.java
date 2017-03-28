package org.template.com.service;

import java.util.Set;

import org.template.com.model.Permission;

public interface PermissionService {

	Set<Permission> loadPermissionsByRolesId(String roleids);
	Set<String> loadStrPermissionsByRolesId(String roleids);

}
