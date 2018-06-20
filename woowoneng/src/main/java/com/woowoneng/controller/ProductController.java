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

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Category;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Product;
import com.woowoneng.service.CategoryService;
import com.woowoneng.service.CommonFileService;
import com.woowoneng.service.ProductService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : ProductController.java
 * 기        능 : 상품 Controller
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
@RequestMapping({ "/product/" })
public class ProductController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CommonFileService commonFileService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 상품 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product_list.do")
	public ModelAndView prodList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int categoryTopIdx = param.getInt("category1");
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 48;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Product> productList = productService.getSelectList(param, reqPage, pageSize);
		
		view = productList.setPageModel(view, productList, pageSize);
		
		view.addObject("productList", productList);
		
		param.put("category1", param.getInt("category1"));
		param.put("category2", param.getInt("category2"));
		param.put("category3", param.getInt("category3"));
		//카테고리 로케이션 정보
		String categoryLocation = categoryService.getCategoryLocation(param);
		view.addObject("categoryLocation", categoryLocation);
		
		param.clear();
		param.put("cType", "PRODUCT");
		param.put("topIdx", categoryTopIdx);
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategory(param);
		view.addObject("cateList", cateList);
		
		String[] cateName = categoryLocation.split("/");
		view.addObject("cateName", cateName[cateName.length-1]);
		
		view.setViewName("product/product_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 상품 > 리스트 Ajx
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "productListAjx.do")
	public ModelAndView prodListAjx(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 48;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Product> productList = productService.getSelectList(param, reqPage, pageSize);
		
		view = productList.setPageModel(view, productList, pageSize);
		
		view.addObject("productList", productList);
		
		param.put("category1", param.getInt("category1"));
		param.put("category2", param.getInt("category2"));
		param.put("category3", param.getInt("category3"));
		//카테고리 로케이션 정보
		String categoryLocation = categoryService.getCategoryLocation(param);
		view.addObject("categoryLocation", categoryLocation);
		
		param.clear();
		param.put("cType", "PRODUCT");
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategory(param);
		view.addObject("cateList", cateList);
		
		view.setViewName("product/product_list_ajx.ajx");
		
		return view;
		
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * 상품 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product_search_list.do")
	public ModelAndView prodSearchList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int categoryTopIdx = param.getInt("category1");
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 12;							//페이지당 뿌려질 리스트 갯수

		PagedList<Product> productList = productService.getSelectList(param, reqPage, pageSize);
		
		view = productList.setPageModel(view, productList, pageSize);
		
		view.addObject("productList", productList);
				
		view.setViewName("product/product_search_list");
		
		return view;
		
	}
	
}
