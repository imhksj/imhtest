package com.woowoneng.service.impl;

import java.util.ArrayList;

import java.util.List;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.dao.CommonFileDao;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.service.CommonFileService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CommonFileServiceImpl.java
 * 기        능 : 공통파일 처리관련 Service Implement
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

@Service("commonFileService")
@Transactional(propagation=Propagation.REQUIRED)
public class CommonFileServiceImpl extends AbstractGenericManager implements CommonFileService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CommonFileDao commonFileDao;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 리스트 파일 저장
	 * @param ArrayList<CommonFile>
	 * @param int
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int commonFileAdd(CommonFile commonFile, int idx) throws Exception{
		
		int cnt = 0;

		if(commonFile != null && commonFile.getFileOrgName() != null && !"".equals(commonFile.getFileOrgName()) && idx > 0){
			
			commonFile.setPidx(idx);
			cnt = commonFileDao.insert(commonFile);
			
		}
		
		return cnt;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 리스트 파일 저장
	 * @param ArrayList<CommonFile>
	 * @param int
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int commonFileAdd(List<CommonFile> fileList, int idx) throws Exception{
		
		int cnt = 0;
		int fileListSize = 0;
		
		if(fileList != null && fileList.size() > 0 && idx > 0){
			
			for(CommonFile item : fileList){
				
				item.setPidx(idx);
				
				if(item.getFileOrgName() != null && !"".equals(item.getFileOrgName())){
					item.setIdx(0);
					fileListSize++;
				}
				
			}
			
			if(fileListSize > 0){
				cnt = commonFileDao.insertArr(fileList);
			}
			
		}
		
		return cnt;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 리스트 파일 저장
	 * @param ArrayList<CommonFile>
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int commonFileAdd(List<CommonFile> fileList) throws Exception{
		
		int cnt = 0;
		int fileListSize = 0;
		
		if(fileList != null && fileList.size() > 0){
			
			for(CommonFile item : fileList){
				
				if(!"".equals(StringUtil.nvl(item.getFileOrgName())) && item.getPidx() > 0){
					fileListSize++;
					item.setIdx(0);
				}
				
			}
			
			if(fileListSize > 0){
				cnt = commonFileDao.insertArr(fileList);
			}
			
		}
		
		return cnt;
		
	}
	
	@Override
	public List<CommonFile> getSelect(CommonFile commonFile) throws Exception{
		return commonFileDao.getSelect(commonFile);
	}
	
	@Override
	public List<CommonFile> select(ParamUtil<String, Object> paramUtil) throws Exception{
		return commonFileDao.select(paramUtil);
	}
	
	@Override
	public int commonFileDel(CommonFile commonFile) throws Exception{
		return commonFileDao.fileDel(commonFile);
	}
	
	@Override
	public int commonFileListDel(List<CommonFile> commonFileList) throws Exception{
		
		int cnt = 0;
		
		if(commonFileList != null && commonFileList.size() > 0){
			
			for(CommonFile item : commonFileList){
				
				//새로 등록된 파일이 있고 파일 고유번호가 있으면 파일 삭제
				if(item.getFileOrgName() != null && !"".equals(item.getFileOrgName()) && item.getIdx() > 0){
					cnt += commonFileDao.fileDel(item);
				}
				
			}
		
		}
		
		return cnt;
		
	}
	
	@Override
	public int commonFileIdxDel(int idx) throws Exception{
		return commonFileDao.fileIdxDel(idx);
	}
	
}
