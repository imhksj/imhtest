package com.woowoneng.dao;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.CustomerRequest;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CustomerRequestDao.java
 * 기        능 : 고객관리 DAO
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

public interface CustomerRequestDao {

	int insert(CustomerRequest customerRequest) throws Exception;

	CustomerRequest select(ParamUtil<String, Object> param) throws Exception;

	int updateProcType(ParamUtil<String, Object> param) throws Exception;

}
