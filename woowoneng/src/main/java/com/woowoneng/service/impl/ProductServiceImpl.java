package com.woowoneng.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.dao.ProductDao;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Product;
import com.woowoneng.service.CommonFileService;
import com.woowoneng.service.ProductService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : ProductServiceImpl.java
 * 기        능 : 상품 Service
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

@Service("productService")
@Transactional(propagation=Propagation.REQUIRED)
public class ProductServiceImpl extends AbstractGenericManager implements ProductService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private ProductDao productDao;
		
	@Autowired
	private CommonFileService commonFileService;
	
	@Override
	public PagedList<Product> getSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Product>) list("com.woowoneng.dao.ProductDao", "selectList", param, reqPage, pageSize);
	}

}
