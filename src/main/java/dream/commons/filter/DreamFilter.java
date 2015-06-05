package dream.commons.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * 
 * ClassName：YoYoFilter
 * Description: 字符编码过滤器及String.trim()过滤器。
 * @author zhujian
 * @param args 
 * @date 2014年6月17日 上午9:57:27
 * @version
 */
public class DreamFilter extends CharacterEncodingFilter{
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		request = new DreamHttpServletRequestWrapper(request);
//		System.out.println(request+"-------------------------------------------");
		super.doFilterInternal(request, response, filterChain);
		
	}

}
