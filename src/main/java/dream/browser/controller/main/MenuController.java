package dream.browser.controller.main;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dream.browser.controller.BaseController;
import dream.browser.services.interfaces.IMenuService;
import dream.commons.model.commons.FastJsonSerialize;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	
	@Resource
	private IMenuService menuService;
	
	@RequestMapping(params={"m=getMenuList"})
	public FastJsonSerialize getMenuList(HttpServletRequest request,String id,Integer page,Integer rows){
		FastJsonSerialize listResult = menuService.getMenuPagination(id, page, rows);
		System.out.println("m=getMenuList?page="+page+"&rows="+rows);
		return listResult;
	}
}
