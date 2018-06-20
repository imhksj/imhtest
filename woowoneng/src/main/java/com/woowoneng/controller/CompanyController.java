package com.woowoneng.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Certify;
import com.woowoneng.entity.Client;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.service.CompanyService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CompanyController.java
 * 기        능 : 회사소개 Controller
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
@RequestMapping({ "/company/" })
public class CompanyController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CompanyService companyService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 회사소개 > 인증현황 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "certificicate.do")
	public ModelAndView certifyList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		List<Certify> certifyList = companyService.getCertifySelectList();
		
		view.addObject("certifyList", certifyList);
		
		view.setViewName("company/certificicate");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 회사소개 > 주요고객 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "client.do")
	public ModelAndView clientList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		List<Client> clientList = companyService.getClientSelectList();
		
		view.addObject("clientList", clientList);
		
		view.setViewName("company/client");
		
		return view;
		
	}
}
