package com.woowoneng.admin.service;

import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Ad;
import com.woowoneng.entity.Exhibition;
import com.woowoneng.entity.Press;
import com.woowoneng.entity.ProductCase;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminPrService.java
 * 기        능 : 어드민 홍보센터 Service interface
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

public interface AdminPrService {

	int addProductCase(ProductCase productCase) throws Exception;

	ProductCase getProductCaseSelect(ParamUtil<String, Object> param) throws Exception;

	int modiProductCase(ProductCase productCase, ParamUtil<String, Object> param) throws Exception;

	int delProductCase(ParamUtil<String, Object> param) throws Exception;

	Ad getAdSelect(ParamUtil<String, Object> param) throws Exception;

	int addAd(Ad ad) throws Exception;

	int modiAd(Ad ad, ParamUtil<String, Object> param) throws Exception;

	int delAd(ParamUtil<String, Object> param) throws Exception;

	Press getPressSelect(ParamUtil<String, Object> param) throws Exception;

	int addPress(Press press) throws Exception;

	int modiPress(Press press, ParamUtil<String, Object> param) throws Exception;

	int delPress(ParamUtil<String, Object> param) throws Exception;

	Exhibition getExhibitionSelect(ParamUtil<String, Object> param) throws Exception;

	int addExhibition(Exhibition exhibition) throws Exception;

	int modiExhibition(Exhibition exhibition, ParamUtil<String, Object> param) throws Exception;

	int delExhibition(ParamUtil<String, Object> param) throws Exception;

	PagedList<ProductCase> getProductCaseSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	PagedList<Ad> getAdSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	PagedList<Press> getPressSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

	PagedList<Exhibition> getExhibitionSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception;

}
