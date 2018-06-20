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
import com.woowoneng.entity.Category;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.CustomerRequest;
import com.woowoneng.entity.Faq;
import com.woowoneng.entity.Notice;
import com.woowoneng.service.CategoryService;
import com.woowoneng.admin.service.AdminCustomeService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminPrController.java
 * 기        능 : 어드민 고객센터 Controller
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
@RequestMapping({ "/admin/customer/" })
public class AdminCustomerController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AdminCustomeService adminCustomeService;
	
	@Autowired
	private CategoryService categoryService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객센터 > 공지사항 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "notice_list.do")
	public ModelAndView noticeList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 25;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Notice> noticeList = adminCustomeService.getSelectNoticeList(param, reqPage, pageSize);
		
		view = noticeList.setPageModel(view, noticeList, pageSize);
		
		view.addObject("noticeList", noticeList);
		
		param.clear();
		param.put("cType", "NOTICE");
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategory(param);
		view.addObject("cateList", cateList);
		
		view.setViewName("admin/customer/notice_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객센터 > 공지사항 > 등록, 수정 폼
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "notice_detail.do")
	public ModelAndView noticeDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){
			
			Notice notice = adminCustomeService.getSelectNotice(param);
			
			view.addObject("notice", notice);
			
		}
		
		param.clear();
		param.put("cType", "NOTICE");
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategory(param);
		view.addObject("cateList", cateList);
		
		view.setViewName("admin/customer/notice_detail");
		
		return view;
		
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객센터 > 공지사항 > 등록
	 * @param Notice
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addNotice.do")
	public ModelAndView addNotice(@Valid Notice notice, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
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
        if(fileList.size() > 0 && !"".equals(fileList.get(0).getFileSysName())){
        
        	notice.setCommonFile(fileList.get(0));
        	
        	//파일 type 검사
          	if(!CommonUtil.getFileTypeValidate(notice.getCommonFile(), "image")){
    			view.addObject("RETURN_CODE", "0002");
    			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
    			view.setViewName("jsonView");
    			return view;
    		}
        	
        }
        
        notice.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
				
		int cnt = adminCustomeService.addNotice(notice);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", notice.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객센터 > 공지사항 > 수정
	 * @param Notice
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiNotice.do")
	public ModelAndView modiNotice(@Valid Notice notice, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
		
        if (bindingResult.hasErrors() || notice.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
      	List<CommonFile> fileList = (List<CommonFile>) req.getAttribute("fileList");
      	if(fileList.size() > 0 || !"".equals(fileList.get(0).getFileSysName())){

      		notice.setCommonFile(fileList.get(0));
      		
      		//파일 type 검사
          	if(!CommonUtil.getFileTypeValidate(notice.getCommonFile(), "image")){
    			view.addObject("RETURN_CODE", "0002");
    			view.addObject("RETURN_MSG", "지원하지 않는 파일형식입니다.");
    			view.setViewName("jsonView");
    			return view;
    		}
      		
        }
      	
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		notice.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		
		int cnt = adminCustomeService.modiNotice(notice, param);
		
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
	 * ADMIN > 고객센터 > 공지사항 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delNotice.do")
	public ModelAndView delNotice(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";

		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminCustomeService.delNotice(param);
		
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
	 * ADMIN > 고객센터 > 자주묻는 질문 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "faq_list.do")
	public ModelAndView faqList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 25;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Faq> faqList = adminCustomeService.getSelectFaqList(param, reqPage, pageSize);
		
		view = faqList.setPageModel(view, faqList, pageSize);
		
		view.addObject("faqList", faqList);
		
		view.setViewName("admin/customer/faq_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객센터 > 자주묻는 질문 > 등록, 수정 폼
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "faq_detail.do")
	public ModelAndView faqDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){
			
			Faq faq = adminCustomeService.getSelectFaq(param);
			
			view.addObject("faq", faq);
			
		}
		
		view.setViewName("admin/customer/faq_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객센터 > 자주묻는 질문 > 등록
	 * @param Faq
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addFaq.do")
	public ModelAndView addFaq(@Valid Faq faq, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "저장되었습니다.";
				
		if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }

		faq.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
				
		int cnt = adminCustomeService.addFaq(faq);
		
		if(cnt < 0){
			RETURN_CODE = "0002";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", faq.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객센터 > 자주묻는 질문 > 수정
	 * @param Faq
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiFaq.do")
	public ModelAndView modiFaq(@Valid Faq faq, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
		
        if (bindingResult.hasErrors() || faq.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }

		faq.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		
		int cnt = adminCustomeService.modiFaq(faq);
		
		if(cnt < 0){
			RETURN_CODE = "0002";
			RETURN_MSG = "수정에 실패하였습니다.";
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객센터 > 자주묻는 질문 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delFaq.do")
	public ModelAndView delFaq(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";

		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminCustomeService.delFaq(param);
		
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
	 * ADMIN > 고객관리 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "request_list.do")
	public ModelAndView requestList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 25;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<CustomerRequest> requestList = adminCustomeService.getSelectRequestList(param, reqPage, pageSize);
		
		view = requestList.setPageModel(view, requestList, pageSize);
		
		view.addObject("requestList", requestList);
		
		
		view.setViewName("admin/customer/request_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객관리 > 상세
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "request_detail.do")
	public ModelAndView requestDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		String viewName = "admin/customer/qna_detail";
		
		if(param.getInt("idx") > 0){
			
			CustomerRequest customerRequest = adminCustomeService.getSelectRequest(param);
			
			view.addObject("customerRequest", customerRequest);
			
			//뷰페이지 분기
			if(customerRequest != null){

				if("A/S요청".equals(customerRequest.getRequestType())){
					viewName = "admin/customer/as_detail";
				}else if("카탈로그".equals(customerRequest.getRequestType())){
					viewName = "admin/customer/catalog_detail";
				}
				
			}
			
		}
		
		view.setViewName(viewName);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 고객관리 > 상태변경
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateCustomerRequestProcType.do")
	public ModelAndView updateCustomerRequestProcType(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);

		if(param.getInt("idx") <= 0 || "".equals(param.getString("procType"))){

			view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "잘못된 데이타입니다.");
			return view;
		}
		
		param.put("modiAdminIdx", (int) req.getSession().getAttribute("adminIdx"));
		
		int cnt = adminCustomeService.updateCustomerRequestProcType(param);
		
		if(cnt > 0){
			view.addObject("RETURN_CODE", "0000");
			view.addObject("RETURN_MSG", "상태를 변경하였습니다.");
		}else{
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "상태를 변경에 실패하였습니다.");
		}
		
		return view;
		
	}
	
}
