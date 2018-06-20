package com.woowoneng.entity;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : Notice.java
 * 기        능 : 공지사항 EntityBean  
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

public class Notice {

	private int idx;							//고유번호
	@NotNull @Size(min=1)
	private String title;						//제목
	@NotNull @Size(min=1)
	private String name;						//공지 작성자 이름
	@NotNull
	private int category;						//카테고리 고유번호
	private String content;						//내용
	private String noticeDate;					//공지등록일
	private String delYn;						//삭제여부
	private String regDate;						//등록일
	private int regAdminIdx;					//등록 관리자 아이디 고유번호
	private String modiDate;					//수정일
	private int modiAdminIdx;					//수정 관리자 아이디 고유번호
	private String delDate;						//삭제일
	private int delAdminIdx;					//삭제 관리자 아이디 고유번호
	private int hit;							//조회수
	private CommonFile commonFile;				//첨부파일 EntityBean
	private Category categorys;					//카테고리 EntityBean
	
	public Notice() {
		super();
	}

	@Override
	public String toString() {
		return "Notice [idx=" + idx + ", title=" + title + ", name=" + name + ", category=" + category + ", content="
				+ content + ", noticeDate=" + noticeDate + ", delYn=" + delYn + ", regDate=" + regDate
				+ ", regAdminIdx=" + regAdminIdx + ", modiDate=" + modiDate + ", modiAdminIdx=" + modiAdminIdx
				+ ", delDate=" + delDate + ", delAdminIdx=" + delAdminIdx + ", hit=" + hit + ", commonFile="
				+ commonFile + ", categorys=" + categorys + "]";
	}

	public Notice(int idx, String title, String name, int category, String content, String noticeDate, String delYn,
			String regDate, int regAdminIdx, String modiDate, int modiAdminIdx, String delDate, int delAdminIdx,
			int hit, CommonFile commonFile, Category categorys) {
		super();
		this.idx = idx;
		this.title = title;
		this.name = name;
		this.category = category;
		this.content = content;
		this.noticeDate = noticeDate;
		this.delYn = delYn;
		this.regDate = regDate;
		this.regAdminIdx = regAdminIdx;
		this.modiDate = modiDate;
		this.modiAdminIdx = modiAdminIdx;
		this.delDate = delDate;
		this.delAdminIdx = delAdminIdx;
		this.hit = hit;
		this.commonFile = commonFile;
		this.categorys = categorys;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
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

	public Category getCategorys() {
		return categorys;
	}

	public void setCategorys(Category categorys) {
		this.categorys = categorys;
	}
		
}
