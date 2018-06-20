package com.woowoneng.admin.service.impl;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.admin.service.AdminOrdersService;
import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.dao.OrdersDao;
import com.woowoneng.entity.Orders;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminOrderServiceImpl.java
 * 기        능 : 어드민 주문관리 Service
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

@Service("adminOrderService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminOrdersServiceImpl extends AbstractGenericManager implements AdminOrdersService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Override
	public Orders getSelect(ParamUtil<String, Object> param) throws Exception{
		return ordersDao.select(param);
	}
	
	@Override
	public int updateProcType(ParamUtil<String, Object> param) throws Exception{
		return ordersDao.updateProcType(param);
	}
	
	@Override
	public PagedList<Orders> getSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Orders>) list("com.woowoneng.dao.OrdersDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public int modiOrder(Orders orders) throws Exception{
		return ordersDao.update(orders);
	}
	
}
