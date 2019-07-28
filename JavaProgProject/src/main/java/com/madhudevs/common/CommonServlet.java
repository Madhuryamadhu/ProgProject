package com.madhudevs.common;

import javax.servlet.http.HttpServlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger();
	/**
	 * 
	 */
	private static final long serialVersionUID = -8851360073800659196L;
	
	public void init() {
		logger.info(">>>>>>Inside CommonServlet");
		
		logger.info(">>>>>>Loading Hibernate configurations....");
		HibernateUtil.getSessionFactory();
	}

}
