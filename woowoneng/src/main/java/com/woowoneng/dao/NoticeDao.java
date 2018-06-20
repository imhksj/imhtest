package com.woowoneng.dao;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Notice;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : NoticeDao.java
 * 기        능 : 공지사항 DAO
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

public interface NoticeDao {

	Notice select(ParamUtil<String, Object> param) throws Exception;

	int insert(Notice notice) throws Exception;

	int update(Notice notice) throws Exception;

	int delete(ParamUtil<String, Object> param) throws Exception;

	Notice selectNext(ParamUtil<String, Object> param) throws Exception;

	Notice selectPrev(ParamUtil<String, Object> param) throws Exception;

	void updateHit(ParamUtil<String, Object> param) throws Exception;

}
