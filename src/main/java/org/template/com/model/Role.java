package org.template.com.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

@Data
@Table(name="t_role")
@EqualsAndHashCode(callSuper=false)
@NameStyle(Style.camelhumpAndLowercase)
public class Role  extends BaseModel implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	@Column(name="role_code")
	private String roleCode;
	@Column(name="role_name")
	private String roleName;
	@Column(name="disabled")
	private String disabled;
	@Column(name="remarker")
	private String remarker;
	
	@Transient
	private List<Permission> permissions;
}
