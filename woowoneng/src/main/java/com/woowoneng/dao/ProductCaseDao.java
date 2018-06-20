package com.woowoneng.dao;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.ProductCase;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : ProductCaseDao.java
 * 기        능 : 납품사례 DAO
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

public interface ProductCaseDao {

	int insert(ProductCase productCase) throws Exception;

	ProductCase select(ParamUtil<String, Object> param) throws Exception;

	int update(ProductCase productCase) throws Exception;

	int delete(ParamUtil<String, Object> param) throws Exception;

	ProductCase selectNext(ParamUtil<String, Object> param) throws Exception;

	ProductCase selectPrev(ParamUtil<String, Object> param) throws Exception;

	void updateHit(ParamUtil<String, Object> param) throws Exception;

}
