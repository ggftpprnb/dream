package dream.commons.exception;

import dream.commons.model.commons.CommonsOperationTips.TipsCode;

public class DreamException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Integer tipsCode;				//业务提示代码
	
	private boolean isShowMsg;				//true时message直接在客户端显示
	
	public Integer getTipsCode() {
		return tipsCode;
	}

	public void setTipsCode(Integer tipsCode) {
		this.tipsCode = tipsCode;
	}

	public boolean isShowMsg() {
		return isShowMsg;
	}

	public void setShowMsg(boolean isShowMsg) {
		this.isShowMsg = isShowMsg;
	}

	public DreamException(Integer tipsCode, String message) {
		this(tipsCode,message,false);
	}
	
	public DreamException(Integer code){
		super();
		this.tipsCode = code;
		this.isShowMsg = false;
	}

	public DreamException(String message) {
		this(TipsCode.FAIL,message,true);
	}
	
	
	public DreamException(Integer tipsCode,String message, boolean isShowMsg) {
		super(message);
		this.tipsCode = tipsCode;
		this.isShowMsg = isShowMsg;
	}
	
}
