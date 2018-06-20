package com.woowoneng.common.utils;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.woowoneng.entity.CommonFile;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CommonUtil.java
 * 기        능 : Global함수  
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

public class CommonUtil {

	private Log log = LogFactory.getLog(this.getClass());
	
	public CommonUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * @author MyungHo Lim
	 * 
     * URL 맵핑에 따른 파일유무 검사후 viewName 리턴
	 * @param 	HttpServletRequest
	 * @param 	HttpServletResponse
	 * @return 	String
	 * @throws Exception
     */
	public static String returnUrl(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		String ctxRoot = req.getContextPath();
        String uri = req.getRequestURI();

        int len = 0;
 
        if(ctxRoot != null && ctxRoot.length() > 0) {
            len = uri.indexOf(ctxRoot) + ctxRoot.length();
        }
        
        uri = uri.substring(len);
        
        if(uri.split("/").length < 2) {
        	uri = "/error/error.ajx";
        }

        uri = "" + uri.substring(1,uri.lastIndexOf("."));
        
        String filePath = req.getSession().getServletContext().getRealPath(File.separator)+"WEB-INF"+File.separator+"views"+File.separator;

        File file = new File( filePath + uri + ".jsp" );
        
        if(!file.isFile()){
        	uri = "/error/error.ajx";
        }
        
        return uri;
        
	}
	
	
	
	/*
	 * @author MyungHo Lim
	 * 
     * Session데이타 호출
	 * @param 	HttpServletRequest
	 * @param 	String 
	 * @return 	String	저장된 세션 이름
	 * @throws Exception
     */
	public static String getSessionData(HttpServletRequest req, String sessionName) throws Exception{
		
		String value = StringUtil.nvl((String)req.getSession().getAttribute(sessionName));

		return value;
	}
	
	
	/*
	 * @author MyungHo Lim
	 * 
     * Number 램덤 리턴 (현재날짜(20150412) + 랜덤함수 Long 4자리)
	 * @return 	String
	 * @throws Exception
     */
	public static String getRandomNumberStr() throws Exception{
		
		try{
			Random rand = new Random();
			
			long randLong = rand.nextLong();
			
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			
			String dateStr = dateFormat.format(cal.getTime());
			String randLongStr = (String.valueOf(randLong)).substring((String.valueOf(randLong)).length()-4);
			
			return dateStr+randLongStr;
			
		}catch(Exception e){
			System.out.println(e.toString());
			return "";
		}
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
     * 파일 검증
	 * @return 	String
	 * @throws Exception
     */
	public static boolean getFileTypeValidate(List<CommonFile> file, String type){
		
		if(file == null) return true;
		
		HashMap<String, String> fileTypeMap = new HashMap<>();
		fileTypeMap.put("image", "jpg,jpeg,gif,png");
		fileTypeMap.put("doc", "hwp,pdf,doc,ppt,pptx,docx,xls,xlsx,zip");
		fileTypeMap.put("media", "avi,mpg,mpeg,asf,wma,flv");
		fileTypeMap.put("all", "jpg,jpeg,gif,png,hwp,pdf,doc,ppt,pptx,docx,xls,xlsx,zip,avi,mpg,mpeg,asf,wma,flv");
		
		if(fileTypeMap.get(type) == null)	return false;
		
		for(CommonFile item : file){
			
			if(item != null){
				
				String fileName = item.getFileOrgName().substring(item.getFileOrgName().lastIndexOf(".")+1);
				
				if(fileTypeMap.get(type).indexOf(fileName.toLowerCase()) <= -1)	return false;
				
			}
				
		}
		
		return true;
	}
	
	/*
	 * @author MyungHo Lim
	 * 
     * 파일 검증
	 * @return 	String
	 * @throws Exception
     */
	public static boolean getFileTypeValidate(CommonFile file, String type){
		
		if(file == null) return true;
		
		HashMap<String, String> fileTypeMap = new HashMap<>();
		fileTypeMap.put("image", "jpg,jpeg,gif,png");
		fileTypeMap.put("doc", "hwp,pdf,doc,ppt,pptx,docx,xls,xlsx,zip");
		fileTypeMap.put("media", "avi,mpg,mpeg,asf,wma,flv");
		fileTypeMap.put("all", "jpg,jpeg,gif,png,hwp,pdf,doc,ppt,pptx,docx,xls,xlsx,zip,avi,mpg,mpeg,asf,wma,flv");
		
		if(fileTypeMap.get(type) == null)	return false;
			
		String fileName = file.getFileOrgName().substring(file.getFileOrgName().lastIndexOf(".")+1);
		
		if(fileTypeMap.get(type).indexOf(fileName.toLowerCase()) <= -1)	return false;
		
		return true;
	}
	
	/*
	 * @author MyungHo Lim
	 * 
     * 파일 경로 검증
	 * @return 	String
	 * @throws Exception
     */
	public static boolean getFilePathValidate(List<CommonFile> file, String pathList){
		
		if(file == null) return true;
		
		for(CommonFile item : file){
			
			String fileGroup = item.getFileGroup();
			
			if(pathList.indexOf(fileGroup) <= -1)	return false;
				
		}
		
		return true;
	}
}
