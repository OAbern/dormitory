package com.cqupt.dormitory.test;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.ResultMessage;

/**
 * 测试类(对部分返回json的快速处理)
 * @author Bern
 *
 */
@Controller
@RequestMapping("/test")
public class TestController {
	
	/**
	 * 操作结果返回的json
	 */
	@RequestMapping("/getResult")
	public void resultTest(int result, HttpServletResponse response) {
		ResultMessage message = new ResultMessage();
		if(result == 1) {
			message.setStatus(ResultMessage.SUCCESS);
			message.setInfo("操作成功！》测试");
		}else if(result == 0){
			message.setStatus(ResultMessage.FAILED);
			message.setInfo("操作失败！》测试");
			message.setError("错误原因！》测试");
		}
		JSONUtils.toJSON(message, response);
	}
	
	
	
	
}
