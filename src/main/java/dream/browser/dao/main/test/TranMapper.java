package dream.browser.dao.main.test;

import org.apache.ibatis.annotations.Param;

import dream.browser.model.Tran;


public interface TranMapper {
	
	int insertSelective(Tran record);
	
	String getTime(@Param("uuid")String uuid);
	
	Tran selectByKey(@Param("id")String id);
	
	Tran selectByName(@Param("name")String name);
	
	int updateByKey(@Param("id")String id,@Param("age")Integer age);
	
	int updateByName(@Param("name")String name,@Param("age")Integer age);
	
	
	int insertOrUpdate(Tran record);
}