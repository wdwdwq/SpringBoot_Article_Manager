package com.koreaIT.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultData<DT> {
	private String resultCode;
	private String msg;
	private DT data;
	
	public static <DT> ResultData<DT> from(String resultCode, String msg) {
		return from(resultCode, msg, null);
	}
	
	public static <DT> ResultData<DT> from(String resultCode, String msg, DT data) {
		return new ResultData<>(resultCode, msg, data);
	}
	
	public boolean isSuccess() {
		return this.resultCode.startsWith("S-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
}
