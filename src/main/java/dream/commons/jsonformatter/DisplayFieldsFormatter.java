package dream.commons.jsonformatter;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.serializer.ValueFilter;

import dream.commons.model.commons.CommonsOperation;
import dream.commons.model.commons.FastJsonSerialize;

/**
 * 
 * ClassName：DisplayFieldsFormatter
 * Description: 		显示需要的字段
 * @author zhujian
 * @param args 
 * @date 2015年1月15日 下午5:12:12
 * @version
 */
public class DisplayFieldsFormatter implements ValueFilter {

	//需要过滤的字段集合
	private Set<String> needFilterFields = new HashSet<String>();

	public DisplayFieldsFormatter(Set<String> needFilterFields) {
		this.needFilterFields = needFilterFields;
	}

	@Override
	public Object process(Object source, String name, Object value) {
		if(!(source instanceof FastJsonSerialize || source instanceof CommonsOperation))
			if(!needFilterFields.contains(name)){
				value = null;
			}
		return value;
	}
}
