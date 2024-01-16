package com.koreaIT.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData {
	private String resultCode;
	private String msg;
	private Object data;
	
	public static ResultData from(String resultCode, String msg) {
		return from(resultCode,msg, null);
	}
	//메서드
	public static ResultData from(String resultCode, String msg, Object data) {
		return new ResultData(resultCode,msg,data);
	}
	
	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
		
	}
	
	public boolean isFail() {
		return isSuccess() == false;
		
	}
}
