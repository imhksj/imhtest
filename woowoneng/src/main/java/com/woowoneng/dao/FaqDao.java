package com.woowoneng.dao;

import java.util.List;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Faq;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : FaqDao.java
 * 기        능 : 자주묻는 질문 DAO
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

public interface FaqDao {

	Faq select(ParamUtil<String, Object> param) throws Exception;

	int insert(Faq faq) throws Exception;

	int update(Faq faq) throws Exception;

	int delete(ParamUtil<String, Object> param) throws Exception;

	List<Faq> selectList() throws Exception;

	int updateHit(ParamUtil<String, Object> param) throws Exception;

}
