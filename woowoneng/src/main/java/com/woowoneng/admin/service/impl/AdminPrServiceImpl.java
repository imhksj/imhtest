package com.woowoneng.admin.service.impl;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.admin.service.AdminPrService;
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

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminPrServiceImpl.java
 * 기        능 : 어드민 홍보센터 Service
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

@Service("adminPrService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminPrServiceImpl extends AbstractGenericManager implements AdminPrService{

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
	public int addProductCase(ProductCase productCase) throws Exception{
		
		int cnt = productCaseDao.insert(productCase);
		
		if(cnt > 0 && productCase.getIdx() > 0){
			
			//파일등록
			commonFileService.commonFileAdd(productCase.getCommonFileList(), productCase.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int modiProductCase(ProductCase productCase, ParamUtil<String, Object> param) throws Exception{
		
		String[] fileDelIdx = param.getString("fileDelIdx").split(",");
		
		int cnt = productCaseDao.update(productCase);
		
		if(cnt > 0 && productCase.getIdx() > 0){
			
			for(CommonFile item : productCase.getCommonFileList()){
				//수정시 넘어온 파일이 있고 파일 idx가 있으면 기존파일 삭제
				if(!"".equals(item.getFileOrgName()) && item.getIdx() > 0){
					commonFileService.commonFileDel(item);
				}
			}
			
			for(int i = 0; i < fileDelIdx.length; i++){
				if(!"".equals(fileDelIdx[i])){
					commonFileService.commonFileIdxDel(Integer.parseInt(fileDelIdx[i]));
				}
			}
			System.out.println(productCase.getCommonFileList());
			commonFileService.commonFileAdd(productCase.getCommonFileList(), productCase.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int delProductCase(ParamUtil<String, Object> param) throws Exception{
		return productCaseDao.delete(param);
	}
	
	@Override
	public PagedList<Ad> getAdSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Ad>) list("com.woowoneng.dao.AdDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public Ad getAdSelect(ParamUtil<String, Object> param) throws Exception{
				
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
	public int addAd(Ad ad) throws Exception{
		
		int cnt = adDao.insert(ad);
		
		if(cnt > 0 && ad.getIdx() > 0){
			
			//파일등록
			commonFileService.commonFileAdd(ad.getCommonFileList(), ad.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int modiAd(Ad ad, ParamUtil<String, Object> param) throws Exception{
		
		String[] fileDelIdx = param.getString("fileDelIdx").split(",");
		
		int cnt = adDao.update(ad);
		
		if(cnt > 0 && ad.getIdx() > 0){
			
			for(CommonFile item : ad.getCommonFileList()){
				//수정시 넘어온 파일이 있고 파일 idx가 있으면 기존파일 삭제
				if(!"".equals(item.getFileOrgName()) && item.getIdx() > 0){
					commonFileService.commonFileDel(item);
				}
			}
			
			for(int i = 0; i < fileDelIdx.length; i++){
				if(!"".equals(fileDelIdx[i])){
					commonFileService.commonFileIdxDel(Integer.parseInt(fileDelIdx[i]));
				}
			}
			
			commonFileService.commonFileAdd(ad.getCommonFileList(), ad.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int delAd(ParamUtil<String, Object> param) throws Exception{
		return adDao.delete(param);
	}
	
	@Override
	public PagedList<Press> getPressSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Press>) list("com.woowoneng.dao.PressDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public Press getPressSelect(ParamUtil<String, Object> param) throws Exception{
	
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
	public int addPress(Press press) throws Exception{
		
		int cnt = pressDao.insert(press);
		
		if(cnt > 0 && press.getIdx() > 0){
			
			//파일등록
			commonFileService.commonFileAdd(press.getCommonFileList(), press.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int modiPress(Press press, ParamUtil<String, Object> param) throws Exception{
		
		String[] fileDelIdx = param.getString("fileDelIdx").split(",");
		
		int cnt = pressDao.update(press);
		
		if(cnt > 0 && press.getIdx() > 0){
			
			for(CommonFile item : press.getCommonFileList()){
				//수정시 넘어온 파일이 있고 파일 idx가 있으면 기존파일 삭제
				if(!"".equals(item.getFileOrgName()) && item.getIdx() > 0){
					commonFileService.commonFileDel(item);
				}
			}
			
			for(int i = 0; i < fileDelIdx.length; i++){
				if(!"".equals(fileDelIdx[i])){
					commonFileService.commonFileIdxDel(Integer.parseInt(fileDelIdx[i]));
				}
			}
			
			commonFileService.commonFileAdd(press.getCommonFileList(), press.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int delPress(ParamUtil<String, Object> param) throws Exception{
		return pressDao.delete(param);
	}
	
	@Override
	public PagedList<Exhibition> getExhibitionSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Exhibition>) list("com.woowoneng.dao.ExhibitionDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public Exhibition getExhibitionSelect(ParamUtil<String, Object> param) throws Exception{
	
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
	public int addExhibition(Exhibition exhibition) throws Exception{
		
		int cnt = exhibitionDao.insert(exhibition);
		
		if(cnt > 0 && exhibition.getIdx() > 0){
			
			//파일등록
			commonFileService.commonFileAdd(exhibition.getCommonFileList(), exhibition.getIdx());
			
		}
		
		return cnt;
		
	}
	
	@Override
	public int modiExhibition(Exhibition exhibition, ParamUtil<String, Object> param) throws Exception{
		
		String[] fileDelIdx = param.getString("fileDelIdx").split(",");
		
		int cnt = exhibitionDao.update(exhibition);
		
		if(cnt > 0 && exhibition.getIdx() > 0){
			
			for(CommonFile item : exhibition.getCommonFileList()){
				//수정시 넘어온 파일이 있고 파일 idx가 있으면 기존파일 삭제
				if(!"".equals(item.getFileOrgName()) && item.getIdx() > 0){
					commonFileService.commonFileDel(item);
				}
			}
			
			for(int i = 0; i < fileDelIdx.length; i++){
				if(!"".equals(fileDelIdx[i])){
					commonFileService.commonFileIdxDel(Integer.parseInt(fileDelIdx[i]));
				}
			}
			
			commonFileService.commonFileAdd(exhibition.getCommonFileList(), exhibition.getIdx());
			
		}
		
		return cnt;
		
	}

	@Override
	public int delExhibition(ParamUtil<String, Object> param) throws Exception{
		return exhibitionDao.delete(param);
	}
	
}
