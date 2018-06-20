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

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Ad;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Exhibition;
import com.woowoneng.entity.Press;
import com.woowoneng.entity.ProductCase;
import com.woowoneng.admin.service.AdminPrService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminPrController.java
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
@RequestMapping({ "/admin/pr/" })
public class AdminPrController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AdminPrService adminPrService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 납품사례 > 리스트
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
		int pageSize = 12;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<ProductCase> productCaseList = adminPrService.getProductCaseSelectList(param, reqPage, pageSize);
		
		view = productCaseList.setPageModel(view, productCaseList, pageSize);
		
		view.addObject("productCaseList", productCaseList);
		
		view.setViewName("admin/pr/product_case_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 납품사례 > 등록, 수정 폼
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
			
			ProductCase productCase = adminPrService.getProductCaseSelect(param);
			
			//파일 하나씩 object로 따로 담기
			if(productCase != null && productCase.getCommonFileList() != null){
				int cnt = 0;
				for(CommonFile item : productCase.getCommonFileList()){
					
					if("LIST_IMG".equals(item.getFileType())){
						view.addObject(item.getFileType(), item);
					}else{
						view.addObject(item.getFileType() + (cnt++), item);
					}
					
				}
				
			}
			
			view.addObject("productCase", productCase);
			
		}
		
		view.setViewName("admin/pr/product_case_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 납품사례 > 등록
	 * @param ProductCase
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addProductCase.do")
	public ModelAndView addProductCase(@Valid ProductCase productCase, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "저장되었습니다.";
		
		if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		productCase.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		productCase.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
		
		//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(productCase.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
		
		int cnt = adminPrService.addProductCase(productCase);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", productCase.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 납품사례 > 수정
	 * @param ProductCase
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiProductCase.do")
	public ModelAndView modiProductCase(@Valid ProductCase productCase, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
				
		if (bindingResult.hasErrors() || productCase.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		productCase.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		productCase.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
		
		//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(productCase.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
		
		int cnt = adminPrService.modiProductCase(productCase, param);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "수정에 실패하였습니다.";
		}
		
		view.addObject("IDX", productCase.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 납품사례 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delProductCase.do")
	public ModelAndView delProductCase(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";

		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminPrService.delProductCase(param);
		
		if(cnt < 0){
			RETURN_CODE = "0001";
			RETURN_MSG = "삭제에 실패하였습니다.";
		}

		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 광고홍보 > 리스트
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
		int pageSize = 12;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Ad> adList = adminPrService.getAdSelectList(param, reqPage, pageSize);
		
		view = adList.setPageModel(view, adList, pageSize);
		
		view.addObject("adList", adList);
		
		view.setViewName("admin/pr/ad_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 광고홍보 > 등록, 수정 폼
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
			
			Ad ad = adminPrService.getAdSelect(param);
			
			//파일 하나씩 object로 따로 담기
			if(ad != null && ad.getCommonFileList() != null){
				int cnt = 0;
				for(CommonFile item : ad.getCommonFileList()){
					
					if("LIST_IMG".equals(item.getFileType())){
						view.addObject(item.getFileType(), item);
					}else{
						view.addObject(item.getFileType() + (cnt++), item);
					}
					
				}
				
			}
			
			view.addObject("ad", ad);
			
		}
		
		view.setViewName("admin/pr/ad_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 광고홍보 > 등록
	 * @param Ad
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addAd.do")
	public ModelAndView addAd(@Valid Ad ad, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "저장되었습니다.";
				
		if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		ad.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		ad.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
		
		//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(ad.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
		
		int cnt = adminPrService.addAd(ad);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", ad.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 광고홍보 > 수정
	 * @param Ad
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiAd.do")
	public ModelAndView modiProductCase(@Valid Ad ad, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
				
		if (bindingResult.hasErrors() || ad.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		ad.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		ad.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
		
		//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(ad.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
		
		int cnt = adminPrService.modiAd(ad, param);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "수정에 실패하였습니다.";
		}
		
		view.addObject("IDX", ad.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 광고홍보 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delAd.do")
	public ModelAndView delAd(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";

		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminPrService.delAd(param);
		
		if(cnt < 0){
			RETURN_CODE = "0001";
			RETURN_MSG = "삭제에 실패하였습니다.";
		}

		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 언론보도 > 리스트
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
		int pageSize = 12;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Press> pressList = adminPrService.getPressSelectList(param, reqPage, pageSize);
		
		view = pressList.setPageModel(view, pressList, pageSize);
		
		view.addObject("pressList", pressList);
		
		view.setViewName("admin/pr/press_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 언론보도 > 등록, 수정 폼
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
			
			Press press = adminPrService.getPressSelect(param);
			
			//파일 하나씩 object로 따로 담기
			if(press != null && press.getCommonFileList() != null){
				int cnt = 0;
				for(CommonFile item : press.getCommonFileList()){
					
					if("LIST_IMG".equals(item.getFileType())){
						view.addObject(item.getFileType(), item);
					}else{
						view.addObject(item.getFileType() + (cnt++), item);
					}
					
				}
				
			}
			
			view.addObject("press", press);
			
		}
		
		view.setViewName("admin/pr/press_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 언론보도 > 등록
	 * @param Press
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addPress.do")
	public ModelAndView addPress(@Valid Press press, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "저장되었습니다.";
				
		if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		press.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		press.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
		
		//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(press.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
		
		int cnt = adminPrService.addPress(press);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", press.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 언론보도 > 수정
	 * @param Ad
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiPress.do")
	public ModelAndView modiPress(@Valid Press press, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
				
		if (bindingResult.hasErrors() || press.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		press.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		press.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
		
		//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(press.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
		
		int cnt = adminPrService.modiPress(press, param);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "수정에 실패하였습니다.";
		}
		
		view.addObject("IDX", press.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 언론보도 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delPress.do")
	public ModelAndView delPress(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";

		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminPrService.delPress(param);
		
		if(cnt < 0){
			RETURN_CODE = "0001";
			RETURN_MSG = "삭제에 실패하였습니다.";
		}

		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 언론보도 > 리스트
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
		int pageSize = 12;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Exhibition> exhibitionList = adminPrService.getExhibitionSelectList(param, reqPage, pageSize);
		
		view = exhibitionList.setPageModel(view, exhibitionList, pageSize);
		
		view.addObject("exhibitionList", exhibitionList);
		
		view.setViewName("admin/pr/exhibition_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 전시회 > 등록, 수정 폼
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
			
			Exhibition exhibition = adminPrService.getExhibitionSelect(param);
			
			//파일 하나씩 object로 따로 담기
			if(exhibition != null && exhibition.getCommonFileList() != null){
				int cnt = 0;
				for(CommonFile item : exhibition.getCommonFileList()){
					
					if("LIST_IMG".equals(item.getFileType())){
						view.addObject(item.getFileType(), item);
					}else{
						view.addObject(item.getFileType() + (cnt++), item);
					}
					
				}
				
			}
			
			view.addObject("exhibition", exhibition);
			
		}
		
		view.setViewName("admin/pr/exhibition_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 전시회 > 등록
	 * @param Exhibition
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addExhibition.do")
	public ModelAndView addExhibition(@Valid Exhibition exhibition, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "저장되었습니다.";
				
		if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		exhibition.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		exhibition.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
		
		//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(exhibition.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
		
		int cnt = adminPrService.addExhibition(exhibition);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", exhibition.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 전시회 > 수정
	 * @param Exhibition
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiExhibition.do")
	public ModelAndView modiExhibition(@Valid Exhibition exhibition, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
				
		if (bindingResult.hasErrors() || exhibition.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		exhibition.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		exhibition.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
		
		//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(exhibition.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
		
		int cnt = adminPrService.modiExhibition(exhibition, param);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "수정에 실패하였습니다.";
		}
		
		view.addObject("IDX", exhibition.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 홍보센터 > 전시회 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delExhibition.do")
	public ModelAndView delExhibition(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";

		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminPrService.delExhibition(param);
		
		if(cnt < 0){
			RETURN_CODE = "0001";
			RETURN_MSG = "삭제에 실패하였습니다.";
		}

		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	
}
