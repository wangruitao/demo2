package org.template.com.common.enums;

public enum UserLockedEnums {

	LOCKED_TRUE("锁定", 1),
	LOCKED_FALSE("未锁定", 0);
	
	private String description;
	private Integer flag;
	
	private UserLockedEnums(String description, Integer flag) {
		this.description = description;
		this.flag = flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public static String getDescription(Integer flag) {
		
		for(CommonDisabledEnums cd : CommonDisabledEnums.values()) {
			if(cd.getFlag().equals(flag)) {
				return cd.getDescription();
			}
		}
		return null;
	}
}
