package com.woowoneng.common.utils;

import java.util.ArrayList;



import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

import com.woowoneng.entity.CommonFile;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : MultipartFileUpload.java
 * 기        능 : 파일 업로드
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


public class MultipartFileUpload {

	static Log log = LogFactory.getLog(MultipartFileUpload.class);
	
	public MultipartFileUpload() {
		// TODO Auto-generated constructor stub
	}

	
	/*
	 * @author MyungHo Lim
	 * 
     * 파일 업로드
	 * @param 	HttpServlStringetRequest
	 * @param 	ArrayList<File>
	 * @throws Exception
     */
	public static void fileUpload(String rootPath, List<CommonFile> fileList) throws Exception{

		if (CollectionUtils.isNotEmpty(fileList)) {
			
			for (CommonFile entity : fileList) {

				if (entity.getFileOrgName() != null && !"".equals(entity.getFileOrgName()) && (entity.getPidx() > 0 || "EDITOR".equals(entity.getFileGroup())) ) {
					
					String filePath = rootPath;
					for(int i = 0; i < (entity.getFileGroup()).split("/").length; i++)	filePath += java.io.File.separator + (entity.getFileGroup()).split("/")[i];
					filePath += java.io.File.separator + entity.getFileSysName();
					
					entity.getFile().transferTo(new java.io.File(filePath));
					
				}
				
			}
			
		}
		
	}
	
}
