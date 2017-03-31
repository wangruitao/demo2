package org.template.com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name="t_role")
@EqualsAndHashCode(callSuper=false)
public class Role  extends BaseModel implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	private String roleCode;
	private String roleName;
	private String disabled;
	private String roleRemark;
	
	@Transient
	private List<Permission> permissions;
}
