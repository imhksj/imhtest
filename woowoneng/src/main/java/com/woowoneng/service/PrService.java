package com.woowoneng.service;

import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Ad;
import com.woowoneng.entity.Exhibition;
import com.woowoneng.entity.Press;
import com.woowoneng.entity.ProductCase;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : PrService.java
 * 기        능 : 홍보센터 Service interface
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

public interface PrService {

	ProductCase getProductCaseSelect(ParamUtil<String, Object> param) throws Exception;

	Ad getAdSelect(ParamUtil<String, Object> param) throws Exception;

	Press getPressSelect(ParamUtil<String, Object> param) throws Exception;

	Exhibition getExhibitionSelect(ParamUtil<String, Object> param) throws Exception;

	PagedList<ProductCase> getProductCaseSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	PagedList<Ad> getAdSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	PagedList<Press> getPressSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	PagedList<Exhibition> getExhibitionSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	Exhibition getExhibitionSelectNext(ParamUtil<String, Object> param) throws Exception;

	Exhibition getExhibitionSelectPrev(ParamUtil<String, Object> param) throws Exception;

	Press getPressSelectNext(ParamUtil<String, Object> param) throws Exception;

	Press getPressSelectPrev(ParamUtil<String, Object> param) throws Exception;

	Ad getAdSelectNext(ParamUtil<String, Object> param) throws Exception;

	Ad getAdSelectPrev(ParamUtil<String, Object> param) throws Exception;

	ProductCase getProductCaseSelectNext(ParamUtil<String, Object> param) throws Exception;

	ProductCase getProductCaseSelectPrev(ParamUtil<String, Object> param) throws Exception;

}
