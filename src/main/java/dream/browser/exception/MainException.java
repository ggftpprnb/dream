package dream.browser.exception;

import dream.commons.exception.DreamException;

public class MainException extends DreamException {

	private static final long serialVersionUID = 1L;

	public MainException(Integer tipsCode, String message, boolean isShowToast) {
		super(tipsCode, message, isShowToast);
	}

	public MainException(Integer tipsCode, String message) {
		super(tipsCode, message);
	}

	public MainException(Integer code) {
		super(code);
	}

	public MainException(String message) {
		super(message);
	}

	
}
