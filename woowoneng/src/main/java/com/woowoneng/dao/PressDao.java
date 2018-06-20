package com.woowoneng.dao;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Press;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : PressDao.java
 * 기        능 : 언론보도 DAO
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

public interface PressDao {

	Press select(ParamUtil<String, Object> param) throws Exception;

	int insert(Press press) throws Exception;

	int update(Press press) throws Exception;

	int delete(ParamUtil<String, Object> param) throws Exception;

	Press selectNext(ParamUtil<String, Object> param) throws Exception;

	Press selectPrev(ParamUtil<String, Object> param) throws Exception;

	void updateHit(ParamUtil<String, Object> param) throws Exception;

}
