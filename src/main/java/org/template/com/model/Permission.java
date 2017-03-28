package org.template.com.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Permission extends BaseModel{

	private Long id;
	private String permissionName;
	private String permissionCode;
	private String permissionUrl;
	private Integer disabled;
	
}
