import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionElement {

	protected static boolean isBinaryOperator(String s) {
		List<String> operators = Arrays.asList("+", "-", "*", "/");
		return operators.contains(s);
	}

	protected static boolean isBracket(String s) {
		List<String> brackets = Arrays.asList("(", ")");
		return brackets.contains(s);
	}

	static boolean isUnarOperator(String s) {
		List<String> operators = Arrays.asList("sin", "cos", "tan", "ctg");
		return operators.contains(s);
	}

	static int getComparPriority(String s) {
		switch (s) {
		case "(":
			return 100;
		case ")":
			return 0;
		case "+":
			return 2;
		case "-":
			return 2;
		case "*":
			return 3;
		case "/":
			return 3;
		case "sin":
			return 4;
		case "cos":
			return 4;
		case "tan":
			return 4;
		case "ctg":
			return 4;
		default:
			return 0;
		}
	}

	static int getStorePriority(String s) {
		switch (s) {
		case "(":
			return 0;
		case "+":
			return 2;
		case "-":
			return 2;
		case "*":
			return 3;
		case "sin":
			return 4;
		case "cos":
			return 4;
		case "tan":
			return 4;
		case "ctg":
			return 4;
		case "/":
			return 3;
		default:
			return 0;
		}
	}

	static double calcUnOp(double x, String unOp) {
		switch (unOp) {
		case "sin":
			return Math.sin(x);
		case "cos":
			return Math.cos(x);
		case "tan":
			return Math.tan(x);
		case "ctg":
			return Math.tanh(x);
		default:
			return 0;
		}
	}

	static double calcBinOp(double x, double y, char op) {
		switch (op) {
		case '+':
			return x + y;
		case '-':
			return x - y;
		case '*':
			return x * y;
		case '/':
			return x / y;
		default:
			return 0;
		}
	}

	public static boolean isNumber(String peek) {
		Pattern p = Pattern.compile("[0-9]*\\.?[0-9]");
		Matcher m = p.matcher(peek);
		return m.matches();
	}
}
