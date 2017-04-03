package dao.armurerie.exception;

import armurerie.Exception.ExceptionMessageErreurOlivB;

public class DaoOlivBException extends Exception{


	private static final long serialVersionUID = 1L;
	
	public DaoOlivBException(String message) {
		super(message);	
	}

	public DaoOlivBException(ExceptionMessageErreurOlivB message){
		super(message.getMessage());
	}

}
