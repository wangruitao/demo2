package org.template.com.model;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Role extends BaseModel{

	private Long id;
	private String roleCode;
	private String roleName;
	private String disabled;
	private String roleRemark;
	
	private List<Permission> permissions;
}
