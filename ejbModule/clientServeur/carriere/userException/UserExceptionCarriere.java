package clientServeur.carriere.userException;

import javax.persistence.Version;

import service.ressources.carriere.Erreur;


//import ressources.Erreur;

/**
 * Classe mere pour les message d'erreurs
 * @author Ismael
 *
 */
public class UserExceptionCarriere extends Exception
{
	@Version
	private static final long serialVersionUID = 1L;
	
	private int codeMess;

	public UserExceptionCarriere()
	{
		super();
	}

	public UserExceptionCarriere(int codeMess)
	{
		super();
		this.codeMess = codeMess;
	}
	
	public UserExceptionCarriere(String message, Throwable cause, int codeMess) 
	{
		super(message, cause);
		this.codeMess = codeMess;
	}

	public UserExceptionCarriere(String message, int codeMess) 
	{
		super(message);
		this.codeMess = codeMess; 
	}

	public UserExceptionCarriere(Throwable cause) 
	{
		super(cause);
	}

	public UserExceptionCarriere(Erreur msg) 
	{
		super(msg.getMsg());
	}

	public int getCodeMess() 
	{
		return codeMess;
	}

	public void setCodeMess(int codeMess)
	{
		this.codeMess = codeMess;
	}
	
	public String getMessage()
	{
		return super.getMessage();
	}
	
}
