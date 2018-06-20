package com.woowoneng.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Exhibition;
import com.woowoneng.entity.Notice;
import com.woowoneng.entity.Press;
import com.woowoneng.entity.ProductCase;
import com.woowoneng.service.CategoryService;
import com.woowoneng.service.CustomerService;
import com.woowoneng.service.PrService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : MainController.java
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
@RequestMapping({""})
public class MainController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CustomerService customeService;
	
	@Autowired
	private PrService prService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회원관리 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"","/", "/main.do"})
	public ModelAndView memberList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = 1;							//현재 페이지
		int pageSize = 6;							//페이지당 뿌려질 리스트 갯수
		
		//공지사항 조회
		PagedList<Notice> noticeList = customeService.getSelectNoticeList(param, reqPage, pageSize);
		//언론보도 조회
		PagedList<Press> pressList = prService.getPressSelectList(param, reqPage, pageSize);
		
		pageSize = 2;
		//전시회
		PagedList<Exhibition> exhibitionList = prService.getExhibitionSelectList(param, reqPage, pageSize);
		//납품사례
		PagedList<ProductCase> productCaseList = prService.getProductCaseSelectList(param, reqPage, pageSize);
		
		view.addObject("noticeList", noticeList);
		view.addObject("pressList", pressList);
		view.addObject("exhibitionList", exhibitionList);
		view.addObject("productCaseList", productCaseList);
		
		view.setViewName("main");
		
		return view;
		
	}
	
}
