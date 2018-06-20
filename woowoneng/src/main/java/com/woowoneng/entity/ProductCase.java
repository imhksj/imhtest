package com.woowoneng.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : ProductCase.java
 * 기        능 : 납품사례 EntityBean  
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

public class ProductCase {

	private int idx;							//고유번호
	@NotNull @Size(min=1)
	private String vendor;						//납품처
	@NotNull @Size(min=1)
	private String deliverDate;					//납품일
	private String content;						//내용
	private String delYn;						//삭제여부
	private String regDate;						//등록일
	private int regAdminIdx;					//등록 관리자 아이디 고유번호
	private String modiDate;					//수정일
	private int modiAdminIdx;					//수정 관리자 아이디 고유번호
	private String delDate;						//삭제일
	private int delAdminIdx;					//삭제 관리자 아이디 고유번호
	private int hit;							//조회수
	private CommonFile commonFile;				//공통파일 EntityBean
	private List<CommonFile> commonFileList;	//공통파일 EntityBean 리스트
	
	public ProductCase() {
		super();
	}

	@Override
	public String toString() {
		return "ProductCase [idx=" + idx + ", vendor=" + vendor + ", deliverDate=" + deliverDate + ", content="
				+ content + ", delYn=" + delYn + ", regDate=" + regDate + ", regAdminIdx=" + regAdminIdx + ", modiDate="
				+ modiDate + ", modiAdminIdx=" + modiAdminIdx + ", delDate=" + delDate + ", delAdminIdx=" + delAdminIdx
				+ ", hit=" + hit + ", commonFile=" + commonFile + ", commonFileList=" + commonFileList + "]";
	}

	public ProductCase(int idx, String vendor, String deliverDate, String content, String delYn, String regDate,
			int regAdminIdx, String modiDate, int modiAdminIdx, String delDate, int delAdminIdx, int hit,
			CommonFile commonFile, List<CommonFile> commonFileList) {
		super();
		this.idx = idx;
		this.vendor = vendor;
		this.deliverDate = deliverDate;
		this.content = content;
		this.delYn = delYn;
		this.regDate = regDate;
		this.regAdminIdx = regAdminIdx;
		this.modiDate = modiDate;
		this.modiAdminIdx = modiAdminIdx;
		this.delDate = delDate;
		this.delAdminIdx = delAdminIdx;
		this.hit = hit;
		this.commonFile = commonFile;
		this.commonFileList = commonFileList;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(String deliverDate) {
		this.deliverDate = deliverDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
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
