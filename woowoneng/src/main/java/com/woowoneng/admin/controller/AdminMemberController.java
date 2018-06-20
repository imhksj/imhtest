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

import com.woowoneng.admin.service.AdminMemberService;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Member;
import com.woowoneng.service.CommonFileService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminMemberController.java
 * 기        능 : 어드민 회원관리 Controller
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
@RequestMapping({ "/admin/member/" })
public class AdminMemberController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AdminMemberService adminMemberService;
	
	@Autowired
	private CommonFileService commonFileService;
	
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회원관리 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "member_list.do")
	public ModelAndView memberList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 25;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Member> memberList = adminMemberService.getSelectList(param, reqPage, pageSize);
		
		view = memberList.setPageModel(view, memberList, pageSize);
		
		view.addObject("memberList", memberList);
		
		view.setViewName("admin/member/member_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회원관리 > 등록, 수정 폼
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "member_detail.do")
	public ModelAndView memberDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		//idx 정보가 있으면 회원정보 조회
		param.put("idx", param.getInt("idx"));
		Member member = adminMemberService.getSelect(param);
		
		//관련 파일 셋팅
		if(member != null && member.getCommonFileList() != null){
			
			for(CommonFile item : member.getCommonFileList()){
				view.addObject(item.getFileType(), item);
			}
			
			view.addObject("member", member);
			
		}
		
		view.setViewName("admin/member/member_detail");
		
		return view;
		
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회원관리 > 등록
	 * @param Member
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addMember.do")
	public ModelAndView addMember(@Valid Member member, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "저장되었습니다.";

        if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
        //member bean에 파일 리스트 추가
      	member.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
        
      	//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(member.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
        
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int cnt = adminMemberService.addMember(member);
		
		if(cnt < 0){
			RETURN_CODE = "0003";
			RETURN_MSG = "저장에 실패하였습니다.";
		}
		
		view.addObject("IDX", member.getIdx());
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 회원관리 > 수정
	 * @param Member
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiMember.do")
	public ModelAndView modiMember(@Valid Member member, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";
		
        if (bindingResult.hasErrors() || member.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
        //member bean에 파일 리스트 추가
      	member.setCommonFileList((List<CommonFile>) req.getAttribute("fileList"));
        
      	//파일 type 검사
      	if(!CommonUtil.getFileTypeValidate(member.getCommonFileList(), "image")){
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "지원하는 않는 파일형식입니다.");
			view.setViewName("jsonView");
			return view;
		}
        
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int cnt = adminMemberService.modiMember(member, param);
		
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
	 * ADMIN > 회원관리 > 삭제
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "delMember.do")
	public ModelAndView delMember(HttpServletRequest req, HttpServletResponse rep) throws Exception{

		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "삭제되었습니다.";
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int cnt = adminMemberService.delMember(param.getInt("idx"));
		
		if(cnt < 0){
			RETURN_CODE = "0001";
			RETURN_MSG = "삭제에 실패하였습니다.";
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
}
