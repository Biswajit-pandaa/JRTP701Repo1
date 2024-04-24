package com.nt.interceptor;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class TimeCheckInterceptor implements HandlerInterceptor {
	
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		if(req.getServletPath().equalsIgnoreCase("/")) { // Do not apply this logic for home page
			return true;
		}
		
		// get system Date and Time
		LocalDateTime ldt = LocalDateTime.now();
		// get current hour of the day
		int hour = ldt.getHour();
		if(hour>9 && hour < 17) {
			RequestDispatcher rd = req.getRequestDispatcher("/timeout.jsp");
			rd.forward(req, res);
			return false;
		}
		
		return true;
		// TODO Auto-generated method stub
//		return HandlerInterceptor.super.preHandle(req, res, handler);
	}
	
}
