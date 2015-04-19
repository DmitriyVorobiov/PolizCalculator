import java.util.Stack;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Stack<String> out = null;
		String sss = "";
		String input = "4*(   - 1  +4)/2";
		double res = 0;
		try {
			out = PolizCalculator.convertToPolish(input);
			 res = PolizCalculator.interpretation(input);
		} catch (InputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!out.empty()) {
			sss += out.pop() + " ";
		}
		sss = String.valueOf(res);

		/**
		 * String ss = "2.1+(sin 2)"; String parts[] = ss.split("\\s+|(?=[^.\\w\\s])|(?<=[^.\\w\\s])"); System.out.println(Arrays.toString(parts));
		 */
		System.out.println(sss);

	}

}
