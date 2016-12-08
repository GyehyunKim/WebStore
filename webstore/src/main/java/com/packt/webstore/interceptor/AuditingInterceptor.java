package com.packt.webstore.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuditingInterceptor extends HandlerInterceptorAdapter {
	
	Logger logger = Logger.getLogger("auditLogger");
	String user;
	String productId; 
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object handler) {
		
		System.out.println("Hello, This is preHandle1()");
		System.out.println(request.getRequestURI());
		System.out.println(request.getMethod().equals("POST"));
		if (request.getRequestURI().endsWith("/products/add") && request.getMethod().equals("POST")) {
			System.out.println("Hello, This is preHandle2()");
			user = request.getRemoteUser();
			productId = request.getParameterValues("productId")[0];
		}
		return true;
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		if (request.getRequestURI().endsWith("/products/add") && response.getStatus() == 302) {
			logger.info(String.format("A new product[%s] added by %s on %s", productId, user, getCurrentTime()));
		}
	}

	private String getCurrentTime() {
		// 
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm:ss");
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTimeInMillis(System.currentTimeMillis());
	    return formatter.format(calendar.getTime());	 
	}
}
