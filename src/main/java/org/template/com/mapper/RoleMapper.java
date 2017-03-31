package org.template.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.template.com.common.mybatis.MyMapper;
import org.template.com.model.Role;

public interface RoleMapper  extends MyMapper<Role>{

	public String column = "id, role_name, role_code, remarker, disabled, create_time, update_time";
	
	@Select("r.id, r.role_name, r.role_code, r.remarker, r.disabled, r.create_time, "
			+ " r.update_time FROM t_role r inner join t_user_role ru on r.id=ru.role_id  "
			+ "inner join t_user u on ru.user_id = u.id where u.login_name=#{username} and r.disabled=0")
	List<Role> findByUserLoginName(@Param("userName") String userName);

}
