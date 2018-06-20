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
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Product;
import com.woowoneng.service.CategoryService;
import com.woowoneng.service.CommonFileService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminMemberController.java
 * 기        능 : 어드민 상품관리 Controller
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
@RequestMapping({ "/admin/product/" })
public class AdminProductController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AdminProductService adminProductService;
	
	@Autowired
	private CommonFileService commonFileService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 상품관리 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product_list.do")
	public ModelAndView prodList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 9;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Product> productList = adminProductService.getSelectList(param, reqPage, pageSize);
		
		view = productList.setPageModel(view, productList, pageSize);
		
		view.addObject("productList", productList);
		
		param.clear();
		param.put("cType", "PRODUCT");
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategory(param);
		view.addObject("cateList", cateList);
		
		view.setViewName("admin/product/product_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 상품관리 > 등록, 수정 폼
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product_detail.do")
	public ModelAndView prodDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		//idx 정보가 있으면 상품정보 조회
		if(param.getInt("idx") > 0){
			param.put("idx", param.getInt("idx"));
			Product product = adminProductService.getSelect(param);
			view.addObject("product", product);
		}
		
		
		param.clear();
		param.put("cType", "PRODUCT");
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategory(param);
		view.addObject("cateList", cateList);
				
		view.setViewName("admin/product/product_detail");
		
		return view;
		
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 상품관리 > 등록
	 * @param Product
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addProduct.do")
	public ModelAndView addProd(@Valid Product product, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "저장되었습니다.";

        if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
        List<CommonFile> fileList = (List<CommonFile>) req.getAttribute("fileList");
        if(fileList.size() <= 0 || "".equals(fileList.get(0).getFileSysName())){
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "제품이미지는 필수 입력 항목입니다.");
			return view;
        }
        
        product.setCommonFile(fileList.get(0));
        product.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
        
      	//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(product.getCommonFile(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
        
		int cnt = adminProductService.addProd(product);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", product.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 상품관리 > 수정
	 * @param Product
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiProduct.do")
	public ModelAndView modiProd(@Valid Product product, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
		
        if (bindingResult.hasErrors() || product.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
      	List<CommonFile> fileList = (List<CommonFile>) req.getAttribute("fileList");
      	
      	//제품 이미지 수정이 있을시 파일 형식 검사
      	if(fileList.size() > 0 || !"".equals(fileList.get(0).getFileSysName())){

      		product.setCommonFile(fileList.get(0));
      		
      		//파일 type 검사
          	if(!CommonUtil.getFileTypeValidate(product.getCommonFile(), "image")){
    			view.addObject("RETURN_CODE", "0002");
    			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
    			view.setViewName("jsonView");
    			return view;
    		}
      		
        }
      	
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		product.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		
		int cnt = adminProductService.modiProd(product, param);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "수정에 실패하였습니다.";
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 상품관리 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delProduct.do")
	public ModelAndView delProd(HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";
		      	
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminProductService.delProd(param);
		
		if(cnt < 0){
			RETURN_CODE = "0001";
			RETURN_MSG = "삭제에 실패하였습니다.";
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
}
