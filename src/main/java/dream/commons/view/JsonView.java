package dream.commons.view;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

import dream.commons.model.commons.CommonsResult;
import dream.commons.model.commons.DataResult;
import dream.commons.model.commons.FastJsonSerialize;
import dream.commons.model.commons.ListResult;

/**
 * 
 * ClassName：SmicJsonView
 * Description: 自定义的JSON解释器，当Controller的方法返回的对象是Result时，
 * 				在把Result转换为JSON字符串时，进行值的序列化处理（如，boolean类型的值转换成1和0），
 * 				基于FastJson对Spring的支持实现的
 * @author zhujian
 * @date 2014年5月4日 下午2:03:38
 * @version
 */
public class JsonView extends FastJsonJsonView{
	
	private boolean updateContentLength  = false;
	
	private Set<String> renderedAttributes;
	
	@Override
	public void setRenderedAttributes(Set<String> renderedAttributes) {
        this.renderedAttributes = renderedAttributes;
    }

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		Object value = filterModel(model);
		
		String text = null;
		if(value instanceof FastJsonSerialize){
			
			Object data = null;
			if(value instanceof CommonsResult){
				data = value;
			}else if(value instanceof DataResult){
				data = ((DataResult) value).getData();
			}else if(value instanceof ListResult){
				data = value;
			}
			
			JSONSerializer jser = new JSONSerializer();
			addFilter((FastJsonSerialize) value, jser);
			jser.write(data);
			text = jser.toString();
			jser.close();
		}else{
			text = JSON.toJSONString(value, super.getFeatures());
		}
		
        byte[] bytes = text.getBytes(super.getCharset());

        OutputStream stream = this.updateContentLength ? createTemporaryOutputStream() : response.getOutputStream();
        stream.write(bytes);

        if (this.updateContentLength) {
            writeToResponse(response, (ByteArrayOutputStream) stream);
        }
		
	}
	
	
	
	@Override
	protected Object filterModel(Map<String, Object> model) {
		Map<String, Object> result = new HashMap<String, Object>(model.size());
        Set<String> renderedAttributes = !CollectionUtils.isEmpty(this.renderedAttributes) ? this.renderedAttributes : model.keySet();
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            if (!(entry.getValue() instanceof BindingResult) && renderedAttributes.contains(entry.getKey())) {
                if(entry.getValue() instanceof CommonsResult){
                	return entry.getValue();
                }else if(entry.getValue() instanceof ListResult){
                	return entry.getValue();
                }else if(entry.getValue() instanceof DataResult){
                	return entry.getValue();
                }
            	result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
	}



	/**
	 * 
	 * @Title: addFilter
	 * @Description: 	添加过滤条件，如Boolean对象转换成1和0
	 * @param result	需要转换成JSON字符串的对象
	 * @param jser
	 * @throws
	 */
	private void addFilter(FastJsonSerialize fastJsonSerialize,JSONSerializer jser){
		
		SerializeFilter[] filters = fastJsonSerialize.getSerializeFilter();
		if(filters!=null){
			
			for(SerializeFilter filter :filters){
				if(filter instanceof ValueFilter){
					jser.getValueFilters().add((ValueFilter)filter);
				}else if(filter instanceof NameFilter){
					jser.getNameFilters().add((NameFilter)filter);
				}else if(filter instanceof PropertyFilter){
					jser.getPropertyFilters().add((PropertyFilter)filter);				
				}else if(filter instanceof PropertyPreFilter){
					jser.getPropertyPreFilters().add((PropertyPreFilter)filter);
				}
			}
		}
		
		SerializerFeature[] features = fastJsonSerialize.getSerializerFeature();
		if(features!=null){
			for(SerializerFeature feature:features){
				jser.config(feature, true);
			}
		}
		
		fastJsonSerialize.setSerializeFilter(null);
		fastJsonSerialize.setSerializerFeature(null);
	}
	
}
