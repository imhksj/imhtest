package com.woowoneng.common.utils;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : JSONUtil.java
 * 기        능 : Json 파싱  
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
public class JSONUtil {

	/*
	 * @author MyungHo Lim
	 *
	 * Object를 JSON String으로 변환하여 반환
	 * @param object
	 * @param excludes
	 * @return String
	 */
	public static String toJSON(Object object, String[] excludes) {
		
		JsonConfig jconfig = new JsonConfig();
		jconfig.setAllowNonStringKeys(true);
		jconfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		if(excludes!=null) jconfig.setExcludes(excludes);
		
		JSON json = JSONSerializer.toJSON(object, jconfig);
		return json.toString();
		
	}
}
