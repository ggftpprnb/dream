package dream.browser.services.interfaces;

import dream.browser.model.Manager;

public interface IMainService {
	
	/**
	 * 
	 * @Title: addManager
	 * @Description: 			新增后台管理用户
	 * @param operator			操作者
	 * @param newManager		新增的用户对象
	 * @return
	 *
	 */
	int addManager(Manager operator,Manager newManager);
	
	/**
	 * 
	 * @Title: getMenuUrl
	 * @Description: 			根据菜单ID获取菜单URL
	 * @param manager			管理员对象
	 * @param menuId			菜单ID
	 * @return
	 *
	 */
	String getMenuUrl(Manager manager,String menuId);
}
