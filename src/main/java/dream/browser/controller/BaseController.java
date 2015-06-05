package dream.browser.controller;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

import dream.commons.config.Config;
import dream.commons.config.Config.DeployType;


public class BaseController {

	@ModelAttribute
	protected void printlnURL(HttpServletRequest request){
		if(DeployType.developer.equals(Config.getDeployType())){
			String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
			StringBuilder sb = new StringBuilder();
			Map<String,String[]> map = request.getParameterMap();
			Iterator<Entry<String, String[]>> iter = map.entrySet().iterator();
			while(iter.hasNext()){
				Entry<String, String[]> entry = iter.next();
				String[] str = entry.getValue();
				for(String s:str){
					sb.append("&").append(entry.getKey()).append("=").append(s);
				}
			}
			if(sb.length()>=1){
				url = url + "?" + sb.substring(1, sb.length());
			}
			System.out.println(url);
		}
	}
}
