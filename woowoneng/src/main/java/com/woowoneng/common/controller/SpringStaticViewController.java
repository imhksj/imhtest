package com.woowoneng.common.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.dao.CategoryDao;
import com.woowoneng.entity.Category;
import com.woowoneng.service.CategoryService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : SpringStaticViewController.java
 * 기        능 : 컨트롤러에 정의되지 않은 단순 view페이지일경우 SimpleUrlHandlerMapping 사용  
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

public class SpringStaticViewController implements Controller{
	
	@Autowired
	private CategoryDao categoryDao;
	
	/*
	 * @author MyungHo Lim
	 * 
     * Controller없이 단순 뷰페이지 자동 맵핑 
	 * @param 	HttpServletRequest
	 * @param 	HttpServletResponse
	 * @return 	ModelAndView
	 * @throws Exception
     */
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse rep) throws Exception {

		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		param.clear();
		param.put("cType", "CATALOG");
		List<Category> catalogCateList = categoryDao.select(param);
		
		param.clear();
		param.put("cType", "PRODUCT");
		param.put("depthNum", "1");
		List<Category> productCateList = categoryDao.getCategoryDepth(param);
				
        ModelAndView mv = new ModelAndView();
        
        mv.addObject("catalogCateList", catalogCateList);
        mv.addObject("productCateList", productCateList);
        
        String viewName = CommonUtil.returnUrl(req, rep);
        
        mv.setViewName(viewName);

        return mv;
    }

}
