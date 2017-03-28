package org.template.com.mapper;

import java.util.List;

import org.template.com.model.Permission;

public interface PermissionMapper {

	List<Permission> loadPermissionsByRolesId(String roleids);

}
