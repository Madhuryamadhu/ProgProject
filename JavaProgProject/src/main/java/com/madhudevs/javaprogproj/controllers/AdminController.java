package com.madhudevs.javaprogproj.controllers;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class AdminController {
	private static final Logger logger = LogManager.getLogger();
	
	@RequestMapping(value = "/goToPage", method = RequestMethod.POST)
	public String goToPage(AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response,Map<String,Object> map) {
		logger.info("Inside goToPage method:-"+bean.toString());
		try {
			map.put("parameters", bean.getParameters());
			return ((String)bean.getPagePath()).replace(".jsp", "");
			
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/getProgramsForAdmin", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean getProgramsForAdmin(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getProgramsForAdmin method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.getProgramsForAdmin(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from getProgramsForAdmin method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/loadPagramDetailsForModify", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean loadPagramDetailsForModify(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside loadPagramDetailsForModify method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.loadPagramDetailsForModify(bean)) {
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
	
	@RequestMapping(value = "/modifyProgram", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean modifyProgram(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside modifyProgram method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.modifyProgram(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from modifyProgram method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/deleteProgram", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean deleteProgram(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside deleteProgram method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.deleteProgram(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from deleteProgram method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/hideShowProgram", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean hideShowProgram(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside hideShowProgram method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.hideShowProgram(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from hideShowProgram method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/createProgram", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean createProgram(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside createProgram method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.createProgram(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from createProgram method:-"+bean.toString());
		return bean;
	}
	
	
}
