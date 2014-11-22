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
		String input = "2*(1+4)/2";
		double res = 0;
		// TODO Auto-generated method stub
		Performer perf = new Performer();
		try {
			out = perf.split(input);
			res = perf.interpretation(input);
		} catch (InputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!out.empty()) {
			sss += out.pop();
		}
		 sss = String.valueOf(res);
		System.out.println(sss);

	}

}
