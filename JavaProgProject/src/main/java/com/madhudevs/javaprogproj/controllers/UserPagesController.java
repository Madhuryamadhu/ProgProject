package com.madhudevs.javaprogproj.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.madhudevs.javaprogproj.adminPages.AdminPageDAO;
import com.madhudevs.javaprogproj.adminPages.AdminPagesBean;
import com.madhudevs.javaprogproj.userSection.UserPagesBean;
import com.madhudevs.javaprogproj.userSection.UserPagesDAO;

@Controller
public class UserPagesController {
	private static final Logger logger = LogManager.getLogger();

	@RequestMapping(value = "/loadPages", method = RequestMethod.POST)
	public String getPage(@RequestBody UserPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getPage method:-"+bean.toString());
		try {
			if(bean.getPageId().equals("0")) { 
				bean.setStatus(0);
				return "/userPages/programList";
			}else if(bean.getPageId().equals("1")){
				bean.setStatus(0);
				return "/adminPages/adminMainPage";
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}
		logger.info("returning from getPage method:-"+bean.toString());
		return "programList";
	}

	@RequestMapping(value = "/getProgramList", method = RequestMethod.POST)
	public @ResponseBody UserPagesBean getProgramList(@RequestBody UserPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getProgramList method:-"+bean.toString());
		UserPagesDAO dao= new UserPagesDAO();
		try {
			if(dao.getProgramList(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from getProgramList method:-"+bean.toString());
		return bean;
	}

	@RequestMapping(value = "/loadPagramForView", method = RequestMethod.POST)
	public @ResponseBody UserPagesBean getPagramForView(@RequestBody UserPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getPagramForView method:-"+bean.toString());
		UserPagesDAO dao= new UserPagesDAO();
		try {
			if(dao.getPagramForView(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from getPagramForView method:-"+bean.toString());
		return bean;
	}

	@RequestMapping(value = "/getTypes", method = RequestMethod.POST)
	public @ResponseBody UserPagesBean getTypeDetails(@RequestBody UserPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getTypeDetails method:-"+bean.toString());
		UserPagesDAO dao= new UserPagesDAO();
		try {
			if(dao.getTypes(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from getTypeDetails method:-"+bean.toString());
		return bean;
	}

	@RequestMapping(value = "/getComplexity", method = RequestMethod.POST)
	public @ResponseBody UserPagesBean getComplexityDetails(@RequestBody UserPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getComplexityDetails method:-"+bean.toString());
		UserPagesDAO dao= new UserPagesDAO();
		try {
			if(dao.getComplexityDetails(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from getComplexityDetails method:-"+bean.toString());
		return bean;
	}


	@RequestMapping(value = "/getProgramViewPage", method = RequestMethod.POST)
	public String loadProgViewPage(@ModelAttribute("userForm")UserPagesBean bean,HttpServletRequest request, HttpServletResponse response,Map<String, Object> model) {
		logger.info("Inside loadProgViewPage method:-"+bean.toString());
		try {
			bean.setStatus(0);
			model.put("userForm", bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("returning from loadProgViewPage method:-"+bean.toString());
		return "/userPages/programView";
	}

	@RequestMapping(value = "/updateLikeDislike", method = RequestMethod.POST)
	public @ResponseBody UserPagesBean updateLikeDislike(@RequestBody UserPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside updateLikeDislike method:-"+bean.toString());
		UserPagesDAO dao= new UserPagesDAO();
		try {
			if(dao.updateLikeDislike(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from updateLikeDislike method:-"+bean.toString());
		return bean;
	}

}
