package com.woowoneng.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : Member.java
 * 기        능 : 회원 EntityBean  
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

public class Member {

	private int idx;								//고유번호
	@NotNull @Size(min=1)
	private String memberId;						//회원 아이디
	@NotNull @Size(min=1)
	private String memberPwd;						//회원 비밀번호
	@NotNull @Size(min=1)
	private String companyName;						//회사명
	@NotNull @Size(min=1)
	private String region;							//지역
	private String businessLicNum;					//사업자등록번호
	private String email;							//이메일
	@NotNull @Size(min=1)
	private String tel;								//전화번호
	private String fax;								//팩스번호
	private String companyAddr;						//회사주소
	private String tptb1;							//책임자1
	private String tptb1Phone;						//책임자1 전화번호
	private String tptb2;							//책임자2
	private String tptb2Phone;						//책임자2 전화번호
	private String note;							//비고
	private String delYn;							//삭제여부
	private String regDate;							//등록일
	private CommonFile commonFile;					//파일 EntityBean
	private List<CommonFile> commonFileList;		//파일 EntityBean List
	
	public Member() {
		super();
	}

	@Override
	public String toString() {
		return "Member [idx=" + idx + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", companyName="
				+ companyName + ", region=" + region + ", businessLicNum=" + businessLicNum + ", email=" + email
				+ ", tel=" + tel + ", fax=" + fax + ", companyAddr=" + companyAddr + ", tptb1=" + tptb1
				+ ", tptb1Phone=" + tptb1Phone + ", tptb2=" + tptb2 + ", tptb2Phone=" + tptb2Phone + ", note=" + note
				+ ", delYn=" + delYn + ", regDate=" + regDate + ", commonFile=" + commonFile + ", commonFileList="
				+ commonFileList + "]";
	}

	public Member(int idx, String memberId, String memberPwd, String companyName, String region, String businessLicNum,
			String email, String tel, String fax, String companyAddr, String tptb1, String tptb1Phone, String tptb2,
			String tptb2Phone, String note, String delYn, String regDate, CommonFile commonFile,
			List<CommonFile> commonFileList) {
		super();
		this.idx = idx;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.companyName = companyName;
		this.region = region;
		this.businessLicNum = businessLicNum;
		this.email = email;
		this.tel = tel;
		this.fax = fax;
		this.companyAddr = companyAddr;
		this.tptb1 = tptb1;
		this.tptb1Phone = tptb1Phone;
		this.tptb2 = tptb2;
		this.tptb2Phone = tptb2Phone;
		this.note = note;
		this.delYn = delYn;
		this.regDate = regDate;
		this.commonFile = commonFile;
		this.commonFileList = commonFileList;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getBusinessLicNum() {
		return businessLicNum;
	}

	public void setBusinessLicNum(String businessLicNum) {
		this.businessLicNum = businessLicNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	public String getTptb1() {
		return tptb1;
	}

	public void setTptb1(String tptb1) {
		this.tptb1 = tptb1;
	}

	public String getTptb1Phone() {
		return tptb1Phone;
	}

	public void setTptb1Phone(String tptb1Phone) {
		this.tptb1Phone = tptb1Phone;
	}

	public String getTptb2() {
		return tptb2;
	}

	public void setTptb2(String tptb2) {
		this.tptb2 = tptb2;
	}

	public String getTptb2Phone() {
		return tptb2Phone;
	}

	public void setTptb2Phone(String tptb2Phone) {
		this.tptb2Phone = tptb2Phone;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public CommonFile getCommonFile() {
		return commonFile;
	}

	public void setCommonFile(CommonFile commonFile) {
		this.commonFile = commonFile;
	}

	public List<CommonFile> getCommonFileList() {
		return commonFileList;
	}

	public void setCommonFileList(List<CommonFile> commonFileList) {
		this.commonFileList = commonFileList;
	}
	
}
