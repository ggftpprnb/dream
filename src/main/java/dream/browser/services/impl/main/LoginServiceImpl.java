package dream.browser.services.impl.main;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dream.browser.constant.MainConstant;
import dream.browser.dao.main.ManagerMapper;
import dream.browser.model.Manager;
import dream.browser.services.interfaces.ILoginService;
import dream.commons.exception.DreamException;
import dream.commons.util.SecretUtil;

@Service
public class LoginServiceImpl implements ILoginService {

	@Resource
	private ManagerMapper managerMapper;
	
	@Override
	public Manager getLoginingManager(String loginname,String loginSalt, String randomPwd) {
		Manager loginingManager = null;
		if(!StringUtils.isAnyBlank(loginname,loginSalt,randomPwd)){
			Manager manager = managerMapper.getManagerByLoginname(loginname);
			if(manager!=null){
				//数据库真正的密码（SHA1）。
				String realPwd = SecretUtil.decryptAES(manager.getPassword(), SecretUtil.getHmacMD5(manager.getSalt(), MainConstant.DATABASE_COMMONS_SALT));
				
				//用户真正的密码SHA1加上随机码得到的认证密码，若randomPwd与authPwd则表示密码正确，允许登录
				String authPwd = SecretUtil.getHmacSHA1(realPwd, loginSalt);
				
				if(randomPwd.equals(authPwd)){
					loginingManager = manager;
				}
			}
		}
		
		return loginingManager;
	}

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

}
