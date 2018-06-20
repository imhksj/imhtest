package com.woowoneng.admin.controller;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woowoneng.admin.service.AdminLoginService;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Admin;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminLoginController.java
 * 기        능 : 어드민 로그인 Controller
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

@Controller
@RequestMapping({ "/admin/" })
public class AdminLoginController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AdminLoginService adminLoginService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 로그인 > 로그인 체크
	 * @param Admin
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "loginProc.do")
	public ModelAndView loginProc(Admin admin, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "로그인 되었습니다.";
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
				
		if(admin.getAdminId() == null || admin.getAdminPassword() == null){
			view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "잘못된 접근입니다.");
			return view;
		}

		admin = adminLoginService.getSelect(admin);
		
		if( admin == null ){
			RETURN_CODE = "0001";
			RETURN_MSG = "계정이 없거나 패스워드가 잘못되었습니다.";
		}else{
			req.getSession().setAttribute("admin", admin);
			req.getSession().setAttribute("adminIdx", admin.getIdx());
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 로그아웃
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "logoutProc.do")
	public ModelAndView logoutProc(HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
				
		req.getSession().removeAttribute("admin");
		req.getSession().removeAttribute("adminIdx");
		
		view.addObject("RETURN_CODE", "0000");
		view.addObject("RETURN_MSG", "로그아웃 되었습니다.");
		
		return view;
		
	}
	
}
