package dream.browser.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;

import dream.commons.config.Config.DeployType;

public class DreamBrowserDataSource extends BasicDataSource{
	
	//发布类型。参考：commons.properties中的deployType
	public void setDeployType(String deployType) {
		if(!StringUtils.isBlank(deployType)){
			
			if(DeployType.developer.getValue().equals(deployType)){
				
				//开发库
				//super.setDriverClassName("com.mysql.jdbc.Driver");
				
				//super.setUrl("jdbc:mysql:loadbalance://192.168.44.125:3306,192.168.44.126:3306,192.168.44.127:3306/dream?roundRobinLoadBalance=true&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
				//super.setUrl("jdbc:mysql://192.168.44.135:6666/dream?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
				super.setUrl("jdbc:mysql://192.168.44.126:3306/dream?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
				
				//super.setUrl("jdbc:mysql://192.168.44.137:8066/dream?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
				
				super.setDriverClassName("org.mariadb.jdbc.Driver");
				
				super.setUsername("root");
				//super.setPassword("smc2012");
				
				//怎么解决冲突
				
				super.setInitialSize(0);
				super.setMaxIdle(8);
				super.setMaxTotal(8);
				super.setMaxWaitMillis(5000);
				super.setMinIdle(0);
			}else if(DeployType.testOnline.getValue().equals(deployType)){
				
				//准上线测试库
				super.setUrl("jdbc:mysql://192.168.111.132:3306/dream?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
				super.setDriverClassName("com.mysql.jdbc.Driver");
				super.setUsername("root");
				super.setPassword("smc2012");
				
				super.setInitialSize(0);
				super.setMaxIdle(8);
				super.setMaxTotal(8);
				super.setMaxWaitMillis(5000);
				super.setMinIdle(0);
			}else if(DeployType.online.getValue().equals(deployType)){
				
				//正式上线库
				
				//how to online
				
				super.setUrl("jdbc:mysql://192.168.111.132:3306/dream?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true");
				super.setDriverClassName("com.mysql.jdbc.Driver");
				super.setUsername("root");
				super.setPassword("smc2012");
				
				super.setInitialSize(0);
				super.setMaxIdle(8);
				super.setMaxTotal(8);
				super.setMaxWaitMillis(5000);
				super.setMinIdle(0);
			}
		}
	}
	
	
}
