package com.woowoneng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Member;
import com.woowoneng.service.MemberService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : MemberLoginController.java
 * 기        능 : 메인 Controller
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
@RequestMapping({"/member/"})
public class MemberLoginController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private MemberService memberService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 회원 > 로그인
	 * @param Admin
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "loginProc.do")
	public ModelAndView loginProc(Member member, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		String RETURN_CODE = "0000";
		String RETURN_MSG = "로그인 되었습니다.";
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
				
		if(member.getMemberId() == null || member.getMemberPwd() == null){
			view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "잘못된 접근입니다.");
			return view;
		}

		member = memberService.getSelectMember(member);
		
		if( member == null ){
			RETURN_CODE = "0001";
			RETURN_MSG = "계정이 없거나 패스워드가 잘못되었습니다.";
		}else{
			req.getSession().setAttribute("member", member);
			req.getSession().setAttribute("companyName", member.getCompanyName());
			req.getSession().setAttribute("memberIdx", member.getIdx());
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 회원 > 로그아웃
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "logoutProc.do")
	public ModelAndView logoutProc(HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
				
		req.getSession().removeAttribute("member");
		req.getSession().removeAttribute("memberIdx");
		
		view.addObject("RETURN_CODE", "0000");
		view.addObject("RETURN_MSG", "로그아웃 되었습니다.");
		
		return view;
		
	}
	
}
