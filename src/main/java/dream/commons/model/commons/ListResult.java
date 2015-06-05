package dream.commons.model.commons;

import java.util.List;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * ClassName：ListResult
 * Description: 			返回某个列表的JSON字符串
 * @author zhujian
 * @param args 
 * @date 2015年1月5日 下午7:42:07
 * @version
 */
public class ListResult extends FastJsonSerialize{
	
	public ListResult(List<?> rows,Integer total,SerializeFilter... serializeFilter) {
		super();
		super.serializeFilter = serializeFilter;
		this.rows = rows;
		this.total = total;
	}
	
	public ListResult(List<?> rows,Integer total,SerializeFilter[] serializeFilter,SerializerFeature[] serializerFeature) {
		super();
		super.serializeFilter = serializeFilter;
		super.serializerFeature = serializerFeature;
		this.rows = rows;
		this.total = total;
	}

	private List<?> rows;							//列表数据

	private Integer total;							//列表数据总数

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
