import java.util.Stack;


public class Performer {

	public Performer() {
		// TODO Auto-generated constructor stub
	}

	private static boolean isBinOp(char c){
		switch(c)
		{
		case '+': return true;
		case '-': return true;
		case '*': return true;
		case '/': return true;
		default: return false;
		}
	}
	
	private static boolean isBkt(char c){
		switch(c)
		{
		case '(': return true;
		case ')': return true;
		default: return false;
		}
	}
	private static boolean isUnOp(String s){
		switch(s)
		{
		case "sin": return true;
		case "cos": return true;
		case "tan": return true;
		case "ctg": return true;
		default: return false;
		}
	}
	
	private static int getComparPriority(String s){
		switch(s){
		case "(": return 100;
		case ")": return 0;
		case "+": return 2;
		case "-": return 2;
		case "*": return 3;
		case "/": return 3;
		default: return 0;
		}
	}
	
	private static int getStorePriority(String s){
		switch(s){
		case "(": return 0;
		case "+": return 2;
		case "-": return 2;
		case "*": return 3;
		case "/": return 3;
		default: return 0;
		}
	}
	
	private static double calc(double x, String unOp){
		switch(unOp){
		case "sin": return Math.sin(x);
		case "cos": return Math.cos(x);
		case "tan": return Math.tan(x);
		case "ctg": return Math.tanh(x);
		default: return 0;
		}
	}
	
	static Stack<String> divide(String in) throws InputException {
		Stack<String> out = new Stack<String>();
		for (int i = 0; i<in.length(); ){
			if (Character.isDigit(in.charAt(i))){
				int st = i;

				while ((i!=in.length()-1)&&Character.isDigit(in.charAt(i+1))){
					i++;
				}
				out.push(in.substring(st, i+1));
				i++;
			} else 
			if (isBinOp(in.charAt(i))) {
				out.push(in.substring(i, i+1));
				i++;
			} else
			if (isBkt(in.charAt(i))) {
				out.push(in.substring(i, i+1));
				i++;
			} else
			if (isUnOp(in.substring(i, i+3))){
				out.push(in.substring(i, i+3));
				i+=3;
			} else throw new InputException("incorrect input", i);

		}
		Stack<String> inputStack = new Stack<String>();
		while(!out.empty())	{
			inputStack.push(out.pop());
		}
		return inputStack;
	}
	
	private  Stack<String> split(String in) throws InputException {     
		Stack<String> inputString = divide(in);
		Stack<String> outputString = new Stack<String>();
		Stack<String> store = new Stack<String>();
		while (!inputString.empty()) {
			if(Character.isDigit(inputString.peek().charAt(0))) {
				outputString.push(inputString.pop());
			} else
			if (isUnOp(inputString.peek())) {
				outputString.push(inputString.pop());
			} else
			if (isBinOp(inputString.peek().charAt(0))) {
				if (store.isEmpty()) {
					store.push(inputString.pop());
				} else
				if (getComparPriority(inputString.peek()) > getStorePriority(store.peek())) {
					store.push(inputString.pop());
				} else {
					outputString.push(store.pop());
				}
			} else 
				if (isBkt(inputString.peek().charAt(0))) {
					if (inputString.peek().equals("(")) {
						store.push(inputString.pop());
					} else {                                                // catch infinity here
						while(!store.peek().equals("(")) {
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
	
	public void interpretation(String in) throws InputException {
		Stack<String> poliz = split(in);
		Stack<Double> out = new Stack<Double>();
		while(!poliz.empty()){
			if(Character.isDigit(poliz.peek().charAt(0))) {
				out.push(Double.valueOf(poliz.pop()));
			} else if (isUnOp(poliz.peek())) {
				Double dig = out.pop();
				out.push(calc(dig, poliz.pop()));
			}
		}
	}
}
