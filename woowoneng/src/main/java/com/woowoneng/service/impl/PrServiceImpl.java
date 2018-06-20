package com.woowoneng.service.impl;

import org.apache.commons.logging.Log;


import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.dao.AdDao;
import com.woowoneng.dao.ExhibitionDao;
import com.woowoneng.dao.PressDao;
import com.woowoneng.dao.ProductCaseDao;
import com.woowoneng.entity.Ad;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Exhibition;
import com.woowoneng.entity.Press;
import com.woowoneng.entity.ProductCase;
import com.woowoneng.service.CommonFileService;
import com.woowoneng.service.PrService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminPrServiceImpl.java
 * 기        능 : 홍보센터 Service
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

@Service("prService")
@Transactional(propagation=Propagation.REQUIRED)
public class PrServiceImpl extends AbstractGenericManager implements PrService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ProductCaseDao productCaseDao;
	
	@Autowired
	private AdDao adDao;
	
	@Autowired
	private PressDao pressDao;
	
	@Autowired
	private ExhibitionDao exhibitionDao;
	
	@Autowired
	private CommonFileService commonFileService;
	
	@Override
	public PagedList<ProductCase> getProductCaseSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<ProductCase>) list("com.woowoneng.dao.ProductCaseDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public ProductCase getProductCaseSelect(ParamUtil<String, Object> param) throws Exception{
		
		productCaseDao.updateHit(param);
		
		ProductCase productCase = productCaseDao.select(param);
		
		if(productCase != null){
			
			param.clear();
			param.put("pidx", productCase.getIdx());
			param.put("fileGroup", "PR/CASE");

			productCase.setCommonFileList(commonFileService.select(param));

		}
		
		return productCase;
		
	}
	
	@Override
	public PagedList<Ad> getAdSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Ad>) list("com.woowoneng.dao.AdDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public Ad getAdSelect(ParamUtil<String, Object> param) throws Exception{
		
		adDao.updateHit(param);
		
		Ad ad = adDao.select(param);
		
		if(ad != null){
			
			param.clear();
			param.put("pidx", ad.getIdx());
			param.put("fileGroup", "PR/AD");
			
			ad.setCommonFileList(commonFileService.select(param));
			
		}
		
		return ad;
		
	}
	
	@Override
	public PagedList<Press> getPressSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Press>) list("com.woowoneng.dao.PressDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public Press getPressSelect(ParamUtil<String, Object> param) throws Exception{
	
		pressDao.updateHit(param);
		
		Press press = pressDao.select(param);
		
		if(press != null){
			
			param.clear();
			param.put("pidx", press.getIdx());
			param.put("fileGroup", "PR/PRESS");

			press.setCommonFileList(commonFileService.select(param));

		}
		
		return press;
		
	}
	
	@Override
	public PagedList<Exhibition> getExhibitionSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Exhibition>) list("com.woowoneng.dao.ExhibitionDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public Exhibition getExhibitionSelect(ParamUtil<String, Object> param) throws Exception{
	
		exhibitionDao.updateHit(param);
		
		Exhibition exhibition = exhibitionDao.select(param);
		
		if(exhibition != null){
			
			param.clear();
			param.put("pidx", exhibition.getIdx());
			param.put("fileGroup", "PR/EXHIBITION");

			exhibition.setCommonFileList(commonFileService.select(param));

		}
		
		return exhibition;
		
	}
	@Override
	public Exhibition getExhibitionSelectNext(ParamUtil<String, Object> param) throws Exception{
		return exhibitionDao.selectNext(param);
	}
	
	@Override
	public Exhibition getExhibitionSelectPrev(ParamUtil<String, Object> param) throws Exception{
		return exhibitionDao.selectPrev(param);
	}
	
	@Override
	public Press getPressSelectNext(ParamUtil<String, Object> param) throws Exception{
		return pressDao.selectNext(param);
	}

	@Override
	public Press getPressSelectPrev(ParamUtil<String, Object> param) throws Exception{
		return pressDao.selectPrev(param);
	}
	
	@Override
	public Ad getAdSelectNext(ParamUtil<String, Object> param) throws Exception{
		return adDao.selectNext(param);
	}
	
	@Override
	public Ad getAdSelectPrev(ParamUtil<String, Object> param) throws Exception{
		return adDao.selectPrev(param);
	}
	
	@Override
	public ProductCase getProductCaseSelectNext(ParamUtil<String, Object> param) throws Exception{
		return productCaseDao.selectNext(param);
	}

	@Override
	public ProductCase getProductCaseSelectPrev(ParamUtil<String, Object> param) throws Exception{
		return productCaseDao.selectPrev(param);
	}
	
}
