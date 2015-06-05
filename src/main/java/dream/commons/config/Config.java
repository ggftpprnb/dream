package dream.commons.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public final class Config {
	private static Configuration commonsConfig;			//全局使用的公用配置
	private static DeployType deployType;
	static{
		try {
			commonsConfig = new PropertiesConfiguration("commons.properties");
			
			
			
			
			getDeployType();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: getDeployType
	 * @Description: 		获取发布类型。开发、准上线、正式上线
	 * @return
	 *
	 */
	public static DeployType getDeployType(){
		
		if(deployType==null){
			String deploy = commonsConfig.getString("deployType", "online");
			if(DeployType.developer.getValue().equals(deploy)){
				deployType = DeployType.developer;
			}else if(DeployType.testOnline.getValue().equals(deploy)){
				deployType = DeployType.testOnline;
			}else if(DeployType.online.getValue().equals(deploy)){
				deployType = DeployType.online;
			}
		}
		
		return deployType;
	}
	
	public enum DeployType{
		developer("developer"),					//开发
		testOnline("testOnline"),				//准上线
		online("online")						//上线
		;
		
		private String value;
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		private DeployType(String str){
			value = str;
		}
	}
}
