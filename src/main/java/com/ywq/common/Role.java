package com.ywq.common;

public enum Role {

	ADMIN("管理员", 0), ORDINARY("普通用户", 1), GUEST("游客", 2);
	private String desc;
	private Integer code;

	private Role(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}

	//  普通方法  
	public static String getDesc(int code) {
		for(Role role : Role.values()){
			if(role.getCode() == code) {
				return role.getDesc();	
				}
		}
		return null;
	}

	public String getDesc() {
		return desc;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	}
