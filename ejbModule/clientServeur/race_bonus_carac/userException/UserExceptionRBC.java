package clientServeur.race_bonus_carac.userException;

public class UserExceptionRBC extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserExceptionRBC(String msg) {
		super(msg);
	}

	public UserExceptionRBC(UserExceptionRBCMsg msg) {
		super(msg.getMsg());
	}
	
}
