package dream.browser.constant;

import dream.commons.util.SecretUtil;

public class MainConstant {

	/**
	 * 
	 * ClassName：SessionKey
	 * Description: 		session中的key
	 * @author zhujian
	 * @param args 
	 * @date 2015年1月13日 下午7:48:52
	 * @version
	 */
	public static final class SessionKey{
		
		public static final String LOGIN_SALT = "login_salt"; 	//session中的随机码的key
		
		public static final String LOGIN_USER = "login_user"; 	//session中的登录用户的key
	}

	/**
	 * 数据库的d_manager.salt字段的salt值。
	 * 使用SecretUtil.getHmacSHA1(d_manager.salt,LoginConstant.DATABASE_COMMONS_SALT)得到一个中间值。
	 * 用户明文密码SHA1值与此中间值生成AES对应密钥
	 */
	public static final String DATABASE_COMMONS_SALT = SecretUtil.getSHA1("Dream_dcs");
}
