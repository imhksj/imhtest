package com.woowoneng.common.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : GlobalExceptionHandler.java
 * 기        능 : 모든 Exception 처리 핸들러  
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

@ControllerAdvice
public class GlobalExceptionHandler {

	Log log = LogFactory.getLog(this.getClass());
	
	/*
	 * @author MyungHo Lim
	 * 
     * Exception이 일어나면 에러페이지로로 이동
     * @param 	HttpServletRequest
	 * @param 	HttpServletResponse
	 * @param 	Exception
	 * @return 	ModelAndView
	 * @throws Exception
     */
	
	@ExceptionHandler(Exception.class)
	public ModelAndView error(HttpServletRequest req, HttpServletResponse rep, Exception e) throws Exception {

		log.warn(e);
		
		System.out.println(e.toString());
		
		ModelAndView view = new ModelAndView();
		
		view.addObject("message", "Sorry, Site Error");
		
		view.setViewName("error/error");
		
		return view;
		
	}
	
}
