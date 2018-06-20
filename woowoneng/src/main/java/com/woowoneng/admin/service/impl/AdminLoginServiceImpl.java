package com.woowoneng.admin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.admin.service.AdminLoginService;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.dao.AdminDao;
import com.woowoneng.entity.Admin;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminLoginServiceImpl.java
 * 기        능 : 어드민 로그인 Service
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

@Service("adminLoginService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminLoginServiceImpl extends AbstractGenericManager implements AdminLoginService{

	private Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private AdminDao adminDao;
	
	@Override
	public Admin getSelect(Admin admin) throws Exception{
		return adminDao.select(admin);
	}
}
