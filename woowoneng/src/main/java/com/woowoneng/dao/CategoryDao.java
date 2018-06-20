package com.woowoneng.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Category;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CategoryDao.java
 * 기        능 : 카테고리 DAO
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

public interface CategoryDao {

	List<Category> select(ParamUtil<String, Object> param) throws Exception;

	List<Category> getCategoryDepth(ParamUtil<String, Object> param) throws Exception;

	int insert(Category category) throws Exception;

	int updateTopIdx(Category category) throws Exception;

	int update(Category category) throws Exception;

	String getCategoryLocation(ParamUtil<String, Object> param);

}
