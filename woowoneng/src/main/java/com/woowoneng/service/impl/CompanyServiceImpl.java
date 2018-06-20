package com.woowoneng.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.dao.CertifyDao;
import com.woowoneng.dao.ClientDao;
import com.woowoneng.entity.Certify;
import com.woowoneng.entity.Client;
import com.woowoneng.service.CompanyService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminCompanyServiceImpl.java
 * 기        능 : 회사소개 Service
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

@Service("companyService")
@Transactional(propagation=Propagation.REQUIRED)
public class CompanyServiceImpl extends AbstractGenericManager implements CompanyService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CertifyDao certifyDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Override
	public List<Certify> getCertifySelectList() throws Exception{
		return certifyDao.selectList();
	}
	
	@Override
	public List<Client> getClientSelectList() throws Exception{
		return clientDao.selectList();
	}
	
}
