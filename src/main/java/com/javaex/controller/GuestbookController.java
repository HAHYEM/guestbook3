package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list");
		List<GuestbookVo> list = guestbookDao.getList();
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("insert");
		guestbookDao.insert(guestbookVo);
		System.out.println(guestbookVo.toString());
		return "redirect:/list";
	}
	
	@RequestMapping(value="/deleteform",method=RequestMethod.GET)
	public String deleteform(Model model, @RequestParam("no") int no) {
		System.out.println("deleteform");
		model.addAttribute("no", no);
		return "deleteform";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo guestbookVo, @RequestParam("no") int no) {
		System.out.println("delete");
		GuestbookVo gvo = guestbookDao.select(guestbookVo.getNo());
		System.out.println(gvo.toString());
			
		System.out.println(guestbookVo.toString());
		if(guestbookVo.getPassword().equals(gvo.getPassword())) {
			guestbookDao.delete(no);
		}	
			System.out.println("다시 입력하삼");
			return "redirect:/list";
		
	}
	
	
	
	
	
	
	
}
