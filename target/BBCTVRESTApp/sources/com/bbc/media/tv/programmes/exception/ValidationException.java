package com.bbc.media.tv.programmes.exception;

public class ValidationException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -688642418405032466L;
	private String errMsg;
	
	public ValidationException(){
		super();
	}
	
	public ValidationException(String eMsg){
		super(eMsg);
		this.errMsg=eMsg;
		
	}

	public String getErrMsg() {
		return errMsg;
	}
	
	

}
