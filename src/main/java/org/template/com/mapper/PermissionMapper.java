package org.template.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.template.com.model.Permission;

public interface PermissionMapper {

	List<Permission> loadPermissionsByRolesId(@Param(value="roleids")String roleids);

}
