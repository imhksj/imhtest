package com.woowoneng.admin.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.admin.service.AdminCompanyService;
import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.dao.CertifyDao;
import com.woowoneng.dao.ClientDao;
import com.woowoneng.entity.Certify;
import com.woowoneng.entity.Client;
import com.woowoneng.service.CommonFileService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminCompanyServiceImpl.java
 * 기        능 : 어드민 회사소개 Service
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

@Service("adminCompanyService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminCompanyServiceImpl extends AbstractGenericManager implements AdminCompanyService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CertifyDao certifyDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private CommonFileService commonFileService;
	
	@Override
	public List<Certify> getCertifySelectList() throws Exception{
		return certifyDao.selectList();
	}
	
	@Override
	public Certify getSelectCertify(ParamUtil<String, Object> param) throws Exception{
		return certifyDao.select(param);
	}
	
	@Override
	public int addCertify(Certify certify) throws Exception{
		
		int cnt = certifyDao.insert(certify);
		
		//DB저장 후 파일 있으면 저장
		if(cnt > 0 && certify.getIdx() > 0 && certify.getCommonFile() != null){
			
			commonFileService.commonFileAdd(certify.getCommonFile(), certify.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int modiCertify(Certify certify, ParamUtil<String, Object> param) throws Exception{
		
		int cnt = certifyDao.update(certify);

		//파일 수정이 있으면 기존 파일 삭제 후 파일 새로 등록
		if(cnt > 0 && !"".equals(certify.getCommonFile().getFileOrgName())){
			
			commonFileService.commonFileDel(certify.getCommonFile());
			commonFileService.commonFileAdd(certify.getCommonFile(), certify.getIdx());

		}
		
		return cnt;
		
	}
	
	@Override
	public int delCertify(ParamUtil<String, Object> param) throws Exception{
		return certifyDao.delete(param);
	}

	@Override
	public List<Client> getClientSelectList() throws Exception{
		return clientDao.selectList();
	}
	
	@Override
	public Client getSelectClient(ParamUtil<String, Object> param) throws Exception{
		return clientDao.select(param);
	}
	
	@Override
	public int addClient(Client client) throws Exception{
		
		int cnt = clientDao.insert(client);
		
		//DB저장 후 파일 있으면 저장
		if(cnt > 0 && client.getIdx() > 0 && client.getCommonFile() != null){
			
			commonFileService.commonFileAdd(client.getCommonFile(), client.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int modiClient(Client client, ParamUtil<String, Object> param) throws Exception{
		
		int cnt = clientDao.update(client);

		//파일 수정이 있으면 기존 파일 삭제 후 파일 새로 등록
		if(cnt > 0 && !"".equals(client.getCommonFile().getFileOrgName())){
			
			commonFileService.commonFileDel(client.getCommonFile());
			commonFileService.commonFileAdd(client.getCommonFile(), client.getIdx());

		}
		
		return cnt;
		
	}
	
	@Override
	public int delClient(ParamUtil<String, Object> param) throws Exception{
		return clientDao.delete(param);
	}
	
}
