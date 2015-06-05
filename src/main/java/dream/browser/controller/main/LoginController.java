package dream.browser.controller.main;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dream.browser.constant.MainConstant.SessionKey;
import dream.browser.controller.BaseController;
import dream.browser.exception.LoginException;
import dream.browser.model.Manager;
import dream.browser.services.interfaces.ILoginService;
import dream.commons.model.commons.CommonsOperation;
import dream.commons.model.commons.CommonsOperationTips.TipsCode;
import dream.commons.model.commons.CommonsResult;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Resource
	private ILoginService loginService;
	
	@RequestMapping(params={"m=toIndex"})
	public ModelAndView index(HttpServletRequest request){
		String loginSalt = UUID.randomUUID().toString();
		request.getSession(true).setAttribute(SessionKey.LOGIN_SALT, loginSalt);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		mav.addObject(SessionKey.LOGIN_SALT, loginSalt);
		return mav;
	}
	
	@RequestMapping(params={"m=login"})
	public CommonsResult login(HttpServletRequest request,String loginname,String password,String loginSalt){
		
		HttpSession session = request.getSession(true);
		Object sessionLoginSalt = session.getAttribute(SessionKey.LOGIN_SALT);
		if(StringUtils.isAnyBlank(sessionLoginSalt.toString(),loginSalt) || !sessionLoginSalt.toString().equals(loginSalt)){
			throw new LoginException("随机码不匹配");
		}
		
		//根据账号和密码获取登录对象，当且仅当账号密码匹配时返回登录对象
		Manager loginingManager = loginService.getLoginingManager(loginname, loginSalt, password);
		if(loginingManager!=null){
			session.removeAttribute(SessionKey.LOGIN_SALT);
			session.setAttribute(SessionKey.LOGIN_USER, loginingManager);
		}else{
			session.setAttribute(SessionKey.LOGIN_SALT, UUID.randomUUID().toString());
		}
		
		Integer tipsCode = null;
		String tipsMsg = null;
		if(loginingManager!=null){
			tipsCode = TipsCode.SUCCESS;
			tipsMsg = "登录成功";
		}else{
			tipsCode = TipsCode.FAIL;
			tipsMsg = "登录账号或登录密码不正确";
		}
		
		CommonsOperation op = new CommonsOperation(tipsCode,tipsMsg);
		
		return new CommonsResult(op);
	}
}
