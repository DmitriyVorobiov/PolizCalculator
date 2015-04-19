

public class Utils {
// TODO replace , to .
	protected static String getValidForm(String s) {
		String in = s.replaceAll("\\s+","");
		StringBuilder out = new StringBuilder("");
		for (int i = 0; i < in.length(); i++) {
			if (in.charAt(i) == '-' && !Character.isDigit(in.charAt(i-1))) {
				out.append("0-");
			} else {
				out.append(in.charAt(i));
			}
		}
		return out.toString();
	}
	
	
}
