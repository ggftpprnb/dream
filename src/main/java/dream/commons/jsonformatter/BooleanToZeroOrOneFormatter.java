package dream.commons.jsonformatter;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * 
 * ClassName：BooleanToZeroOrOneFormatter
 * Description: 			controller层把对象转换成json字符串时，把boolean值转为0或1
 * @author zhujian
 * @param args 
 * @date 2015年1月15日 下午5:11:28
 * @version
 */
public class BooleanToZeroOrOneFormatter implements ValueFilter {

	@Override
	public Object process(Object source, String name, Object value) {
		if (value instanceof Boolean) {
			return (Boolean)value?1:0;
		}

		return value;
	}

}
