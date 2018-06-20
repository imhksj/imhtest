package com.woowoneng.dao;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Member;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : MemberDao.java
 * 기        능 : 회원관리 DAO
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

public interface MemberDao {

	Member select(ParamUtil<String, Object> param) throws Exception;

	int insert(Member member) throws Exception;

	int update(Member member) throws Exception;

	int delete(int idx) throws Exception;

	Member selectMember(Member member) throws Exception;

}
