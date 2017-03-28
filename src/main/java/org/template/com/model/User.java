package org.template.com.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseModel {

	private Long id;
	private String userName;
	private String loginName;
	private String password;
	private String salt;
	private Integer locked;
	private Integer disabled;
	
	private List<Role> roles = new ArrayList<Role>();
	


}