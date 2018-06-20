package com.woowoneng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.woowoneng.common.service.MailService;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Category;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.CustomerRequest;
import com.woowoneng.entity.Faq;
import com.woowoneng.entity.Notice;
import com.woowoneng.service.CategoryService;
import com.woowoneng.service.CustomerService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CustomerController.java
 * 기        능 : 고객센터 Controller
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
@RequestMapping({"/customer/"})
public class CustomerController {
	
	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MailService mailService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > 공지사항 > 리스트
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
		
		PagedList<Notice> noticeList = customerService.getSelectNoticeList(param, reqPage, pageSize);
		
		view = noticeList.setPageModel(view, noticeList, pageSize);
		
		view.addObject("noticeList", noticeList);
		
		param.clear();
		param.put("cType", "NOTICE");
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategory(param);
		view.addObject("cateList", cateList);
		
		view.setViewName("customer/notice_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > 공지사항 > 등록, 수정 폼
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
			
			Notice notice = customerService.getSelectNotice(param);
			Notice noticeNext = customerService.getSelectNoticeNext(param);
			Notice noticePrev = customerService.getSelectNoticePrev(param);
			
			view.addObject("notice", notice);
			view.addObject("noticeNext", noticeNext);
			view.addObject("noticePrev", noticePrev);
			
		}
		
		param.clear();
		param.put("cType", "NOTICE");
		//카테고리 리스트
		List<Category> cateList = categoryService.getCategory(param);
		view.addObject("cateList", cateList);
		
		view.setViewName("customer/notice_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > 자주묻는 질문 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "faq.do")
	public ModelAndView faqList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		List<Faq> faqList = customerService.getSelectFaqList();

		view.addObject("faqList", faqList);
		
		view.setViewName("customer/faq");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > FAQ > 조회수 증가
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateFaqHit.do")
	public ModelAndView updateFaqHit(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "조회수가 증가되었습니다.";
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		        
		int cnt = customerService.updateFaqHit(param);
		
		if(cnt < 0){
			RETURN_CODE = "0001";
			RETURN_MSG = "조회수 증가에 실패하였습니다.";
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > 문의하기 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "qna_list.do")
	public ModelAndView qnaList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 25;							//페이지당 뿌려질 리스트 갯수
		
		param.put("requestType", "문의하기");
		PagedList<CustomerRequest> qnaList = customerService.getSelectRequestList(param, reqPage, pageSize);
		
		view = qnaList.setPageModel(view, qnaList, pageSize);
		
		view.addObject("qnaList", qnaList);
		
		view.setViewName("customer/qna_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > QNA > 문의하기
	 * @param CustomerRequest
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addQna.do")
	public ModelAndView addQna(@Valid CustomerRequest customerRequest, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "전송되었습니다.";
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		//첨부파일 셋팅
		List<CommonFile> fileList = (List<CommonFile>) req.getAttribute("fileList");
        if(fileList != null && fileList.size() > 0 && !"".equals(fileList.get(0).getFileSysName())){
        	customerRequest.setCommonFile(fileList.get(0));
        }
		
        if(req.getSession().getAttribute("member") != null){
        	customerRequest.setMemberIdx((int) req.getSession().getAttribute("memberIdx"));
        }
                
		int cnt = customerService.addCustomerRequest(customerRequest);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "전송에 실패하였습니다.";
		}

		Map<String, Object> commonMap = new HashMap<String, Object>();
		commonMap.put("subject", customerRequest.getName() + "님의 문의하기가 들어왔습니다.");
		
		String content = "성명 : " + customerRequest.getName() + "<br><br>" +
						 "상호명 : " + customerRequest.getCompanyName() + "<br><br>" +
						 "연락처 : " + customerRequest.getTel() + "<br><br>" +
						 "이메일 : " + customerRequest.getEmail() + "<br><br>" +
						 "주소 : " + customerRequest.getAddr() + "<br><br>" +
						 "내용 : " + StringUtil.nvl(customerRequest.getContent()).replaceAll("\n", "<br>");
		
		commonMap.put("mailContent", content);
		
		mailService.sendMail(commonMap);
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > A/S요청 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "as_list.do")
	public ModelAndView asList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 25;							//페이지당 뿌려질 리스트 갯수
		
		param.put("requestType", "A/S요청");
		PagedList<CustomerRequest> asList = customerService.getSelectRequestList(param, reqPage, pageSize);
		
		view = asList.setPageModel(view, asList, pageSize);
		
		view.addObject("asList", asList);
		
		view.setViewName("customer/as_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > AS > 문의하기
	 * @param CustomerRequest
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addAs.do")
	public ModelAndView addAs(@Valid CustomerRequest customerRequest, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "전송되었습니다.";
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		//첨부파일 셋팅
		List<CommonFile> fileList = (List<CommonFile>) req.getAttribute("fileList");
        if(fileList != null && fileList.size() > 0 && !"".equals(fileList.get(0).getFileSysName())){
        	customerRequest.setCommonFile(fileList.get(0));
        }
		
        if(req.getSession().getAttribute("member") != null){
        	customerRequest.setMemberIdx((int) req.getSession().getAttribute("memberIdx"));
        }
                
		int cnt = customerService.addCustomerRequest(customerRequest);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "전송에 실패하였습니다.";
		}
		
		Map<String, Object> commonMap = new HashMap<String, Object>();
		commonMap.put("subject", customerRequest.getName() + "님의 A/S요청이 들어왔습니다.");
		
		String content = "성명 : " + customerRequest.getName() + "<br><br>" +
						 "상호명 : " + customerRequest.getCompanyName() + "<br><br>" +
						 "연락처 : " + customerRequest.getTel() + "<br><br>" +
						 "구입일 : " + customerRequest.getBuyYear() + "년 " + customerRequest.getBuyMonth() + "월<br><br>" +
						 "주소 : " + customerRequest.getAddr() + "<br><br>" +
						 "제품명 : " + customerRequest.getProductName() + "<br><br>" +
						 "내용 : " + StringUtil.nvl(customerRequest.getContent()).replaceAll("\n", "<br>");
		
		commonMap.put("mailContent", content);
		
		mailService.sendMail(commonMap);
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > 카탈로그 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "catalog_list.do")
	public ModelAndView catalogList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 25;							//페이지당 뿌려질 리스트 갯수
		
		param.put("requestType", "카탈로그");
		PagedList<CustomerRequest> catalogList = customerService.getSelectRequestList(param, reqPage, pageSize);
		
		view = catalogList.setPageModel(view, catalogList, pageSize);
		
		view.addObject("catalogList", catalogList);
		
		view.setViewName("customer/catalog_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 고객센터 > 카탈로그 > 신청하기
	 * @param CustomerRequest
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addCatalog.do")
	public ModelAndView addCatalog(@Valid CustomerRequest customerRequest, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "전송되었습니다.";
		String catalogName = param.getString("catalogName");		

		if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
		//첨부파일 셋팅
		List<CommonFile> fileList = (List<CommonFile>) req.getAttribute("fileList");
        if(fileList != null && fileList.size() > 0 && !"".equals(fileList.get(0).getFileSysName())){
        	customerRequest.setCommonFile(fileList.get(0));
        }
		
        if(req.getSession().getAttribute("member") != null){
        	customerRequest.setMemberIdx((int) req.getSession().getAttribute("memberIdx"));
        }
                
		int cnt = customerService.addCustomerRequest(customerRequest);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "전송에 실패하였습니다.";
		}
		
		Map<String, Object> commonMap = new HashMap<String, Object>();
		commonMap.put("subject", customerRequest.getName() + "님의 카탈로그 요청이 들어왔습니다.");
		
		String content = "성명 : " + customerRequest.getName() + "<br><br>" +
						 "상호명 : " + customerRequest.getCompanyName() + "<br><br>" +
						 "연락처 : " + customerRequest.getTel() + "<br><br>" +
						 "이메일 : " + customerRequest.getEmail() + "<br><br>" +
						 "주소 : " + customerRequest.getAddr() + "<br><br>" +
						 "카탈로그 : " + catalogName + "<br><br>" +
						 "내용 : " + StringUtil.nvl(customerRequest.getContent()).replaceAll("\n", "<br>");
		
		commonMap.put("mailContent", content);
		
		mailService.sendMail(commonMap);
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
}
