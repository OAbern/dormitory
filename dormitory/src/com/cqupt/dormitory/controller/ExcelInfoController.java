package com.cqupt.dormitory.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.dormitory.model.Admin;
import com.cqupt.dormitory.model.ExcelInfo;
import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.service.ExcelInfoService;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.utils.SystemContext;
import com.cqupt.dormitory.utils.UploadFileUtils;
import com.cqupt.dormitory.vo.ResultMessage;

/**
 * 处理宿舍分配Excel的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/excel")
public class ExcelInfoController {
	@Resource(name="excelInfoServiceImpl")
	private ExcelInfoService excelInfoService;
	
	/**
	 * 添加上传的文件
	 * @param file
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addExcel")
	public ModelAndView addExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		boolean result1 = false;
		boolean	result2 = false;
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacherInSession = (Teacher) request.getSession().getAttribute("teacher");
		if(teacherInSession == null) {
			modelAndView.setViewName("redirect:/fu/f_stuchangehotel");
			return modelAndView;
		}
		String originalFilename = file.getOriginalFilename();	//获取原始的文件名
		int i = originalFilename.lastIndexOf(".");
		String suffix = originalFilename.substring(i);		//获取文件类型尾缀名
		String savePath = request.getSession().getServletContext().getRealPath("//");	//获取存储的路径
		String fileName = UploadFileUtils.getUniqueName()+suffix;	//生成唯一的文件名
		String path = "UploadFile/"+fileName;		//相对于项目url的存储路径
		
		/*构造Excel信息*/
		ExcelInfo excelInfo = new ExcelInfo();
		excelInfo.setSubmitTeacher(teacherInSession);
		excelInfo.setName(originalFilename);
		excelInfo.setStatus(0);
		excelInfo.setPath(path);
		
		/*保存上传的文件*/
		File saveFile = new File(savePath+"//"+path);
		try {
			result1 = UploadFileUtils.saveFile(file.getInputStream(), saveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(result1) {
			result2 = excelInfoService.addExcel(excelInfo);
		}
		modelAndView.setViewName("redirect:/fu/f_stuchangehotel");
		return modelAndView;
	}
	
	/**
	 * 根据id删除excel信息
	 */
	@RequestMapping("/deleteExcelById")
	public void deleteExcelById(@RequestParam("delRowsIdArray[]") int []delRowsIdArray, HttpServletResponse response) {
		List<Integer> idList = new ArrayList<Integer>();
		ResultMessage resultMessage = new ResultMessage();
		for(Integer id : delRowsIdArray){
			idList.add(id);
		}
		boolean result = excelInfoService.deleteExcelById(idList);
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("删除Excel信息成功！"); 
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("删除Excel信息失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 管理员查找所有的Excel信息(未审批)
	 */
	@RequestMapping("/findAllExcel")
	public void findAllExcelInfo(HttpServletResponse response) {
		List<ExcelInfo> infos = excelInfoService.findAllExcel();
		int total = SystemContext.getTotal();
		if(total < 0) {
			total = infos.size();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", infos);
		JSONUtils.toJSON(map, response);
	}
	
	/**
	 * 根据教工号查找其所提交的excel(已登录)
	 */
	@RequestMapping("/findExcelInfoByTecNum")
	public void findExcelInfoByTecNum(HttpServletRequest request, HttpServletResponse response) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		if(teacher == null) {
			return;
		}
		List<ExcelInfo> infos = excelInfoService.findExcelByTecNum(teacher.getTecNum());
		int total = SystemContext.getTotal();
		if(total < 0) {
			total = infos.size();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", infos);
		JSONUtils.toJSON(map, response);
	}
	
	/**
	 * 审批通过Excel
	 */
	@RequestMapping("/approved")
	public void approvedExcel(@RequestParam("delRowsIdArray[]") int []delRowsIdArray, HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		List<Integer> list = new ArrayList<Integer>();
		for(int id : delRowsIdArray) {
			list.add(id);
		}
		
		boolean result = excelInfoService.changeExcelStatus(list, admin.getId(), 2);
		ResultMessage resultMessage = new ResultMessage();
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("审批成功！");
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("审批失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 审批拒绝Excel
	 */
	@RequestMapping("/refuse")
	public void refuseExcel(@RequestParam("delRowsIdArray[]") int []delRowsIdArray, HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		List<Integer> list = new ArrayList<Integer>();
		for(int id : delRowsIdArray) {
			list.add(id);
		}
		
		boolean result = excelInfoService.changeExcelStatus(list, admin.getId(), 3);
		ResultMessage resultMessage = new ResultMessage();
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("审批成功！");
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("审批失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	
}
