package com.madhudevs.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Properties;
import org.hibernate.HibernateException;


public class CommonFunctions {
	/* checks if the value is not empty or not null and returns true if valid and false if not valid */
	public static boolean isValid(String str) {
		if(str != null && !str.isEmpty())
			return true;
		return false;
	}

	/* checks if the value is valid and returns assigned value if invalid */
	public static String assignValueIfInvalid(String checkValue,String assignValue) {
		if(isValid(checkValue))
			return assignValue;
		else
			return checkValue;
	}

	
	public String getUniqueId() {
		long prefix = 0;
		String suffix="";
		String uniqueID="";
		try {
			suffix =new String(Base64.getDecoder().decode("MTkwNQ==".getBytes()));
			prefix= (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
			uniqueID =suffix+prefix+"";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uniqueID;
	}
	
	public Date getDateFromString(String dateString,String formatString) {
		Date date=null;
		try {
			date = new SimpleDateFormat(formatString).parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return date;
	}

	public String getStringdateFromDate(Date date,String formatString) {
		String dateString="";
		try {
			dateString = new SimpleDateFormat(formatString).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;
	}

	public String changeDateFormat(String fromDate,String fromFormat,String toFormat) {
		String dateString="";
		try {
			dateString= new SimpleDateFormat(toFormat).format(new SimpleDateFormat(fromFormat).parse(fromDate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateString;
	}
	
	
	public String getFromProperty(String file,String property) {
		String value="";
		InputStream inputStream;
		try {
			Properties prop = new Properties();
			String propFileName = file+".properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} 
			value=prop.getProperty(property);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public Connection getConnection() {
		Connection connection=null;
		try {
			final String JDBC_DRIVER = getFromProperty("General", "JDBC_DRIVER");
			final String DB_URL = getFromProperty("General", "DB_URL");
			final String USER = getFromProperty("General", "USER");
			final String PASS = getFromProperty("General", "PASSWORD");

			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	

	public static void main(String[] args) {
		try {
			
	
		
		
		
		} catch (HibernateException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
