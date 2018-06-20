package com.woowoneng.service;

import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Product;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : ProductService.java
 * 기        능 : 상품 Service interface
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

public interface ProductService {

	PagedList<Product> getSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

}