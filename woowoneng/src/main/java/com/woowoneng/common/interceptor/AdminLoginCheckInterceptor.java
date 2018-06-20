package com.woowoneng.common.interceptor;

import java.io.File;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Admin;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminLoginCheckInterceptor.java
 * 기        능 : 관리자 로그인 여부 처리 Interceptor 핸들러  
 * -----------------------------------------------------------------------------
 *                              변경 사항				                     
 * -----------------------------------------------------------------------------
 *    변경일자       	                             변경자(작성자)                 	  변경 내역                 
 * -------------     	--------------------------       ------------------------
 * 2016. 12. 28                		임명호                                          최 초 작 성                          
 * ==============================================================================
 * 
 * @author MyungHo Lim
 *
 */

public class AdminLoginCheckInterceptor extends HandlerInterceptorAdapter{

	Log log = LogFactory.getLog(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object handler) throws Exception {
		
		Admin admin = (Admin) req.getSession().getAttribute("admin");
		
		if(admin == null || "".equals(StringUtil.nvl(admin.getAdminId())) ){
			
			rep.sendRedirect("/admin/login.do");
			
		}
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		super.postHandle(request, response, handler, modelAndView);
		
	}
	
}
