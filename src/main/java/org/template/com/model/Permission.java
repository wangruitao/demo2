package org.template.com.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name="t_permission")
@EqualsAndHashCode(callSuper=false)
public class Permission extends BaseModel implements Serializable{

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "JDBC")
	private Long id;
	private String permissionName;
	private String permissionCode;
	private String permissionUrl;
	private Integer disabled;
	
}
