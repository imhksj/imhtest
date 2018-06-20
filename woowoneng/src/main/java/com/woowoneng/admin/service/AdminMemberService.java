package com.woowoneng.admin.service;

import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Member;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminMemberService.java
 * 기        능 : 어드민 회원관리 Service interface
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

public interface AdminMemberService {

	Member getSelect(ParamUtil<String, Object> param) throws Exception;

	int addMember(Member member) throws Exception;

	int modiMember(Member member, ParamUtil<String, Object> param) throws Exception;

	PagedList<Member> getSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	int delMember(int idx) throws Exception;

}
