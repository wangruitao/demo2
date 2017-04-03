package org.template.com.common.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.template.com.common.enums.CommonDisabledEnums;
import org.template.com.model.Role;
import org.template.com.model.User;
import org.template.com.service.PermissionService;
import org.template.com.service.RoleService;
import org.template.com.service.UserService;

@Component
public class UserRealm extends AuthorizingRealm {

	@ Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SimpleAuthenticationInfo simpleAuthenticationInfo = null;
		String userName = (String)token.getPrincipal();
		User user = userService.loadUserByLoginName(userName);
		
		if(user == null) {
			throw new UnknownAccountException();//没找到帐号 
		}
		
		if(user.getDisabled().equals(CommonDisabledEnums.DISABLE_TRUE.getFlag())) {
			throw new DisabledAccountException(); //帐号锁定 
		}
		
		simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getLoginName(), user.getPassword(), getName());
		simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		return simpleAuthenticationInfo;
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
		String userName = (String)principals.getPrimaryPrincipal();
		Set<Role> roles = roleService.loadRolesByLoginName(userName);
		authorization.setRoles(getStrRoles(roles));
		String roleids = getRolesId(roles);
		if(!StringUtils.isEmpty(roleids)) {
			Set<String> permissionsStr = permissionService.loadStrPermissionsByRolesId(roleids);
			authorization.setStringPermissions(permissionsStr);
		}
		return authorization;
	}

	private Set<String> getStrRoles(Set<Role> roles) {
		Set<String> roleSet = new HashSet<String>();
		if(roles != null && roles.size() > 0) {
			for(Role role : roles) {
				roleSet.add(role.getRoleCode());
			}
		}
		
		return roleSet;
	}

	private String getRolesId(Set<Role> roles) {
		StringBuffer sb = new StringBuffer();
		if(roles != null && roles.size() > 0) {
			for(Role r : roles) {
				sb.append(r.getId()).append(",");
			}
			sb.setLength(sb.length() - 1);
		}
		
		return sb.toString();
	}
}
