package com.woowoneng.service;

import java.util.List;

import com.woowoneng.entity.Certify;
import com.woowoneng.entity.Client;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CompanyService.java
 * 기        능 : 회사소개 Service interface
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

public interface CompanyService {

	List<Certify> getCertifySelectList() throws Exception;

	List<Client> getClientSelectList() throws Exception;

}
