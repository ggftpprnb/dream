package dream.browser.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dream.browser.services.interfaces.ITranService;

@Controller("/test")
public class TestController extends BaseController {
	
	@Resource
	private ITranService tranService;
	
	@RequestMapping(params={"m=test"})
	public void test(Integer number){
		tranService.test(number);
	}
	
}
