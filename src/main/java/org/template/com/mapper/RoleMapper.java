package org.template.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.template.com.common.mybatis.MyMapper;
import org.template.com.model.Role;

public interface RoleMapper  extends MyMapper<Role>{

	public String column = "id, role_name, role_code, remarker, disabled, create_time, update_time";
	
	@Select("select r.id, r.role_name roleName, r.role_code roleCode, r.remarker, r.disabled, r.create_time createTime, "
			+ " r.update_time updateTime FROM t_role r inner join t_user_role ru on r.id=ru.role_id  "
			+ "inner join t_user u on ru.user_id = u.id where u.login_name=#{userName} and r.disabled=0")
//	@SelectProvider(type=org.template.com.mapper.RoleProvider.class, method="findByUserLoginName")
	List<Role> findByUserLoginName(@Param("userName") String userName);

}
