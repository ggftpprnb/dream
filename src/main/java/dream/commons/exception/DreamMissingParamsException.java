package dream.commons.exception;

public class DreamMissingParamsException extends DreamException{

	private static final long serialVersionUID = 1L;

	public DreamMissingParamsException(Integer tipsCode, String message,boolean isShowMsg) {
		super(tipsCode, message, isShowMsg);
	}

	public DreamMissingParamsException(Integer tipsCode, String message) {
		super(tipsCode, message);
	}

	public DreamMissingParamsException(Integer code) {
		super(code);
	}

	public DreamMissingParamsException(String message) {
		super(message);
	}
	
	

}
