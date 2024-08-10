package com.jsp.empoye_management.exception;
public class IdNotFound  extends RuntimeException{
	String msg="id not found";

	

	public IdNotFound(String msg) {
		super();
		this.msg = msg;
	}



	public IdNotFound() {
		super();
	}



	public String getMessage() {
		return msg;
	}

	
}
