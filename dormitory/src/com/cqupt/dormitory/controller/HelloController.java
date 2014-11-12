package com.cqupt.dormitory.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Room;
/**
 * Spring-Mybatis整合测试 
 * @author Bern
 *
 */
@Controller
public class HelloController {
	@Resource(name="roomDaoImpl")
	private RoomDao roomDao;
	
	@RequestMapping("test")
	public ModelAndView hello(String roomId) {
//		System.out.println("roomId:"+roomId);
		Room room = roomDao.findByRoomId(roomId);
		ModelAndView modelAndView = new ModelAndView();
		if(room != null) {
			modelAndView.addObject("room", room);
		} else {
			modelAndView.addObject("notFound", "没有找到该房间！");
		}
		modelAndView.setViewName("findroom");
		return modelAndView;
	}
}
