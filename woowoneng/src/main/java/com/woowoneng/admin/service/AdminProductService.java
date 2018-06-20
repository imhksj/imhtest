package com.woowoneng.admin.service;

import com.woowoneng.common.utils.PagedList;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Product;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminProductService.java
 * 기        능 : 어드민 상품관리 Service interface
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

public interface AdminProductService {

	Product getSelect(ParamUtil<String, Object> param) throws Exception;

	int addProd(Product product) throws Exception;

	int modiProd(Product product, ParamUtil<String, Object> param) throws Exception;

	PagedList<Product> getSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	int delProd(ParamUtil<String, Object> param) throws Exception;

}
