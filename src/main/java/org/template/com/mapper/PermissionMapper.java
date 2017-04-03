package org.template.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.template.com.common.mybatis.MyMapper;
import org.template.com.model.Permission;

public interface PermissionMapper  extends MyMapper<Permission>{

	@Select("select p.id, p.permission_name permissionName, p.permission_code permissionCode, p.permission_url permissionUrl, p.disabled, p.create_time createTime, p.update_time updateTime "
			+ " from t_permission p inner join t_role_permission rp on p.id=rp.permission_id inner join t_role r on rp.role_id=r.id "
			+ " where r.id in (${roleids}) "
			+ " and r.disabled=0 and p.disabled=0")
	List<Permission> loadPermissionsByRolesId(@Param("roleids")String roleids);

}
