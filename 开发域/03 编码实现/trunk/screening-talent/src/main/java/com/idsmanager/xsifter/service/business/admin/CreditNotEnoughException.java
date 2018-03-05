package com.idsmanager.xsifter.service.business.admin;

public class CreditNotEnoughException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 926943136710892555L;
	
	
	public CreditNotEnoughException(String msg){
		super(msg);
	}
	
}
