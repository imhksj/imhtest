package com.woowoneng.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.dao.CategoryDao;
import com.woowoneng.entity.Category;
import com.woowoneng.service.CategoryService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CategoryServiceImpl.java
 * 기        능 : 카테고리 처리관련 Service Implement
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

@Service("categoryService")
@Transactional(propagation=Propagation.REQUIRED)
public class CategoryServiceImpl extends AbstractGenericManager implements CategoryService{
	
	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> getCategory(ParamUtil<String, Object> param) throws Exception{
		return categoryDao.select(param);
	}

	@Override
	public List<Category> getCategoryDepth(ParamUtil<String, Object> param) throws Exception{
		return categoryDao.getCategoryDepth(param);
	}
	
	@Override
	public int addCategory(Category category) throws Exception{
		return categoryDao.insert(category);
	}
	
	@Override
	public int updateTopIdx(Category category) throws Exception{
		return categoryDao.updateTopIdx(category);
	}
	
	@Override
	public int modiCategory(Category category) throws Exception{
		return categoryDao.update(category);
	}
	
	@Override
	public String getCategoryLocation(ParamUtil<String, Object> param){
		return categoryDao.getCategoryLocation(param);
	}
	
}
