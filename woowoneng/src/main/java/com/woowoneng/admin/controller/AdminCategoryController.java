package com.woowoneng.admin.controller;

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

import com.woowoneng.admin.service.AdminProductService;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Category;
import com.woowoneng.service.CategoryService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminCategoryController.java
 * 기        능 : 카테고리관리 Controller
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
@RequestMapping({ "/admin/category/" })
public class AdminCategoryController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 카테고리관리
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "category.do")
	public ModelAndView category(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		//카테고리 분류코드가 있으면 카테고리 정보를 담는다
		if(!"".equals(param.getString("cType"))){
			
			param.put("depthNum", 1);
			
			//카테고리 리스트
			List<Category> cateList = categoryService.getCategoryDepth(param);
			view.addObject("cateList", cateList);
			
		}
		
		view.setViewName("admin/category/category");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 카테고리관리 > 뎁스별 카테고리 리스트 호출
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getCategoryDepth.do")
	public ModelAndView getCategoryDepth(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategoryDepth(param);
		view.addObject("cateList", cateList);
		
		view.setViewName("jsonView");
		
		return view;
		
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 카테고리관리 > 카테고리추가
	 * @param Category
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addCategory.do")
	public ModelAndView addCategory(@Valid Category category, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "저장되었습니다.";

        if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "잘못된 데이타입니다.");
			return view;            
        }
		
       int cnt = categoryService.addCategory(category);
       
       //1depth 일때 자신으 topIdx를 idx로 업데이트 
       if(cnt > 0 && category.getTopIdx() <= 0){
    	   
    	   categoryService.updateTopIdx(category);
    	   
       }
       
       if(cnt <= 0){
    	   RETURN_CODE = "0002";
    	   RETURN_MSG = "저장에 실패하였습니다.";
       }
       
       view.addObject("RETURN_CODE", RETURN_CODE);
       view.addObject("RETURN_MSG", RETURN_MSG);
       view.addObject("IDX", category.getIdx());
       
       return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 카테고리관리 > 카테고리수정
	 * @param Category
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiCategory.do")
	public ModelAndView modiCategory(Category category, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";

        if (param.getInt("idx") <= 0 || "".equals(param.getString("cName"))) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "잘못된 데이타입니다.");
			return view;            
        }
		
       int cnt = categoryService.modiCategory(category);

       if(cnt <= 0){
    	   RETURN_CODE = "0002";
    	   RETURN_MSG = "수정에 실패하였습니다.";
       }
       
       view.addObject("RETURN_CODE", RETURN_CODE);
       view.addObject("RETURN_MSG", RETURN_MSG);
       
       return view;
		
	}
	
}
