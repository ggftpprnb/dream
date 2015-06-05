package dream.commons.model.commons;


/**
 * 
 * ClassName：OperationTips Description: 程序对操作后的结果返回
 * 
 * @author zhujian
 * @date 2014年5月4日 下午6:08:40
 * @version
 */
public class CommonsOperation {
	private Integer code; 	// 程序操作后各种状态标志
	private String msg; 	// 状态标志的描述
	private Object data; 	// 各种数据
	
	

	public CommonsOperation() {
		
	}

	public CommonsOperation(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}


	public CommonsOperation(Integer code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
