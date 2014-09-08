
@SuppressWarnings("serial")
public class InputException extends Exception {

	public int index;
	public InputException() {
		// TODO Auto-generated constructor stub
	}

	public InputException(String mes, int ind) {
		index = ind;
	}

	public InputException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public InputException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public InputException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
