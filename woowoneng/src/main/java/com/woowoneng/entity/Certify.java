package com.woowoneng.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : Certify.java
 * 기        능 : 인증현황 EntityBean  
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

public class Certify {

	private int idx;							//고유번호
	@NotNull @Size(min=1)
	private String certifyName;					//인증서명
	@NotNull @Size(min=1)
	private String certifyNum;					//인증번호
	private String delYn;						//삭제여부
	private String regDate;						//등록일
	private int regAdminIdx;					//등록 관리자 아이디 고유번호
	private String modiDate;					//수정일
	private int modiAdminIdx;					//수정 관리자 아이디 고유번호
	private String delDate;						//삭제일
	private int delAdminIdx;					//삭제 관리자 아이디 고유번호
	private CommonFile commonFile;				//첨부파일
	
	public Certify() {
		super();
	}

	@Override
	public String toString() {
		return "Certify [idx=" + idx + ", certifyName=" + certifyName + ", certifyNum=" + certifyNum + ", delYn="
				+ delYn + ", regDate=" + regDate + ", regAdminIdx=" + regAdminIdx + ", modiDate=" + modiDate
				+ ", modiAdminIdx=" + modiAdminIdx + ", delDate=" + delDate + ", delAdminIdx=" + delAdminIdx
				+ ", commonFile=" + commonFile + "]";
	}

	public Certify(int idx, String certifyName, String certifyNum, String delYn, String regDate, int regAdminIdx,
			String modiDate, int modiAdminIdx, String delDate, int delAdminIdx, CommonFile commonFile) {
		super();
		this.idx = idx;
		this.certifyName = certifyName;
		this.certifyNum = certifyNum;
		this.delYn = delYn;
		this.regDate = regDate;
		this.regAdminIdx = regAdminIdx;
		this.modiDate = modiDate;
		this.modiAdminIdx = modiAdminIdx;
		this.delDate = delDate;
		this.delAdminIdx = delAdminIdx;
		this.commonFile = commonFile;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getCertifyName() {
		return certifyName;
	}

	public void setCertifyName(String certifyName) {
		this.certifyName = certifyName;
	}

	public String getCertifyNum() {
		return certifyNum;
	}

	public void setCertifyNum(String certifyNum) {
		this.certifyNum = certifyNum;
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

	public int getRegAdminIdx() {
		return regAdminIdx;
	}

	public void setRegAdminIdx(int regAdminIdx) {
		this.regAdminIdx = regAdminIdx;
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
	
}
