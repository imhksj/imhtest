package com.woowoneng.admin.service.impl;

import org.apache.commons.logging.Log;


import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.admin.service.AdminCustomeService;
import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.dao.CustomerRequestDao;
import com.woowoneng.dao.FaqDao;
import com.woowoneng.dao.NoticeDao;
import com.woowoneng.entity.CustomerRequest;
import com.woowoneng.entity.Faq;
import com.woowoneng.entity.Notice;
import com.woowoneng.service.CommonFileService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminCustomerServiceImpl.java
 * 기        능 : 어드민 고객센터 Service
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

@Service("adminCustomerService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminCustomerServiceImpl extends AbstractGenericManager implements AdminCustomeService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private FaqDao faqDao;
	
	@Autowired
	private CustomerRequestDao customerRequestDao;
	
	@Autowired
	private CommonFileService commonFileService;
	
	@Override
	public Notice getSelectNotice(ParamUtil<String, Object> param) throws Exception{
		return noticeDao.select(param);
	}
	
	@Override
	public PagedList<Notice> getSelectNoticeList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Notice>) list("com.woowoneng.dao.NoticeDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public int addNotice(Notice notice) throws Exception{
		
		int cnt = noticeDao.insert(notice);
		
		//DB저장 후 파일 있으면 저장
		if(cnt > 0 && notice.getIdx() > 0 && notice.getCommonFile() != null){
			
			commonFileService.commonFileAdd(notice.getCommonFile(), notice.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int modiNotice(Notice notice, ParamUtil<String, Object> param) throws Exception{
		
		int cnt = noticeDao.update(notice);
		String[] fileDelIdx = param.getString("fileDelIdx").split(",");
		
		//파일 수정이 있으면 기존 파일 삭제 후 파일 새로 등록
		if(cnt > 0 && !"".equals(notice.getCommonFile().getFileOrgName())){
			
			commonFileService.commonFileDel(notice.getCommonFile());
			commonFileService.commonFileAdd(notice.getCommonFile(), notice.getIdx());
			
		}
		
		//삭제파일이 있으면
		for(int i = 0; i < fileDelIdx.length; i++){
			if(!"".equals(fileDelIdx[i])){
				commonFileService.commonFileIdxDel(Integer.parseInt(fileDelIdx[i]));
			}
		}
		
		return cnt;
		
	}
	
	@Override
	public int delNotice(ParamUtil<String, Object> param) throws Exception{
		return noticeDao.delete(param);
	}
	
	@Override
	public Faq getSelectFaq(ParamUtil<String, Object> param) throws Exception{
		return faqDao.select(param);
	}
	
	@Override
	public PagedList<Faq> getSelectFaqList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Faq>) list("com.woowoneng.dao.FaqDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public int addFaq(Faq faq) throws Exception{
		return faqDao.insert(faq);
	}
	
	@Override
	public int modiFaq(Faq faq) throws Exception{
		return faqDao.update(faq);
	}
	
	@Override
	public int delFaq(ParamUtil<String, Object> param) throws Exception{
		return faqDao.delete(param);
	}
	
	@Override
	public PagedList<CustomerRequest> getSelectRequestList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<CustomerRequest>) list("com.woowoneng.dao.CustomerRequestDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public CustomerRequest getSelectRequest(ParamUtil<String, Object> param) throws Exception{
		return customerRequestDao.select(param);
	}
	
	@Override
	public int updateCustomerRequestProcType(ParamUtil<String, Object> param) throws Exception{
		return customerRequestDao.updateProcType(param);
	}
	
}
