package dream.commons.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringUtils;

import dream.commons.util.PatternUtil;

/**
 * 
 * ClassName：DreamHttpServletRequestWrapper
 * Description: 		从Request.getParameter或Request.getParameterValues获取参数值时，做一些自定义处理
 * @author zhujian
 * @param args 
 * @date 2014年6月17日 上午10:00:23
 * @version
 */
public class DreamHttpServletRequestWrapper extends HttpServletRequestWrapper{
	
	private static final String CURRENT_PAGE = "page";		//当前页的参数名
	private static final String PAGE_SIZE = "rows";			//页大小的参数名
	private static final int DEFAULT_CURRENT_PAGE = 1;	//默认的当前页
	private static final int DEFAULT_PAGE_SIZE = 10;	//默认的页大小
	private static final int MAX_PAGE_SIZE = 100;	//页大小允许的最大值

	public DreamHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		String returnValue = null;
		String value = super.getParameter(name);
		if(CURRENT_PAGE.equals(name)){
			returnValue = initCurrentPage(value);
		}else if(PAGE_SIZE.equals(name)){
			returnValue = initPageSize(value);
		}else{
			returnValue = StringUtils.trim(value);
		}
		
		return returnValue;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] returnValue = null;
		String[] values = super.getParameterValues(name);
		if(CURRENT_PAGE.equals(name) || PAGE_SIZE.equals(name)){
			
			if(values!=null &&values.length>=1){
				returnValue = new String[values.length];
				for (int i = 0; i < values.length; i++) {
					if(CURRENT_PAGE.equals(name)){
						returnValue[i] = initCurrentPage(values[i]);
					}else if(PAGE_SIZE.equals(name)){
						returnValue[i] = initPageSize(values[i]);
					}
				}
			}
			
			if(returnValue==null){
				returnValue = new String[1];
				if(CURRENT_PAGE.equals(name)){
					returnValue[0] = initCurrentPage(null);
				}else if(PAGE_SIZE.equals(name)){
					returnValue[0] = initPageSize(null);
				}
			}
		}else{
			if (values != null && values.length>=1) {
				returnValue = new String[values.length];
				for (int i = 0; i < values.length; i++) {
					returnValue[i] = StringUtils.trim(values[i]);
				}
			}
		}
		
		
		return returnValue;
	}
	
	/**
	 * 
	 * @Title: initCurrentPage
	 * @Description: 			把当前页初始化后的值返回，当原始值为空或为非法的数字时，
	 * 							返回默认的DEFAULT_CURRENT_PAGE，其它返回原始值
	 * 
	 * @param value				原始值
	 * @return
	 *
	 */
	private String initCurrentPage(String value){
		String currentPage = null;
		if(!StringUtils.isBlank(value) && PatternUtil.isMaches(value, PatternUtil.UNSIGNED_INTEGER)){
			try {
				int intValue = Integer.parseInt(value);
				currentPage = String.valueOf(intValue);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}

		if(currentPage==null) currentPage = String.valueOf(DEFAULT_CURRENT_PAGE);
		
		return currentPage;
	}
	
	/**
	 * 
	 * @Title: initPageSize
	 * @Description: 			把页大小初始化后的值返回
	 * @param value				原始值
	 * @return
	 *
	 */
	private String initPageSize(String value){
		String pageSize = null;
		if(!StringUtils.isBlank(value) && PatternUtil.isMaches(value, PatternUtil.UNSIGNED_INTEGER)){
			try{
				int intValue = Integer.parseInt(value);
				if(intValue>MAX_PAGE_SIZE) intValue = intValue%MAX_PAGE_SIZE;
				pageSize = String.valueOf(intValue);
			}catch(RuntimeException e){
				e.printStackTrace();
			}
		}
		
		if(pageSize==null) pageSize = String.valueOf(DEFAULT_PAGE_SIZE);
		
		return pageSize;
	}
}
