package com.woowoneng.dao;

import java.util.ArrayList;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.CommonFile;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CommonFileDao.java
 * 기        능 : 공통파일 DAO
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


public interface CommonFileDao {

	int insertArr(@Param("fileList") List<CommonFile> fileList) throws Exception;

	List<CommonFile> getSelect(CommonFile commonFile) throws Exception;
	
	List<CommonFile> select(ParamUtil<String, Object> paramUtil) throws Exception;

	int fileDel(CommonFile commonFile) throws Exception;

	int insert(CommonFile commonFile) throws Exception;

	int updateFileDesc(CommonFile commonFile) throws Exception;

	int fileIdxDel(@Param("idx") int idx) throws Exception;

}
