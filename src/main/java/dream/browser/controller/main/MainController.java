package dream.browser.controller.main;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dream.browser.controller.BaseController;
import dream.browser.model.dto.MenuTreeDto;
import dream.browser.services.interfaces.IMainService;
import dream.browser.services.interfaces.IMenuService;
import dream.commons.jsonformatter.DisplayFieldsFormatter;
import dream.commons.model.commons.DataResult;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController{
	
	@Resource
	private IMainService mainService;
	
	@Resource
	private IMenuService menuService;
	
	/**
	 * 
	 * @Title: toMain
	 * @Description: 		运营管理平台主页
	 * @param request		HTTP请求
	 * @return
	 *
	 */
	@RequestMapping
	public ModelAndView toMain(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/main");
		List<MenuTreeDto> topMenuList = menuService.getTopMenuList();
		mav.addObject("topMenuList", topMenuList);
		return mav;
	}
	
	/**
	 * 
	 * @Title: toWelcome
	 * @Description: 		欢迎页
	 * @return
	 *
	 */
	@RequestMapping(params={"m=toWelcome"})
	public ModelAndView toWelcome(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main/welcome");
		return mav;
	}
	
	/**
	 * 
	 * @Title: toMenu
	 * @Description: 		根据菜单ID获取菜单对应的地址
	 * @param menuId		菜单ID
	 * @return
	 *
	 */
	@RequestMapping(params={"m=toMenu"})
	public ModelAndView toMenu(String menuId){
		ModelAndView mav = new ModelAndView();
		String viewName = mainService.getMenuUrl(null, menuId);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	
	private static final Set<String> loadMenuTree_needFields;
	static{
		loadMenuTree_needFields = new HashSet<String>();
		loadMenuTree_needFields.add("children");
		loadMenuTree_needFields.add("id");
		loadMenuTree_needFields.add("text");
	}
	
	/**
	 * 
	 * @Title: loadMenuTree
	 * @Description: 		获取菜单树
	 * @param menuId		父菜单ID
	 * @return
	 *
	 */
	@RequestMapping(params={"m=loadMenuTree"})
	public DataResult loadMenuTree(String menuId){
		MenuTreeDto menuTree = menuService.getMenuTreeById(menuId);
		DataResult dataResult = new DataResult(Arrays.asList(menuTree), new DisplayFieldsFormatter(loadMenuTree_needFields));
		return dataResult;
	}
}
