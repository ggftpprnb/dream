package dream.commons.model.commons;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * ClassName：OperationTips
 * Description: 具体业务操作结果提示
 * @author zhujian
 * @param args 
 * @date 2014年5月27日 上午9:34:57
 * @version
 */
public class CommonsOperationTips {
	/**
	 * 
	 * ClassName：TipsCode
	 * Description: 提示代码。如果需要自己定义错误代码，请勿使用下列代码
	 * @author zhujian
	 * @param args 
	 * @date 2014年5月27日 上午9:35:33
	 * @version
	 */
	public static final class TipsCode{
		public static final Integer SUCCESS = 1;					//方法操作成功，达到预期效果
		public static final Integer FAIL = 0;						//方法操作失败
		public static final Integer MISSING_PARAMS = -1;			//参数不完整或参数格式不正确
		
		public static final Set<Integer> ALL;
		
		static{
			ALL = new HashSet<Integer>();
			ALL.add(SUCCESS);
			ALL.add(FAIL);
			ALL.add(MISSING_PARAMS);
		}
		
	}
	
	/**
	 * 
	 * ClassName：TipsMsg
	 * Description: 相应提示代码的提示语
	 * @author zhujian
	 * @param args 
	 * @date 2014年5月27日 上午9:37:28
	 * @version
	 */
	public static final class TipsMsg{
		public static final String SUCCESS = "操作成功";
		public static final String FAIL = "操作失败";
		public static final String MISSING_PARAMS = "参数不完整";
	}
}
