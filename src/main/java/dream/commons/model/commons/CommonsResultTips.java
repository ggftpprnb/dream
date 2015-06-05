package dream.commons.model.commons;

/**
 * 
 * ClassName：ResultTips
 * Description: 最外层返回结果提示
 * @author zhujian
 * @param args 
 * @date 2014年5月27日 上午9:28:45
 * @version
 */
public class CommonsResultTips{
	
	/**
	 * 
	 * ClassName：ResultCode
	 * Description: 返回结果集
	 * @author zhujian
	 * @param args 
	 * @date 2014年5月27日 上午9:33:08
	 * @version
	 */
	public interface ResultCode{
		public static final Integer SUCCESS = 1;		    //访问成功
		public static final Integer EXCEPTION = -1;			//服务器繁忙
		public static final Integer TIMEOUT = 0;            //SESSION超时（streamId不正确）
	}
}
