package com.woowoneng.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : Ad.java
 * 기        능 : 광고홍보 EntityBean  
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

public class Ad {

	private int idx;							//고유번호
	@NotNull @Size(min=1)
	private String media;						//매체
	@NotNull @Size(min=1)
	private String productionDate;				//제작일
	@NotNull @Size(min=1)
	private String note;						//비고
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
	
	public Ad() {
		super();
	}

	@Override
	public String toString() {
		return "Ad [idx=" + idx + ", media=" + media + ", productionDate=" + productionDate + ", note=" + note
				+ ", content=" + content + ", delYn=" + delYn + ", regDate=" + regDate + ", regAdminIdx=" + regAdminIdx
				+ ", modiDate=" + modiDate + ", modiAdminIdx=" + modiAdminIdx + ", delDate=" + delDate
				+ ", delAdminIdx=" + delAdminIdx + ", hit=" + hit + ", commonFile=" + commonFile + ", commonFileList="
				+ commonFileList + "]";
	}

	public Ad(int idx, String media, String productionDate, String note, String content, String delYn, String regDate,
			int regAdminIdx, String modiDate, int modiAdminIdx, String delDate, int delAdminIdx, int hit,
			CommonFile commonFile, List<CommonFile> commonFileList) {
		super();
		this.idx = idx;
		this.media = media;
		this.productionDate = productionDate;
		this.note = note;
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

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
