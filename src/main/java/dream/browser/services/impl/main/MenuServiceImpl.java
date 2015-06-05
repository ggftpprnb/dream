package dream.browser.services.impl.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import dream.browser.dao.main.MenuMapper;
import dream.browser.model.Menu;
import dream.browser.model.dto.MenuTreeDto;
import dream.browser.services.interfaces.IMenuService;
import dream.commons.exception.DreamMissingParamsException;
import dream.commons.model.commons.DataResult;
import dream.commons.model.commons.FastJsonSerialize;
import dream.commons.model.commons.ListResult;

@Service
public class MenuServiceImpl implements IMenuService {
	
	@Resource
	private MenuMapper menuMapper;

	@Override
	public MenuTreeDto getMenuById(String menuId) {
		if(StringUtils.isBlank(menuId)) throw new DreamMissingParamsException("menuId is null");
		return menuMapper.getMenuById(menuId);
	}

	@Override
	public int addMenu(Menu menu) {
		if(menu==null) throw new DreamMissingParamsException("menu is null");
		if(StringUtils.isBlank(menu.getName())) throw new DreamMissingParamsException("menu name is null");
		//if(StringUtils.isBlank(menu.getParentId()) && StringUtils.isBlank(menu.getUrl())) throw new DreamMissingParamsException("parentId and url is null");
		menu.setId(UUID.randomUUID().toString());
		menu.setIsSoftDelete(false);
		
		return menuMapper.insertSelective(menu);
	}

	@Override
	public MenuTreeDto getMenuTreeById(String menuId) {
		if(StringUtils.isBlank(menuId)) throw new DreamMissingParamsException("menuId is null");
		MenuTreeDto firstMenu = getMenuById(menuId);
		if(firstMenu!=null){
			initMenuTree(new ArrayList<MenuTreeDto>(Arrays.asList(firstMenu)));
		}
		
		return firstMenu;
	}
	
	/**
	 * 
	 * @Title: initMenuTree
	 * @Description: 		初始化菜单树
	 * @param menuList		菜单列表
	 *
	 */
	private void initMenuTree(List<MenuTreeDto> menuList){
		if(menuList!=null &&!menuList.isEmpty()){
			List<String> menuIdList = new ArrayList<String>();
			Map<String,MenuTreeDto> menuMap = new HashMap<String, MenuTreeDto>();
			for(MenuTreeDto menu:menuList){
				menu.setText(menu.getName());
				menuMap.put(menu.getId(), menu);
				menuIdList.add(menu.getId());
			}
			
			List<MenuTreeDto> children = new ArrayList<MenuTreeDto>(menuMapper.getChildrenMenuList(menuIdList));
			initMenuTree(children);
			
			if(children!=null &&!children.isEmpty()){
				for(MenuTreeDto menu:children){
					MenuTreeDto parent = menuMap.get(menu.getParentId());
					if(parent!=null){
						if(parent.getChildren()==null) parent.setChildren(new ArrayList<MenuTreeDto>());
						parent.getChildren().add(menu);
					}
				}
			}
		}
	}

	@Override
	public List<MenuTreeDto> getTopMenuList() {
		return menuMapper.getMenuListByParentId(null, new RowBounds());
	}

	@Override
	public FastJsonSerialize getMenuPagination(String menuId, Integer currentPage,Integer pageSize) {
		// TODO Auto-generated method stub
		List<MenuTreeDto> menuList = menuMapper.getMenuListByParentId(menuId, new RowBounds((currentPage-1)*pageSize,pageSize));
		Integer total = menuMapper.countMenuListByParentId(menuId);
		FastJsonSerialize fastJsonSerialize = null;
		if(StringUtils.isBlank(menuId)){
			fastJsonSerialize = new ListResult(menuList, total);
		}else{
			fastJsonSerialize = new DataResult(menuList);
		}
		return fastJsonSerialize;
	}

}
