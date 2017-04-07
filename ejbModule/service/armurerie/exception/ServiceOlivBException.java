package service.armurerie.exception;

import util.armurerie.ExceptionMessageErreurOlivB;

public class ServiceOlivBException extends Exception{


	private static final long serialVersionUID = 1L;

	public ServiceOlivBException(String message) {
		super(message);	
	}

	public ServiceOlivBException(ExceptionMessageErreurOlivB message){
		super(message.getMessage());
	}
}
