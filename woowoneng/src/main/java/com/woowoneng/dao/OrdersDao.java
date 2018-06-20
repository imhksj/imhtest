package com.woowoneng.dao;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Orders;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : OrdersDao.java
 * 기        능 : 주문관리 DAO
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

public interface OrdersDao {

	Orders select(ParamUtil<String, Object> param) throws Exception;

	int updateProcType(ParamUtil<String, Object> param) throws Exception;

	int update(Orders orders) throws Exception;

	int insert(Orders orders) throws Exception;

}
