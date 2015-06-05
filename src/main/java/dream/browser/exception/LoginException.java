package dream.browser.exception;

import dream.commons.exception.DreamException;

public class LoginException extends DreamException{

	private static final long serialVersionUID = 1L;

	public LoginException(Integer tipsCode, String message, boolean isShowToast) {
		super(tipsCode, message, isShowToast);
	}

	public LoginException(Integer tipsCode, String message) {
		super(tipsCode, message);
	}

	public LoginException(Integer code) {
		super(code);
	}

	public LoginException(String message) {
		super(message);
	}
	
}
