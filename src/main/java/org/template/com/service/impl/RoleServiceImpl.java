package org.template.com.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.template.com.common.mybatis.SqlMapper;
import org.template.com.mapper.RoleMapper;
import org.template.com.model.Role;
import org.template.com.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private SqlMapper sqlMapper;
	
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
		
		String sql = "select r.id, r.role_name roleName , r.role_code roleCode, r.remarker, r.disabled, r.create_time createTime , "
				+ " r.update_time update_time  FROM t_role r inner join t_user_role ru on r.id=ru.role_id  "
				+ "inner join t_user u on ru.user_id = u.id where u.login_name=#{userName} and r.disabled=0";
		List<Role> roles = sqlMapper.selectList(sql, userName, Role.class);
//		List<Role> roles = roleMapper.findByUserLoginName(userName);
		Set<Role> roleSet = new HashSet<Role>();
		roleSet.addAll(roles);
		return roleSet;
	}
	
	/*@Override
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
	}*/

}
