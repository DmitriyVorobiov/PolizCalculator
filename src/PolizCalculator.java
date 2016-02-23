import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InputException;

public class PolizCalculator {

	static Stack<String> splitForExpressonsArray(String input) throws InputException {
		Stack<String> lst = new Stack<String>();
		String in = Utils.getValidForm(input);
		Matcher m = Pattern.compile("\\d+(?:\\.\\d+)?|[a-zA-Z]+|[^\\s\\w\\.]").matcher(in);
		while (m.find()) {
			lst.add(m.group());
		}
		return lst;
	}

	protected static Stack<String> convertToPolish(String in) throws InputException {
		Stack<String> inputString = new Stack<String>();
		Stack<String> outputString = new Stack<String>();
		Stack<String> store = new Stack<String>();
		inputString.addAll(reverse(splitForExpressonsArray(in)));
		while (!inputString.empty()) {
			if (ExpressionElement.isNumber(inputString.peek()) || ExpressionElement.isUnarOperator(inputString.peek())) {
				outputString.push(inputString.pop());
			} else if (ExpressionElement.isBinaryOperator(inputString.peek())) {
				if (store.isEmpty()) {
					store.push(inputString.pop());
				} else if (ExpressionElement.getComparPriority(inputString.peek()) > ExpressionElement.getStorePriority(store.peek())) {
					store.push(inputString.pop());
				} else {
					outputString.push(store.pop());
				}
			} else if (ExpressionElement.isBracket(inputString.peek())) {
				if (inputString.peek().equals("(")) {
					store.push(inputString.pop());
				} else {
					while (!store.peek().equals("(")) {
						outputString.push(store.pop());
					}
					store.pop();
					inputString.pop();
				}
			}
		}
		while (!store.isEmpty()) {
			outputString.push(store.pop());
		}
		return outputString;
	}

	public static double interpretation(String in) throws InputException {
		Stack<String> poliz = reverse(convertToPolish(in));
		Stack<Double> out = new Stack<Double>();
		while (!poliz.empty()) {
			if (ExpressionElement.isNumber(poliz.peek())) {
				out.push(Double.valueOf(poliz.pop()));
			} else if (ExpressionElement.isUnarOperator(poliz.peek())) {
				Double dig = out.pop();
				out.push(ExpressionElement.calcUnOp(dig, poliz.pop()));
			} else if (ExpressionElement.isBinaryOperator(poliz.peek())) {
				char operator = poliz.pop().charAt(0);
				Double dig = out.pop();
				Double dig2 = out.pop();
				out.push(ExpressionElement.calcBinOp(dig2, dig, operator));
			}
		}
		return out.pop();
	}

	private static Stack<String> reverse(Stack<String> split) {
		Stack<String> result = new Stack<String>();
		while (!split.isEmpty()) {
			result.push(split.pop());
		}
		return result;
	}
}
