package dream.commons.model.commons;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * ClassName：DataResult
 * Description: 			返回某个对象的JSON字符串
 * @author zhujian
 * @param args 
 * @date 2015年1月5日 下午7:41:14
 * @version
 */
public class DataResult extends FastJsonSerialize{
	
	public DataResult(Object data,SerializeFilter... serializeFilter) {
		super();
		super.serializeFilter = serializeFilter;
		this.data = data;
	}
	
	public DataResult(Object data,SerializeFilter[] serializeFilter,SerializerFeature[] serializerFeature) {
		super();
		super.serializeFilter = serializeFilter;
		super.serializerFeature = serializerFeature;
		this.data = data;
	}
	

	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	
}
