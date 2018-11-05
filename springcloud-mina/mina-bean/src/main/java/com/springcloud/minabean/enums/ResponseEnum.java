package com.springcloud.minabean.enums;

/**
 * 响应码枚举类
 */
public enum ResponseEnum {
	SUCCESS("0", "成功"),
	FAIL("-1", "操作失败"),
	PATAM_ERROR("1", "请求参数不合法"),
	DATA_EMPTY("2", "数据为空"),


	;

	private String code;
	private String msg;

	private ResponseEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
