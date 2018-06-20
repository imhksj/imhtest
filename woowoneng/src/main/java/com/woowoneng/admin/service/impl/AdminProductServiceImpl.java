package com.woowoneng.admin.service.impl;

import java.util.List;



import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.admin.service.AdminProductService;
import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.dao.ProductDao;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Product;
import com.woowoneng.service.CommonFileService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminProductServiceImpl.java
 * 기        능 : 어드민 상품관리 Service
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

@Service("adminProductService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminProductServiceImpl extends AbstractGenericManager implements AdminProductService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ProductDao productDao;
		
	@Autowired
	private CommonFileService commonFileService;
	
	@Override
	public Product getSelect(ParamUtil<String, Object> param) throws Exception{
		return productDao.select(param);
	}
	
	@Override
	public PagedList<Product> getSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Product>) list("com.woowoneng.dao.ProductDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public int addProd(Product product) throws Exception{
		
		int cnt = productDao.insert(product);
		
		//DB에 저장이 되었으면
		if(cnt > 0 && product.getIdx() > 0){
			commonFileService.commonFileAdd(product.getCommonFile(), product.getIdx());
		}
		
		return cnt;
	}
	
	@Override
	public int modiProd(Product product, ParamUtil<String, Object> param) throws Exception{
		
		int cnt = productDao.update(product);

		//파일 수정이 있으면 기존 파일 삭제 후 파일 새로 등록
		if(cnt > 0 && !"".equals(product.getCommonFile().getFileOrgName())){
			
			commonFileService.commonFileDel(product.getCommonFile());
			commonFileService.commonFileAdd(product.getCommonFile(), product.getIdx());

		}
		
		return cnt;
	}

	@Override
	public int delProd(ParamUtil<String, Object> param) throws Exception {
		return productDao.delete(param);
	}
}
