package com.woowoneng.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CustomerRequest.java
 * 기        능 : 고객관리 EntityBean  
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

public class CustomerRequest {

	private int idx;							//고유번호
	@NotNull @Size(min=1)
	private String name;						//성명
	private String companyName;					//상호명
	@NotNull @Size(min=1)
	private String tel;							//연락처
	private String email;						//이메일
	private String addr;						//주소
	private String buyYear;						//구입 년도
	private String buyMonth;					//구입 월
	private String productName;					//제품명
	private int catalog;						//카달로고 (카테고리 고유번호)
	private String content;						//내용
	@NotNull @Size(min=1)
	private String privacyYn;					//개인정보 동의여부 Y:N
	@NotNull @Size(min=1)
	private String requestType;					//요청 타입 (문의하기, AS요청, 카탈로그 요청 등등)
	private String procType;					//처리상태 신규:검토:완료
	private String delYn;						//삭제여부
	private String regDate;						//등록일
	private int memberIdx;						//등록 회원 아이디 고유번호
	private String modiDate;					//수정일
	private int modiAdminIdx;					//수정 관리자 아이디 고유번호
	private String delDate;						//삭제일
	private int delAdminIdx;					//삭제 관리자 아이디 고유번호
	private CommonFile commonFile;				//공통파일 EntityBean 리스트
	private Category catalogCategory;			//카달로그 카테고리 정보
	
	public CustomerRequest() {
		super();
	}

	@Override
	public String toString() {
		return "CustomerRequest [idx=" + idx + ", name=" + name + ", companyName=" + companyName + ", tel=" + tel
				+ ", email=" + email + ", addr=" + addr + ", buyYear=" + buyYear + ", buyMonth=" + buyMonth
				+ ", productName=" + productName + ", catalog=" + catalog + ", content=" + content + ", privacyYn="
				+ privacyYn + ", requestType=" + requestType + ", procType=" + procType + ", delYn=" + delYn
				+ ", regDate=" + regDate + ", memberIdx=" + memberIdx + ", modiDate=" + modiDate + ", modiAdminIdx="
				+ modiAdminIdx + ", delDate=" + delDate + ", delAdminIdx=" + delAdminIdx + ", commonFile=" + commonFile
				+ ", catalogCategory=" + catalogCategory + "]";
	}

	public CustomerRequest(int idx, String name, String companyName, String tel, String email, String addr,
			String buyYear, String buyMonth, String productName, int catalog, String content, String privacyYn,
			String requestType, String procType, String delYn, String regDate, int memberIdx, String modiDate,
			int modiAdminIdx, String delDate, int delAdminIdx, CommonFile commonFile, Category catalogCategory) {
		super();
		this.idx = idx;
		this.name = name;
		this.companyName = companyName;
		this.tel = tel;
		this.email = email;
		this.addr = addr;
		this.buyYear = buyYear;
		this.buyMonth = buyMonth;
		this.productName = productName;
		this.catalog = catalog;
		this.content = content;
		this.privacyYn = privacyYn;
		this.requestType = requestType;
		this.procType = procType;
		this.delYn = delYn;
		this.regDate = regDate;
		this.memberIdx = memberIdx;
		this.modiDate = modiDate;
		this.modiAdminIdx = modiAdminIdx;
		this.delDate = delDate;
		this.delAdminIdx = delAdminIdx;
		this.commonFile = commonFile;
		this.catalogCategory = catalogCategory;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getBuyYear() {
		return buyYear;
	}

	public void setBuyYear(String buyYear) {
		this.buyYear = buyYear;
	}

	public String getBuyMonth() {
		return buyMonth;
	}

	public void setBuyMonth(String buyMonth) {
		this.buyMonth = buyMonth;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCatalog() {
		return catalog;
	}

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPrivacyYn() {
		return privacyYn;
	}

	public void setPrivacyYn(String privacyYn) {
		this.privacyYn = privacyYn;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getProcType() {
		return procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getModiDate() {
		return modiDate;
	}

	public void setModiDate(String modiDate) {
		this.modiDate = modiDate;
	}

	public int getModiAdminIdx() {
		return modiAdminIdx;
	}

	public void setModiAdminIdx(int modiAdminIdx) {
		this.modiAdminIdx = modiAdminIdx;
	}

	public String getDelDate() {
		return delDate;
	}

	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}

	public int getDelAdminIdx() {
		return delAdminIdx;
	}

	public void setDelAdminIdx(int delAdminIdx) {
		this.delAdminIdx = delAdminIdx;
	}

	public CommonFile getCommonFile() {
		return commonFile;
	}

	public void setCommonFile(CommonFile commonFile) {
		this.commonFile = commonFile;
	}

	public Category getCatalogCategory() {
		return catalogCategory;
	}

	public void setCatalogCategory(Category catalogCategory) {
		this.catalogCategory = catalogCategory;
	}
	
}
