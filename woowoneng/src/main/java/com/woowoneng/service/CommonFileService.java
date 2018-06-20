package com.woowoneng.service;

import java.util.ArrayList;

import java.util.List;

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.CommonFile;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일   명 : CommonFileService.java
 * 기         능 : 공통파일 처리관련 Service 인터페이스
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

public interface CommonFileService {

	int commonFileAdd(List<CommonFile> fileList, int idx) throws Exception;
	
	int commonFileAdd(List<CommonFile> fileList) throws Exception;
	
	List<CommonFile> getSelect(CommonFile commonFile) throws Exception;
	
	List<CommonFile> select(ParamUtil<String, Object> paramUtil) throws Exception;

	int commonFileDel(CommonFile commonFile) throws Exception;
	
	int commonFileListDel(List<CommonFile> commonFileList) throws Exception;

	int commonFileAdd(CommonFile commonFile, int idx) throws Exception;
	
	int commonFileIdxDel(int idx) throws Exception;
}
