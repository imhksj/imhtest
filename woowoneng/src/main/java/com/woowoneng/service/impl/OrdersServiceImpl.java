package com.woowoneng.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.dao.OrdersDao;
import com.woowoneng.entity.Orders;
import com.woowoneng.service.OrdersService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : OrdersServiceImpl.java
 * 기        능 : 주문 Service
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

@Service("ordersService")
@Transactional(propagation=Propagation.REQUIRED)
public class OrdersServiceImpl extends AbstractGenericManager implements OrdersService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private OrdersDao ordersDao;
	
	@Override
	public int addOrder(Orders orders) throws Exception{
		return ordersDao.insert(orders);
	}
	
}
