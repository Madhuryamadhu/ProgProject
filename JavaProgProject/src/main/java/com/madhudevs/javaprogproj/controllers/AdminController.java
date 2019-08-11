package com.madhudevs.javaprogproj.controllers;


import java.util.HashMap;
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
public class AdminController {
	private static final Logger logger = LogManager.getLogger();
	
	
	/*==================================LOGIN SECTION================================================*/
	@RequestMapping(value = "/loadLoginPage", method = RequestMethod.GET)
	public String loadLoginPage(AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response,Map<String,Object> map) {
		logger.info("Inside loadLoginPage method:-"+bean.toString());
		HashMap<String, String> loginMap=new HashMap<String, String>();
		try {
			loginMap.put("isAdminLogin", "true");
			map.put("parameters", loginMap);
			return "/adminPages/adminMainPage";
			
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}
		return null;
	}
	
	
	@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean adminLogin(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		logger.info("Inside adminLogin method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		int loginResult=-1;
		try {
			//loginResult [1:username and password wrong] ,[2:admin is inactive], [0:login success]
			loginResult=dao.adminLogin(bean,session);
			bean.setStatus(loginResult);
			
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}
		logger.info("returning from adminLogin method:-"+bean.toString());
		return bean;
	}
	
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
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean logout(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		logger.info("Inside login method:-"+bean.toString());
		try {
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("Returning from login method:-"+bean.toString());
		return bean;
	}
	
	
	/*==================================PROGRAM SECTION================================================*/
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
	
	
	
	/*==================================TYPES SECTION================================================*/
	@RequestMapping(value = "/getTypesForAdmin", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean getTypesForAdmin(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getTypesForAdmin method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.getTypesForAdmin(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from getTypesForAdmin method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/createType", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean createType(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside createType method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.createType(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from createType method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/loadTypeForModify", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean loadTypeForModify(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside loadTypeForModify method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.loadTypeFOrModify(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from loadTypeForModify method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/modifyType", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean modifyType(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside modifyType method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.modifyType(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from modifyType method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/deleteType", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean deleteType(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside deleteType method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.deleteType(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from deleteType method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/hideShowType", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean hideShowType(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside hideShowType method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.hideShowType(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from hideShowType method:-"+bean.toString());
		return bean;
	}
	
	
	
	/*==================================ADMIN SECTION================================================*/
	@RequestMapping(value = "/getAdminList", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean getAdminList(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside getAdminList method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.getAdminList(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from getAdminList method:-"+bean.toString());
		return bean;
	}
	
	
	@RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean createAdmin(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside createAdmin method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.createAdmin(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from createAdmin method:-"+bean.toString());
		return bean;
	}
	
	
	
	@RequestMapping(value = "/deactivateAdmin", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean deactivateAdmin(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside deactivateAdmin method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.deactivateAdmin(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from deactivateAdmin method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/deleteAdmin", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean deleteAdmin(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside deleteAdmin method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.deleteAdmin(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from deleteAdmin method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/loadAdminForModify", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean loadAdminForModify(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside loadAdminForModify method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.loadAdminForModify(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from loadAdminForModify method:-"+bean.toString());
		return bean;
	}
	
	@RequestMapping(value = "/modifyAdmin", method = RequestMethod.POST)
	public @ResponseBody AdminPagesBean modifyAdmin(@RequestBody AdminPagesBean bean,HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside modifyAdmin method:-"+bean.toString());
		AdminPageDAO dao= new AdminPageDAO();
		try {
			if(dao.modifyAdmin(bean)) {
				bean.setStatus(0);
			}else {
				bean.setStatus(1);
			}
		} catch (Exception e) {
			logger.error("Exception occured",e);
		}

		logger.info("Returning from modifyAdmin method:-"+bean.toString());
		return bean;
	}
}

