package com.woowoneng.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.CustomerRequest;
import com.woowoneng.entity.Faq;
import com.woowoneng.entity.Notice;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CustomerService.java
 * 기        능 : 고객센터 Service interface
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

public interface CustomerService {

	Notice getSelectNotice(ParamUtil<String, Object> param) throws Exception;

	PagedList<Notice> getSelectNoticeList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;
	
	Notice getSelectNoticeNext(ParamUtil<String, Object> param) throws Exception;

	Notice getSelectNoticePrev(ParamUtil<String, Object> param) throws Exception;

	List<Faq> getSelectFaqList() throws Exception;
	
	PagedList<CustomerRequest> getSelectRequestList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;
	
	int addCustomerRequest(CustomerRequest customerRequest) throws Exception;

	int updateFaqHit(ParamUtil<String, Object> param) throws Exception;
		
}
