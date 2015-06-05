package dream.browser.services.impl.main;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dream.browser.constant.MainConstant;
import dream.browser.dao.main.ManagerMapper;
import dream.browser.exception.MainException;
import dream.browser.model.Manager;
import dream.browser.model.Menu;
import dream.browser.services.interfaces.IMainService;
import dream.browser.services.interfaces.IMenuService;
import dream.commons.exception.DreamException;
import dream.commons.exception.DreamMissingParamsException;
import dream.commons.util.SecretUtil;

@Service
public class MainServiceImpl implements IMainService {

	@Resource
	private ManagerMapper managerMapper;
	
	@Resource
	private IMenuService menuService;
	
	@Override
	public int addManager(Manager operator, Manager newManager) {
		
		if(operator==null) throw new DreamException("operator is null");
		if(newManager==null) throw new DreamException("newManager is null");
		
		if(StringUtils.isAnyBlank(newManager.getLoginname(),newManager.getNickname(),newManager.getPassword())){
			throw new DreamException("missing params");
		}
		
		newManager.setId(UUID.randomUUID().toString());
		newManager.setSalt(UUID.randomUUID().toString()); 
		newManager.setPassword(SecretUtil.encryptAES(newManager.getPassword(), SecretUtil.getHmacMD5(newManager.getSalt(), MainConstant.DATABASE_COMMONS_SALT)));
		
		return managerMapper.insertSelective(newManager);
	}

	@Override
	public String getMenuUrl(Manager manager, String menuId) {
		if(StringUtils.isBlank(menuId)) throw new DreamMissingParamsException("menuId is null");
		Menu menu = menuService.getMenuById(menuId);
		if(menu==null) throw new MainException("菜单不存在");
		if(StringUtils.isBlank(menu.getUrl())) throw new MainException("菜单没有对应的URL");
		return menu.getUrl();
	}

}
