package dream.browser.dao.main;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import dream.browser.model.Menu;
import dream.browser.model.dto.MenuTreeDto;

public interface MenuMapper {
	
	
	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 		插入后台菜单
	 * @param manager		后台管理员对象
	 * @return
	 *
	 */
	int insertSelective(Menu menu);
	
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
	 * @Title: getChildrenMenuList
	 * @Description: 		根据父菜单ID集合，获取这堆父菜单的子菜单
	 * @param parentIdCollection	父菜单ID集合
	 * @return
	 *
	 */
	List<MenuTreeDto> getChildrenMenuList(@Param("parentIdCollection")Collection<String> parentIdCollection);
	
	/**
	 * 
	 * @Title: getMenuListByParentId
	 * @Description: 		获取菜单列表
	 * @param parentId		菜单ID，可以为空，若为空，则获取顶级父菜单
	 * @param rowBounds		分页对象
	 * @return
	 *
	 */
	List<MenuTreeDto> getMenuListByParentId(@Param("parentId")String parentId,RowBounds rowBounds);
	
	/**
	 * 
	 * @Title: countMenuListByParentId
	 * @Description: 		获取菜单列表（数量）
	 * @param parentId		菜单ID，可以为空，若为空，则获取顶级父菜单
	 * @return
	 *
	 */
	Integer countMenuListByParentId(@Param("parentId")String parentId);
	
}