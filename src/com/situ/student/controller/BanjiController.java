package com.situ.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.situ.student.pojo.Banji;
import com.situ.student.pojo.Kecheng;
import com.situ.student.pojo.Student;
import com.situ.student.service.IStudentService;
import com.situ.student.service.StudentServiceImpl;
import com.situ.student.vo.PageBean;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.sun.xml.internal.bind.v2.model.core.ID;

@Controller
@RequestMapping(value="/banji")
public class BanjiController {

	IStudentService studentService = new StudentServiceImpl();
	
	@RequestMapping(value="/searchBanjiKecheng")
	public ModelAndView searchBanjiKecheng(Integer banjiSearch, ModelAndView modelAndView, String pageIndex, String pageSize){
		if (banjiSearch == null) {
			modelAndView.setViewName("redirect:/banji/findBanjiKecheng.action");
			return modelAndView;
		}
		int pageIndex1 = 1;
		if (pageIndex!= null && !pageIndex.equals("")) {
			pageIndex1 = Integer.parseInt(pageIndex);
		}
		int pageSize1 = 3;
		if (pageSize != null && !pageSize.equals("")) {
			pageSize1 = Integer.parseInt(pageSize);
		}
		IStudentService studentService = new StudentServiceImpl();
		PageBean pageBean = studentService.getPageBeanBanjiKechengSearch(pageIndex1,pageSize1,banjiSearch);
		System.out.println(pageBean);
		modelAndView.addObject("list", pageBean);
		List<Banji> list = studentService.findBanji();
		modelAndView.addObject("banjiList", list);
		modelAndView.addObject("banjiSearch_id", banjiSearch);
		modelAndView.setViewName("findBanjiKechengJsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteBanjiKecheng")
	public String deleteBanjiKecheng(Integer banji_id, Integer kecheng_id){
		studentService.deleteBanjiKecheng(banji_id, kecheng_id);
		return "redirect:/banji/findBanjiKecheng.action";
	}
	
	@RequestMapping(value="/findBanjiKecheng")
	public ModelAndView findBanjiKecheng(ModelAndView modelAndView, String pageIndex, String pageSize){
		int pageIndex1 = 1;
		if (pageIndex!= null && !pageIndex.equals("")) {
			pageIndex1 = Integer.parseInt(pageIndex);
		}
		int pageSize1 = 3;
		if (pageSize != null && !pageSize.equals("")) {
			pageSize1 = Integer.parseInt(pageSize);
		}
		PageBean pageBean = studentService.getPageBeanBanjiKecheng(pageIndex1,pageSize1);
		System.out.println(pageBean);
		modelAndView.addObject("list", pageBean);
		List<Banji> list = studentService.findAllBanji();
		modelAndView.addObject("banjiList", list);
		modelAndView.setViewName("findBanjiKechengJsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/addJiaowu2")
	public String addJiaowu2(Integer banji, Integer kecheng, ModelAndView modelAndView){
		studentService.addBanjiKecheng(banji, kecheng);
		return "redirect:/banji/findJiaowu.action";
	}
	
	@RequestMapping(value="/addJiaowu")
	public ModelAndView addJiaowu(ModelAndView modelAndView){
		List<Banji> listBanji = studentService.findAllBanji();
		List<Kecheng> listKecheng = studentService.findAllKecheng();
		modelAndView.addObject("banjiList", listBanji);
		modelAndView.addObject("kechengList", listKecheng);
		modelAndView.setViewName("addJiaowuJsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/searchstudent")
	public ModelAndView searchStudent(Integer studentSearch, ModelAndView modelAndView, String pageIndex, String pageSize){
		if (studentSearch == null) {
			modelAndView.setViewName("redirect:/banji/findJiaowu.action");
			return modelAndView;
		}
		int pageIndex1 = 1;
		if (pageIndex!= null && !pageIndex.equals("")) {
			pageIndex1 = Integer.parseInt(pageIndex);
		}
		int pageSize1 = 3;
		if (pageSize != null && !pageSize.equals("")) {
			pageSize1 = Integer.parseInt(pageSize);
		}
		IStudentService studentService = new StudentServiceImpl();
		PageBean pageBean = studentService.getPageBeanJiaowuBanjiSearch(pageIndex1,pageSize1,studentSearch);
		System.out.println(pageBean);
		modelAndView.addObject("list", pageBean);
		List<Student> list = studentService.findAllStudent();
		modelAndView.addObject("studentList", list);
		modelAndView.addObject("studentSearch", studentSearch);
		modelAndView.setViewName("findJiaowuJsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/findJiaowu")
	public ModelAndView findJiaowu(ModelAndView modelAndView, String pageIndex, String pageSize){
		int pageIndex1 = 1;
		if (pageIndex!= null && !pageIndex.equals("")) {
			pageIndex1 = Integer.parseInt(pageIndex);
		}
		int pageSize1 = 3;
		if (pageSize != null && !pageSize.equals("")) {
			pageSize1 = Integer.parseInt(pageSize);
		}
		PageBean pageBean = studentService.getPageBeanJiaowuBanji(pageIndex1,pageSize1);
		System.out.println(pageBean);
		modelAndView.addObject("list", pageBean);
		List<Student> list = studentService.findAllStudent();
		modelAndView.addObject("studentList", list);
		
		
		modelAndView.setViewName("findJiaowuJsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/checkKechengName")
	public void checkKechengName(String name, HttpServletRequest request, HttpServletResponse resp) throws IOException{
		boolean isExit = studentService.checkKechengName(name);
		resp.setContentType("charset=utf-8");
		resp.getWriter().write("{\"isExit\":" + isExit + "}");
	}
	
	@RequestMapping(value="/updateKecheng2")
	public String updateKecheng2(Integer id, String name, Integer credit){
		Kecheng kecheng = new Kecheng(id, name, credit);
		studentService.updateKecheng(kecheng);
		return "redirect:/banji/findKecheng.action";
	}
	
	@RequestMapping(value="/updateKecheng")
	public ModelAndView updateKecheng(Integer id, ModelAndView modelAndView){
		Kecheng kecheng = studentService.findKechengById(id);
		modelAndView.addObject("kecheng", kecheng);
		modelAndView.setViewName("updateKechengJsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteKecheng")
	public String deleteKecheng(Integer id){
		studentService.deleteKecheng(id);
		return "redirect:/banji/findKecheng.action";
	}
	
	@RequestMapping(value="/addKecheng2")
	public String addKecheng2(String name, Integer credit){
		Kecheng kecheng = new Kecheng(name, credit);
		studentService.addKecheng(kecheng);
		return "redirect:/banji/findKecheng.action";
	}
	
	@RequestMapping(value="/addKecheng")
	public String addKecheng(){
		return "addKechengJsp";
	}
	
	@RequestMapping(value="/checkBanjiName")
	public void checkBanjiName(String name, HttpServletRequest request, HttpServletResponse resp) throws IOException{
		boolean isExit = studentService.checkBanjiName(name);
		resp.setContentType("charset=utf-8");
		resp.getWriter().write("{\"isExit\":" + isExit + "}");
	}
	
	@RequestMapping(value="/updateBanji2")
	public String updateBanji2(Integer id, String name){
		Banji banji = new Banji(id, name);
		studentService.updateBanji(banji);
		return "redirect:/banji/findBanji.action";
	}
	
	@RequestMapping(value="/updateBanji")
	public ModelAndView updateBanji(Integer id, ModelAndView modelAndView){
		Banji banji = studentService.findBanjiById(id);
		modelAndView.addObject("banji", banji);
		modelAndView.setViewName("updateBanjiJsp");
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteBanji")
	public String deleteBanji(Integer id){
		studentService.deleteBanji(id);
		return "redirect:/banji/findBanji.action";
	}
	
	@RequestMapping(value="/addBanji2")
	public String addBanji2(String name){
		Banji banji = new Banji(name);
		studentService.addBanji(banji);
		return "redirect:/banji/findBanji.action";
	}
	
	@RequestMapping(value="/addBanji")
	public String addBanji(){
		return "addBanjiJsp";
	}
	
	@RequestMapping(value="/findKecheng")
	public ModelAndView findKecheng(String pageIndex, String pageSize,ModelAndView modelAndView ){
		
		int pageIndex1 = 1;
		if (pageIndex!= null && !pageIndex.equals("")) {
			pageIndex1 = Integer.parseInt(pageIndex);
		}
		int pageSize1 = 3;
		if (pageSize != null && !pageSize.equals("")) {
			pageSize1 = Integer.parseInt(pageSize);
		}
		PageBean pageBean = studentService.getPageBeanKecheng(pageIndex1,pageSize1);
		System.out.println(pageBean);
		modelAndView.addObject("list", pageBean);
		modelAndView.setViewName("findKechengJsp");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/findBanji")
	public ModelAndView findBanji(String pageIndex, String pageSize,ModelAndView modelAndView ){
		
		int pageIndex1 = 1;
		if (pageIndex!= null && !pageIndex.equals("")) {
			pageIndex1 = Integer.parseInt(pageIndex);
		}
		int pageSize1 = 3;
		if (pageSize != null && !pageSize.equals("")) {
			pageSize1 = Integer.parseInt(pageSize);
		}
		PageBean pageBean = studentService.getPageBeanBanji(pageIndex1,pageSize1);
		System.out.println(pageBean);
		modelAndView.addObject("list", pageBean);
		modelAndView.setViewName("findBanjiJsp");
		return modelAndView;
	}
}
