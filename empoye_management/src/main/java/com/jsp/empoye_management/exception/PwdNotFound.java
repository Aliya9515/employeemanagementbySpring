package com.jsp.empoye_management.exception;

public class PwdNotFound extends RuntimeException{
	String msg="pwd is not found";

	public PwdNotFound() {
		super();
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMessage() {
		return msg;
	}
}
