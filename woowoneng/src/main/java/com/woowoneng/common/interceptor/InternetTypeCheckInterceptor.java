package com.woowoneng.common.interceptor;

import java.io.File;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/*
 * web or mobile 분기 인터셉터
 * url path /m으로 시작하는 url에서는  viewName을  모바일로 셋팅
 */
public class InternetTypeCheckInterceptor extends HandlerInterceptorAdapter{

	Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object handler) throws Exception {
		
		req.setAttribute("AGENT", "MOBILE");
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		String viewName = modelAndView.getViewName();
		
		if(viewName.lastIndexOf(".") > -1 && viewName.lastIndexOf(".ajx") <= -1 )	viewName = viewName.substring(0, viewName.lastIndexOf("."));
		
		//리다이렉트 제외
        if(viewName.lastIndexOf("redirect") <= -1)	modelAndView.setViewName("mobile" + viewName);
        
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
