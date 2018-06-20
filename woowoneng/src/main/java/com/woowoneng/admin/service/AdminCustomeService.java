package com.woowoneng.admin.service;

import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.CustomerRequest;
import com.woowoneng.entity.Faq;
import com.woowoneng.entity.Notice;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminCustomeService.java
 * 기        능 : 어드민 고객센터 Service interface
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

public interface AdminCustomeService {

	Notice getSelectNotice(ParamUtil<String, Object> param) throws Exception;

	PagedList<Notice> getSelectNoticeList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;
	
	int addNotice(Notice notice) throws Exception;

	int modiNotice(Notice notice, ParamUtil<String, Object> param) throws Exception;

	int delNotice(ParamUtil<String, Object> param) throws Exception;

	Faq getSelectFaq(ParamUtil<String, Object> param) throws Exception;

	PagedList<Faq> getSelectFaqList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;
	
	int addFaq(Faq faq) throws Exception;

	int modiFaq(Faq faq) throws Exception;

	int delFaq(ParamUtil<String, Object> param) throws Exception;

	PagedList<CustomerRequest> getSelectRequestList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	CustomerRequest getSelectRequest(ParamUtil<String, Object> param) throws Exception;

	int updateCustomerRequestProcType(ParamUtil<String, Object> param) throws Exception;

}
