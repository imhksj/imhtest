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

import com.woowoneng.admin.service.AdminCompanyService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Certify;
import com.woowoneng.entity.Client;
import com.woowoneng.entity.CommonFile;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminCompanyController.java
 * 기        능 : 어드민 회사소개 Controller
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
@RequestMapping({ "/admin/company/" })
public class AdminCompanyController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AdminCompanyService adminCompanyService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회사소개 > 인증현황 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "certify_list.do")
	public ModelAndView certifyList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		List<Certify> certifyList = adminCompanyService.getCertifySelectList();
		
		view.addObject("certifyList", certifyList);
		
		view.setViewName("admin/company/certify_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회사소개 > 인증현황 > 등록, 수정 폼
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "certify_detail.do")
	public ModelAndView certifyDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){
			
			Certify certify = adminCompanyService.getSelectCertify(param);
			
			view.addObject("certify", certify);
			
		}
				
		view.setViewName("admin/company/certify_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회사소개 > 인증현황 > 등록
	 * @param Certify
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addCertify.do")
	public ModelAndView addCertify(@Valid Certify certify, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
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
			view.addObject("RETURN_MSG", "이미지는 필수 입력 항목입니다.");
			return view;
        }
		
        certify.setCommonFile(fileList.get(0));
    	
    	//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(certify.getCommonFile(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
        
        certify.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
				
		int cnt = adminCompanyService.addCertify(certify);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", certify.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회사소개 > 인증현황  > 수정
	 * @param Certify
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiCertify.do")
	public ModelAndView modiProd(@Valid Certify certify, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
		
        if (bindingResult.hasErrors() || certify.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
      	List<CommonFile> fileList = (List<CommonFile>) req.getAttribute("fileList");
      	
      	//이미지 수정이 있을시 파일 형식 검사
      	if(fileList.size() > 0 || !"".equals(fileList.get(0).getFileSysName())){

      		certify.setCommonFile(fileList.get(0));
      		
      		//파일 type 검사
          	if(!CommonUtil.getFileTypeValidate(certify.getCommonFile(), "image")){
    			view.addObject("RETURN_CODE", "0002");
    			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
    			view.setViewName("jsonView");
    			return view;
    		}
      		
        }
      	
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		certify.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		
		int cnt = adminCompanyService.modiCertify(certify, param);
		
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
	 * ADMIN > 회사소개 > 인증현황 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delCertify.do")
	public ModelAndView delProd(HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";
		      	
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminCompanyService.delCertify(param);
		
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
	 * ADMIN > 회사소개 > 주요고객 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "client_list.do")
	public ModelAndView clientList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		List<Client> clientList = adminCompanyService.getClientSelectList();
		
		view.addObject("clientList", clientList);
		
		view.setViewName("admin/company/client_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회사소개 > 주요고객 > 등록, 수정 폼
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "client_detail.do")
	public ModelAndView clientDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){
			
			Client client = adminCompanyService.getSelectClient(param);
			
			view.addObject("client", client);
			
		}
				
		view.setViewName("admin/company/client_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회사소개 > 주요고객 > 등록
	 * @param Client
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addClient.do")
	public ModelAndView addClient(@Valid Client client, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
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
			view.addObject("RETURN_MSG", "이미지는 필수 입력 항목입니다.");
			return view;
        }
		
        client.setCommonFile(fileList.get(0));
    	
    	//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(client.getCommonFile(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
        
      	client.setRegAdminIdx((int) req.getSession().getAttribute("adminIdx"));
				
		int cnt = adminCompanyService.addClient(client);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", client.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회사소개 > 주요고객  > 수정
	 * @param Client
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiClient.do")
	public ModelAndView modiClient(@Valid Client client, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
		
        if (bindingResult.hasErrors() || client.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
      	List<CommonFile> fileList = (List<CommonFile>) req.getAttribute("fileList");
      	
      	//이미지 수정이 있을시 파일 형식 검사
      	if(fileList.size() > 0 || !"".equals(fileList.get(0).getFileSysName())){

      		client.setCommonFile(fileList.get(0));
      		
      		//파일 type 검사
          	if(!CommonUtil.getFileTypeValidate(client.getCommonFile(), "image")){
    			view.addObject("RETURN_CODE", "0002");
    			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
    			view.setViewName("jsonView");
    			return view;
    		}
      		
        }
      	
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		client.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
		
		int cnt = adminCompanyService.modiClient(client, param);
		
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
	 * ADMIN > 회사소개 > 주요고객 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delClient.do")
	public ModelAndView delClient(HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";
		      	
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		param.put("delAdminIdx", req.getSession().getAttribute("adminIdx"));
		
		int cnt = 0;
		
		if(param.getInt("idx") > 0)	cnt = adminCompanyService.delClient(param);
		
		if(cnt < 0){
			RETURN_CODE = "0001";
			RETURN_MSG = "삭제에 실패하였습니다.";
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
}
