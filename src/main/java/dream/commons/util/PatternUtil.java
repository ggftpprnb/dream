package dream.commons.util;

import java.util.regex.Pattern;

/**
 * 
 * ClassName：PatternUtil
 * Description: 常用的正则表达式写在此，应用启动的时候加载且只加载一次正则表达式，
 * 				不建议使用以下方式 ：boolean isRightName = "朱".maches("^[\\p{Punct}A-Za-z0-9\u4e00-\u9fa5]+$");
 * 				此种方式，每次运行String.maches方法时，都需要对正则表达式进行编译，效率会很低
 * 				建议使用以下方式：public static final Pattern namePattern = Pattern.compile("^[\\p{Punct}A-Za-z0-9\u4e00-\u9fa5]+$");
 * 				boolean isRightNanme = namePattern.matcher("朱").maches();
 * 				
 * @author zhujian
 * @param args 
 * @date 2014年5月30日 上午11:25:20
 * @version
 */
public final class PatternUtil {
	
	//英文字母正则表达式 
	public static final Pattern ALPHA = Pattern.compile("^[\\p{Alpha}]+$");
	
	//数值正则表达式，包含正负数，小数。
	public static final Pattern NUMBER = Pattern.compile("^-?\\d+.?\\d*$");
	
	//无符号整数正则表达式
	public static final Pattern UNSIGNED_INTEGER = Pattern.compile("^[1-9]\\d*$");
	
	/**
	 * 
	 * @Title: isMaches
	 * @Description: 判断传入的内容是否与Pattern完全匹配，若匹配，则返回TRUE。
	 * @param text			需要判断的内容
	 * @param pattern		正则表达类
	 * @return
	 * @throws
	 */
	public static boolean isMaches(String text,Pattern pattern){
		return pattern.matcher(text).matches();
	}
	
	/**
	 * 
	 * @Title: find
	 * @Description: 判断传入的内容，是否含有Pattern:如，text:习近习主席，pattern:习近平。那么，此方法返回true
	 * @param text			需要判断的内容
	 * @param pattern		正则表达类
	 * @return
	 * @throws
	 */
	public static boolean find(String text,Pattern pattern){
		return pattern.matcher(text).find();
	}
}
