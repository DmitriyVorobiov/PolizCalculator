import java.util.Stack;


public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Stack<String> out = null;
		String sss = "";
		// TODO Auto-generated method stub
		Performer perf = new Performer();
		try {
			out = perf.split("(1+1)*3");
		} catch (InputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!out.empty()){
			sss+=out.pop();
		}

		System.out.println(sss);
	}

}
