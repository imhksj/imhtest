package com.woowoneng.common.interceptor;

import java.util.ArrayList;




import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.integration.MessageChannel;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mysql.fabric.xmlrpc.base.Param;
import com.woowoneng.common.utils.*;
import com.woowoneng.entity.CommonFile;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일   명 : FileUploadHandlerInterceptorAdapter.java
 * 기         능 : 파일 업로드 Interceptor
 *           MultipartHttpServletRequest 의 Parameter 처리 및 파일 업로드 
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

public class FileUploadHandlerInterceptorAdapter extends HandlerInterceptorAdapter implements MessageSourceAware{
	
	Log log = LogFactory.getLog(this.getClass());
		
	@Value("#{global['file.upload.path']}")
	private String FILE_PATH;
	
	private MessageSource messageSource;
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}
	
	
	/*
	 * @author MyungHo Lim
	 * 
     * MultipartHttpServletRequest의 값들을 HttpServletRequest에 셋팅
	 * @param 	HttpServletRequest
	 * @param 	HttpServletResponse
	 * @param 	Object
	 * @return 	boolean
	 * @throws Exception
     */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse rep, Object handler) throws Exception {
			
			if (req instanceof MultipartHttpServletRequest) {
				
				final MultipartHttpServletRequest multiRequest  = (MultipartHttpServletRequest) req;
				
				final Map<String, MultipartFile> files = multiRequest.getFileMap();
				
				java.io.File uploadPath = new java.io.File(req.getSession().getServletContext().getRealPath(java.io.File.separator)+FILE_PATH);
				
				System.out.println("uploadPath : " + uploadPath);
				
				if(!uploadPath.exists() || uploadPath.isFile()){
					
					uploadPath.mkdir();

					if(uploadPath.exists()){
						System.out.println("업로드 폴더 생성");
					}
					
				}
				
				Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
				
				List<CommonFile> fileList = new ArrayList<CommonFile>();
				
				ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
				
				int fileCnt = 0;
				
				while (itr.hasNext()) {
					
					MultipartFile file;
					String fileFormName = "";
					String orgFileName = "";
					String sysFileName = "";
					String fileUpLoadPath = "";
					String file_extension = "";
					String fileGroup = param.getStringArray("fileGroup")[fileCnt];
					String fileType = param.getStringArray("fileType")[fileCnt];
					int fileIdx = param.getIntArray("fileIdx")[fileCnt];
					int orderNum = param.getIntArray("orderNum")[fileCnt];
					
					Entry<String, MultipartFile> entry = itr.next();

					file = entry.getValue();
					fileFormName = file.getName();
					
					if (!"".equals(file.getOriginalFilename()) && file.getSize() > 0) {
						
						file_extension = (file.getOriginalFilename()).substring( (file.getOriginalFilename()).lastIndexOf(".") );
						orgFileName = file.getOriginalFilename();
						sysFileName = Long.toString(System.currentTimeMillis()) + StringUtil.randomString(5) + file_extension;
						fileUpLoadPath = uploadPath.toString();
						
						for(int i = 0; i < fileGroup.split("/").length; i++){
							
							fileUpLoadPath += java.io.File.separator + fileGroup.split("/")[i];
							
							java.io.File newUploadPath = new java.io.File(fileUpLoadPath);
							
							if(!newUploadPath.exists() || newUploadPath.isFile()){
								
								newUploadPath.mkdir();

								if(newUploadPath.exists()){
									System.out.println(fileGroup.split("/")[i] + "업로드 폴더 생성");
								}
								
							}
							
						}

					}

					req.setAttribute(entry.getKey(), sysFileName);

					CommonFile fileEntity = new CommonFile();
					fileEntity.setFile(file);
					fileEntity.setIdx(fileIdx);
					fileEntity.setFileGroup(fileGroup);
					fileEntity.setFileType(fileType);
					fileEntity.setFileSysName(sysFileName);
					fileEntity.setFileOrgName(orgFileName);
					fileEntity.setFileFormName(fileFormName);
					fileEntity.setOrderNum(orderNum);
					fileList.add(fileEntity);
					
					fileCnt ++;
					
				}

				req.setAttribute("fileList", fileList);
				
			}

		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
	
	/*
	 * @author MyungHo Lim
	 * 
     * HttpServletRequest의 파일정보 업로드
	 * @param 	HttpServletRequest
	 * @param 	HttpServletResponse
	 * @param 	Object
	 * @param 	ModelAndView
	 * @throws Exception
     */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		List<CommonFile> fileList = (List<CommonFile>) request.getAttribute("fileList");

		if (fileList != null) {
			
			String fileUpLoadPath = request.getSession().getServletContext().getRealPath(java.io.File.separator)+FILE_PATH;
	
			MultipartFileUpload.fileUpload(fileUpLoadPath, (List<CommonFile>) request.getAttribute("fileList"));
						
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
}
