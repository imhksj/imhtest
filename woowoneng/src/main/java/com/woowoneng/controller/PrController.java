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
import com.woowoneng.entity.Ad;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Exhibition;
import com.woowoneng.entity.Press;
import com.woowoneng.entity.ProductCase;
import com.woowoneng.service.PrService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : PrController.java
 * 기        능 : 어드민 홍보센터 Controller
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
@RequestMapping({ "/pr/" })
public class PrController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private PrService prService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 홍보센터 > 전시회 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exhibition_list.do")
	public ModelAndView exhibitionList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 9;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Exhibition> exhibitionList = prService.getExhibitionSelectList(param, reqPage, pageSize);
		
		view = exhibitionList.setPageModel(view, exhibitionList, pageSize);
		
		view.addObject("exhibitionList", exhibitionList);
		
		view.setViewName("pr/exhibition_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 홍보센터 > 전시회 > 상세
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "exhibition_detail.do")
	public ModelAndView exhibitionDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){
			
			Exhibition exhibition = prService.getExhibitionSelect(param);
			
			param.put("idx", exhibition.getIdx());
			Exhibition exhibitionNext = prService.getExhibitionSelectNext(param);
			Exhibition exhibitionPrev = prService.getExhibitionSelectPrev(param);
			
			view.addObject("exhibition", exhibition);
			view.addObject("exhibitionNext", exhibitionNext);
			view.addObject("exhibitionPrev", exhibitionPrev);
			
		}
		
		view.setViewName("pr/exhibition_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 홍보센터 > 언론보도 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "press_list.do")
	public ModelAndView pressList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 9;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Press> pressList = prService.getPressSelectList(param, reqPage, pageSize);
		
		view = pressList.setPageModel(view, pressList, pageSize);
		
		view.addObject("pressList", pressList);
		
		view.setViewName("pr/press_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 홍보센터 > 언론보도 > 상세
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "press_detail.do")
	public ModelAndView pressDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){
			
			Press press = prService.getPressSelect(param);
			
			param.put("idx", press.getIdx());
			Press pressNext = prService.getPressSelectNext(param);
			Press pressPrev = prService.getPressSelectPrev(param);
			
			view.addObject("press", press);
			view.addObject("pressNext", pressNext);
			view.addObject("pressPrev", pressPrev);
			
		}
		
		view.setViewName("pr/press_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 홍보센터 > 광고홍보 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "ad_list.do")
	public ModelAndView adList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 9;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Ad> adList = prService.getAdSelectList(param, reqPage, pageSize);
		
		view = adList.setPageModel(view, adList, pageSize);
		
		view.addObject("adList", adList);
		
		view.setViewName("pr/ad_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 홍보센터 > 광고홍보 > 상세
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "ad_detail.do")
	public ModelAndView adDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){
			
			Ad ad = prService.getAdSelect(param);
			
			param.put("idx", ad.getIdx());
			Ad adNext = prService.getAdSelectNext(param);
			Ad adPrev = prService.getAdSelectPrev(param);
			
			view.addObject("ad", ad);
			view.addObject("adNext", adNext);
			view.addObject("adPrev", adPrev);
			
		}
		
		view.setViewName("pr/ad_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 홍보센터 > 납품사례 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product_case_list.do")
	public ModelAndView productCaseList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 9;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<ProductCase> productCaseList = prService.getProductCaseSelectList(param, reqPage, pageSize);
		
		view = productCaseList.setPageModel(view, productCaseList, pageSize);
		
		view.addObject("productCaseList", productCaseList);
		
		view.setViewName("pr/product_case_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 홍보센터 > 납품사례 > 상세
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "product_case_detail.do")
	public ModelAndView productCaseDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){
			
			ProductCase productCase = prService.getProductCaseSelect(param);
			
			param.put("idx", productCase.getIdx());
			ProductCase productCaseNext = prService.getProductCaseSelectNext(param);
			ProductCase productCasePrev = prService.getProductCaseSelectPrev(param);
			
			view.addObject("productCase", productCase);
			view.addObject("productCaseNext", productCaseNext);
			view.addObject("productCasePrev", productCasePrev);
			
		}
		
		view.setViewName("pr/product_case_detail");
		
		return view;
		
	}
	
}
