package dream.browser.dao.main;

import org.apache.ibatis.annotations.Param;

import dream.browser.model.Manager;

public interface ManagerMapper {
	
	
	/**
	 * 
	 * @Title: insertSelective
	 * @Description: 		插入后台管理员
	 * @param manager		后台管理员对象
	 * @return
	 *
	 */
	int insertSelective(Manager manager);
	
	/**
	 * 
	 * @Title: getManagerByLoginname
	 * @Description: 		根据登录账号查找后台管理员
	 * @param loginname		登录账号
	 * @return
	 *
	 */
	Manager getManagerByLoginname(@Param("loginname")String loginname);
}