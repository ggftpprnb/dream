package dream.browser.services.impl.test;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dream.browser.dao.main.MenuMapper;
import dream.browser.dao.main.test.TranMapper;
import dream.browser.services.interfaces.ITranService;

@Service
public class TranServiceImpl implements ITranService {

	@Resource
	private TranMapper tranMapper;
	
	@Resource
	private MenuMapper menuMapper;
	
	@Override
	@Transactional
	public void test(Integer number) {
		String id = "zhujian10000";
		int i = tranMapper.updateByKey(id, number);
		System.out.println(i);
	}

}
