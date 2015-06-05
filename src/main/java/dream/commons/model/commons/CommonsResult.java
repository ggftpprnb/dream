package dream.commons.model.commons;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

import dream.commons.model.commons.CommonsResultTips.ResultCode;



/**
 * 
 * ClassName：Result
 * Description: 		统一定义的返回对象，把此对象转换成JSON字符串给到手机客户端
 * @author zhujian
 * @param args 
 * @date 2014年5月4日 下午6:05:57
 * @version
 */
public class CommonsResult extends FastJsonSerialize{
	
	private Integer code;							//返回代码。详见ResultDto
	private CommonsOperation op;					//访问成功后，程序操作标志的代码
	
	public CommonsResult() {
		this(ResultCode.SUCCESS);
	}
	
	public CommonsResult(Integer code) {
		this(code,null);
	}
	
	public CommonsResult(CommonsOperation op) {
		this(ResultCode.SUCCESS,op);
	}
	
	public CommonsResult(Integer code, CommonsOperation op,SerializeFilter... serializeFilter) {
		this(code,op,serializeFilter,null);
	}
	
	public CommonsResult(Integer code, CommonsOperation op,SerializeFilter[] serializeFilter,SerializerFeature[] serializerFeature) {
		super();
		this.code = code;
		this.op = op;
		super.serializeFilter = serializeFilter;
		super.serializerFeature = serializerFeature;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public CommonsOperation getOp() {
		return op;
	}

	public void setOp(CommonsOperation op) {
		this.op = op;
	}

}
