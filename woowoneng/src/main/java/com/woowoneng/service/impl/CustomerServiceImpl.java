package com.woowoneng.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.dao.CustomerRequestDao;
import com.woowoneng.dao.FaqDao;
import com.woowoneng.dao.NoticeDao;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.CustomerRequest;
import com.woowoneng.entity.Faq;
import com.woowoneng.entity.Notice;
import com.woowoneng.service.CommonFileService;
import com.woowoneng.service.CustomerService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CustomerServiceImpl.java
 * 기        능 : 고객센터 Service
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

@Service("customerService")
@Transactional(propagation=Propagation.REQUIRED)
public class CustomerServiceImpl extends AbstractGenericManager implements CustomerService{

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
		noticeDao.updateHit(param);
		return noticeDao.select(param);
	}
	
	@Override
	public Notice getSelectNoticeNext(ParamUtil<String, Object> param) throws Exception{
		return noticeDao.selectNext(param);
	}

	@Override
	public Notice getSelectNoticePrev(ParamUtil<String, Object> param) throws Exception{
		return noticeDao.selectPrev(param);
	}
	
	@Override
	public PagedList<Notice> getSelectNoticeList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Notice>) list("com.woowoneng.dao.NoticeDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public List<Faq> getSelectFaqList() throws Exception{
		return faqDao.selectList();
	}
	
	@Override
	public int updateFaqHit(ParamUtil<String, Object> param) throws Exception{
		return faqDao.updateHit(param);
	}
	
	@Override
	public PagedList<CustomerRequest> getSelectRequestList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<CustomerRequest>) list("com.woowoneng.dao.CustomerRequestDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public int addCustomerRequest(CustomerRequest customerRequest) throws Exception{
		
		int cnt = customerRequestDao.insert(customerRequest);
		
		//DB에 저장이 되었으면 첨부파일 저장
  		if(customerRequest.getIdx() > 0){
  			commonFileService.commonFileAdd(customerRequest.getCommonFile(), customerRequest.getIdx());
  		}
        
		return cnt;
	}
	
}
