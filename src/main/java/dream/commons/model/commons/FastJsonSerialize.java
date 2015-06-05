package dream.commons.model.commons;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * ClassName：FastJsonSerialize
 * Description: 		fastjson的字段过滤器
 * @author zhujian
 * @param args 
 * @date 2015年1月5日 下午7:42:35
 * @version
 */
public class FastJsonSerialize {

	protected SerializeFilter[] serializeFilter;		//过滤器
	protected SerializerFeature[] serializerFeature;	//fastjson自带的过滤器

	
	public SerializeFilter[] getSerializeFilter() {
		return serializeFilter;
	}

	public void setSerializeFilter(SerializeFilter[] serializeFilter) {
		this.serializeFilter = serializeFilter;
	} 

	public SerializerFeature[] getSerializerFeature() {
		return serializerFeature;
	}

	public void setSerializerFeature(SerializerFeature[] serializerFeature) {
		this.serializerFeature = serializerFeature;
	}
	
	/**
	 * 
	 * @Title: addSerializeFilter
	 * @Description: 添加自定义过滤器
	 * @param filters
	 *
	 */
	public void addSerializeFilter(SerializeFilter... filters){
		if(filters!=null && filters.length>=1){
			if(serializeFilter==null || serializeFilter.length==0){
				serializeFilter = filters;
			}else{
				SerializeFilter[] newFilters = new SerializeFilter[serializeFilter.length+filters.length];
				System.arraycopy(serializeFilter, 0,newFilters, 0, serializeFilter.length);
				
				System.arraycopy(filters, 0,newFilters, serializeFilter.length, filters.length);
				serializeFilter = newFilters;
			}
		}
	}
	
	/**
	 * 
	 * @Title: addSerializerFeature
	 * @Description: 添加fastjson自带的过滤器
	 * @param features
	 *
	 */
	public void addSerializerFeature(SerializerFeature...features){
		if(features!=null && features.length>=1){
			if(serializerFeature==null || serializerFeature.length==0){
				serializerFeature = features;
			}else{
				SerializerFeature[] newFeature = new SerializerFeature[serializerFeature.length+features.length];
				System.arraycopy(serializerFeature, 0,newFeature, 0, serializerFeature.length);
				System.arraycopy(features, 0,newFeature, serializerFeature.length, features.length);
				serializerFeature = newFeature;
			}
		}
	}
}
