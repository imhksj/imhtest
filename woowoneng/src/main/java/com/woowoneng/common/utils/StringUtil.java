package com.woowoneng.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.security.access.method.P;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : StringUtil.java
 * 기        능 : String 관련 유틸
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

public class StringUtil {

	/*
	 * @author MyungHo Lim
	 * 
	 * 문자열의 null 여부를 check하여 null일 경우 ""를 리턴한다.
	 * null이 아닐 경우에는 문자열의 trim()을 호출한 후 리턴한다.
	 */
	public static String nvl(Object obj) {
		if (obj == null) return "";
		return String.valueOf(obj).trim();
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * 문자열의 null 여부를 check하여 null일 경우 ""를 리턴한다.
	 * null이 아닐 경우에는 문자열의 trim()을 호출한 후 리턴한다.
	 */
	public static String nullToBlank(Object obj, String def) {
		def = nvl(def);
		if (obj == null || String.valueOf(obj).trim().equals("")) return def;
		return String.valueOf(obj).trim();
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 문자열의 null 여부를 check하여 null일 경우 디폴트 String을 시스템 줄바꿈 및 쌍따움표를 치환해서 리턴한다.
	 * null이 아닐 경우에는 문자열의 trim()을 호출한 후  시스템 줄바꿈 및 쌍따움표를 치환해서 리턴한다.
	 */
	public static String imgAltReturn(Object obj, String def) {
		def = nvl(def);
		if (obj == null || String.valueOf(obj).trim().equals("")) return (def.replaceAll("\r\n", " ")).replaceAll("\"", "'");
		return ((String.valueOf(obj).trim()).replaceAll("\r\n", " ")).replaceAll("\"", "'");
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 난수 발생
	 * @return
	 */
	public static String randomString(int len) {
		StringBuffer output = new StringBuffer();
		java.util.Random rnd = new Random();
		for (int length = len + rnd.nextInt(3); length-- > 0;) {
			int tmp = rnd.nextInt(36);
			if (tmp < 10)
				tmp += 48;
			else
				tmp += 87;
			output.append((char) tmp);
		}

		return output.toString();
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * 문자열 자르기
	 * @param String 체크 문자열
	 * @param int 체크 길이
	 * @param String 뒤에 붙을 문자열
	 * @return String
	 */
	public static String strLenLimit(String str, Integer len, String limitStr){
		
		str = nvl(str);
		
		if(str.length() > len){
			str = str.substring(0, len-5) + limitStr;
		}
		
		return str;
		
	}
		
	/*
	 * @author MyungHo Lim
	 * 
	 * 태그필터
	 * @return
	 */
	public static String tagFilter(String str){
    
		str = nvl(str);

		if(!"".equals(str)){
			
			str = str.replaceAll("&", "&#38;");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("#", "&#35;");
			str = str.replaceAll("\\(", "&#40;");
			str = str.replaceAll("\\)", "&#41;");
			str = str.replaceAll("\"", "&quot;");
		}

		return str;
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 문자 길이 체크
	 * @param String	체크 될 문자열
	 * @param int		체크할 문자열 길이
	 * @return boolean
	 */
	public static boolean strLenCheck(String str, int len){
    
		str = nvl(str);

		if(str.length() < len)	return false;
		
		return true;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 연속 중복 문자2개 이상 및 연속성 문자2개 이상 체크
	 * @param String
	 * @return boolean
	 */
	public static boolean continuityString(String str){
		
		try{
			
			str = nvl(str);
			
			StringBufferInputStream stream = new StringBufferInputStream(str);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
			
			int temp = -1;
			int[] asCode = new int[str.length()];
			int num = 0;
			
			while((temp=(int)br.read()) > -1)	asCode[num++] = temp;
			
			for(int i = 0; i < asCode.length; i++){
				
				if(asCode.length >= i+3){
				
					if(asCode[i] == asCode[i+1] && asCode[i+1] == asCode[i+2]){
						//System.out.print("중복 문자열 2개");
						return false;
		
					}else if(asCode[i] - asCode[i+1] == -1 && asCode[i+1] -  asCode[i+2] == -1){
						//System.out.print("연속된 문자열 3개");
						return false;
						
					}else if(asCode[i] - asCode[i+1] == 1 && asCode[i+1] -  asCode[i+2] == 1){
						//System.out.print("연속된 문자열 3개");
						return false;
						
					}
				
				}
				
			}
			
			
		}catch(Exception e){
			
			System.out.println(e.toString());
			
			return false;
			
		}
		
		return true;
	
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 패스워드 영문+숫자+특수문자 조홥 체크
	 * @param String
	 * @return boolean
	 */
	public static boolean strPasswordCheck(String str){
		
		str = nvl(str);
		
		String pattern = "([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])";

		Pattern p = Pattern.compile(pattern);
				
		return p.matcher(str).find();
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 이메일 패턴 체크
	 * @param String
	 * @return boolean
	 */
	public static boolean strEmailCheck(String str){
		
		str = nvl(str);
		
		String pattern = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

		Pattern p = Pattern.compile(pattern);
				
		return p.matcher(str).find();
		
	}

	/*
	 * @author MyungHo Lim
	 * 
	 * 패스워드 영문+숫자+특수문자 조홥 체크
	 * @param String
	 * @return boolean
	 */
	public static boolean strPasswordCheck2(String str) {
 
		int varDigit = 0;
		int varAlpha = 0;
		int varHex = 0;
		int varSum = 0;
		str = nvl(str);
		
		for (int i = 0; i < str.length(); i++) {
			
			char index = str.charAt(i);
 
			if (index >= '0' && index <= '9') {
				varDigit = 1;
			} else if ( (index >= 'a' && index <= 'z') || (index >= 'A' && index <= 'Z') ) {
				varAlpha = 1;
			} else if (index == '!' || index == '@' || index == '$'
					|| index == '%' || index == '^' || index == '&'
					|| index == '*') {
				varHex = 1;
			} 
		}
 
		varSum = varDigit + varAlpha + varHex;
				
		return varSum < 3?false:true;
	}

	
}