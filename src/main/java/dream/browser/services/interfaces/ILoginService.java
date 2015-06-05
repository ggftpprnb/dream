package dream.browser.services.interfaces;

import dream.browser.model.Manager;

public interface ILoginService {
	
	/**
	 * 
	 * @Title: getLoginingManager
	 * @Description: 			是否允许用户登录
	 * @param loginname			登录账号
	 * @param loginSalt			随机码
	 * @param randomPwd			随机码+账号密码组成的登录密码
	 * @return
	 *
	 */
	Manager getLoginingManager(String loginname,String loginSalt,String randomPwd);
	
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
}
