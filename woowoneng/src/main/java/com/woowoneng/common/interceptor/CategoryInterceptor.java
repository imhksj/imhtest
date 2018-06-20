package com.woowoneng.common.interceptor;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Admin;
import com.woowoneng.entity.Category;
import com.woowoneng.service.CategoryService;

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

public class CategoryInterceptor extends HandlerInterceptorAdapter{

	Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object handler) throws Exception {
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(request);
		param.clear();
		param.put("cType", "CATALOG");
		
		List<Category> catalogCateList = categoryService.getCategory(param);
		
		param.clear();
		param.put("cType", "PRODUCT");
		param.put("depthNum", "1");
		List<Category> productCateList = categoryService.getCategoryDepth(param);
		
		modelAndView.addObject("catalogCateList", catalogCateList);
		modelAndView.addObject("productCateList", productCateList);
		
		super.postHandle(request, response, handler, modelAndView);
		
	}
	
}
