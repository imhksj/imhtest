package com.woowoneng.dao;

import java.util.List;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Certify;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CertifyDao.java
 * 기        능 : 인증현황 DAO
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

public interface CertifyDao {

	int insert(Certify certify) throws Exception;

	Certify select(ParamUtil<String, Object> param) throws Exception;

	int update(Certify certify) throws Exception;

	int delete(ParamUtil<String, Object> param) throws Exception;

	List<Certify> selectList() throws Exception;

}
