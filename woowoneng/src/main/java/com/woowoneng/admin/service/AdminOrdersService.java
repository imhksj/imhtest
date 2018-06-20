package com.woowoneng.admin.service;

import com.woowoneng.common.utils.PagedList;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Orders;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminOrderService.java
 * 기        능 : 어드민 주문관리 Service interface
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

public interface AdminOrdersService {

	Orders getSelect(ParamUtil<String, Object> param) throws Exception;

	int updateProcType(ParamUtil<String, Object> param) throws Exception;

	PagedList<Orders> getSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	int modiOrder(Orders orders) throws Exception;

}
