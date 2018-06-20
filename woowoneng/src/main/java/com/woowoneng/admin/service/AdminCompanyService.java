package com.woowoneng.admin.service;

import java.util.List;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Certify;
import com.woowoneng.entity.Client;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminCompanyService.java
 * 기        능 : 어드민 회사소개 Service interface
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

public interface AdminCompanyService {

	int addCertify(Certify certify) throws Exception;

	Certify getSelectCertify(ParamUtil<String, Object> param) throws Exception;

	int modiCertify(Certify certify, ParamUtil<String, Object> param) throws Exception;

	int delCertify(ParamUtil<String, Object> param) throws Exception;

	List<Certify> getCertifySelectList() throws Exception;
	
	int addClient(Client client) throws Exception;

	Client getSelectClient(ParamUtil<String, Object> param) throws Exception;

	int modiClient(Client client, ParamUtil<String, Object> param) throws Exception;

	int delClient(ParamUtil<String, Object> param) throws Exception;

	List<Client> getClientSelectList() throws Exception;

}
