package dream.browser.services.interfaces;

import java.util.List;

import dream.browser.model.Menu;
import dream.browser.model.dto.MenuTreeDto;
import dream.commons.model.commons.FastJsonSerialize;

public interface IMenuService {

	/**
	 * 
	 * @Title: getMenuById
	 * @Description: 		根据菜单ID获取菜单对象
	 * @param menuId		菜单ID
	 * @return
	 *
	 */
	MenuTreeDto getMenuById(String menuId);
	
	/**
	 * 
	 * @Title: getMenuTreeById
	 * @Description: 		获取菜单树
	 * @param menuId		菜单ID
	 * @return
	 *
	 */
	MenuTreeDto getMenuTreeById(String menuId);
	
	/**
	 * 
	 * @Title: getTopMenuList
	 * @Description: 		获取顶级菜单
	 * @return
	 *
	 */
	List<MenuTreeDto> getTopMenuList();
	
	/**
	 * 
	 * @Title: addMenu
	 * @Description: 		添加新菜单
	 * @param menu			菜单对象
	 * @return
	 *
	 */
	int addMenu(Menu menu);
	
	/**
	 * 
	 * @Title: getMenuPagination
	 * @Description: 		获取菜单分页对象
	 * @param menuId		菜单ID，可以为空，为空时则获取顶级父菜单列表
	 * @param currentPage	第几页
	 * @param pageSize		页大小
	 * @return
	 *
	 */
	FastJsonSerialize getMenuPagination(String menuId,Integer currentPage,Integer pageSize);
}
