package com.woowoneng.service;

import java.util.List;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Category;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일   명 : CategoryService.java
 * 기         능 : 카테고리 처리관련 Service 인터페이스
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

public interface CategoryService {

	List<Category> getCategory(ParamUtil<String, Object> param) throws Exception;

	List<Category> getCategoryDepth(ParamUtil<String, Object> param) throws Exception;

	int addCategory(Category category) throws Exception;

	int updateTopIdx(Category category) throws Exception;

	int modiCategory(Category category) throws Exception;

	String getCategoryLocation(ParamUtil<String, Object> param);

}
