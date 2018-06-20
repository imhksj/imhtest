package com.woowoneng.dao;

import com.woowoneng.common.utils.ParamUtil;

import com.woowoneng.entity.Ad;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdDao.java
 * 기        능 : 광고홍보 DAO
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

public interface AdDao {

	int insert(Ad ad) throws Exception;

	Ad select(ParamUtil<String, Object> param) throws Exception;

	int update(Ad ad) throws Exception;

	int delete(ParamUtil<String, Object> param) throws Exception;

	Ad selectNext(ParamUtil<String, Object> param) throws Exception;

	Ad selectPrev(ParamUtil<String, Object> param) throws Exception;

	void updateHit(ParamUtil<String, Object> param) throws Exception;

}
