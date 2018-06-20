package com.woowoneng.dao;

import java.util.List;


import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Client;;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : ClientDao.java
 * 기        능 : 주요고객 DAO
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

public interface ClientDao {

	int insert(Client client) throws Exception;

	Client select(ParamUtil<String, Object> param) throws Exception;

	int update(Client client) throws Exception;

	int delete(ParamUtil<String, Object> param) throws Exception;

	List<Client> selectList() throws Exception;

}
