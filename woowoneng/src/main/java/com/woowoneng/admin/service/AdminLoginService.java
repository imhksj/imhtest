package com.woowoneng.admin.service;

import com.woowoneng.entity.Admin;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminLoginService.java
 * 기        능 : 어드민 로그인 Service interface
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

public interface AdminLoginService {

	Admin getSelect(Admin admin) throws Exception;

}
