package dao.carriere.exception;


public class DaoExceptionCarriere extends Exception 
{

private static final long serialVersionUID = 1L;
	
	private int codeMess;

	public DaoExceptionCarriere()
	{
		super();
	}

	public DaoExceptionCarriere(int codeMess)
	{
		super();
		this.codeMess = codeMess;
	}
	
	public DaoExceptionCarriere(String message, Throwable cause, int codeMess) 
	{
		super(message, cause);
		this.codeMess = codeMess;
	}

	public DaoExceptionCarriere(String message, int codeMess) 
	{
		super(message);
		this.codeMess = codeMess; 
	}

	public DaoExceptionCarriere(Throwable cause) 
	{
		super(cause);
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
