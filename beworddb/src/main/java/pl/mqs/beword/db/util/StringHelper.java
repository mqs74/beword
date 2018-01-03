package pl.mqs.beword.db.util;

public class StringHelper {
	public static final String WHITESPACE = "";
	public static final String NESTED_POINTER = "->";
	public static final String CLASS_OPEN_BRACKET = "[";
	public static final String CLASS_CLOSE_BRACKET = "]";
	
	public static String updatePrefix(String prefix) {
		if(prefix == null)
			return StringHelper.WHITESPACE;
		
		return prefix;
	}
}
