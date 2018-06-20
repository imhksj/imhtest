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

public class Faq {

	private int idx;							//고유번호
	@NotNull @Size(min=1)
	private String name;						//작성자 이름
	@NotNull @Size(min=1)
	private String question;					//질문
	@NotNull
	private String answer;						//답변
	private String delYn;						//삭제여부
	private String regDate;						//등록일
	private int regAdminIdx;					//등록 관리자 아이디 고유번호
	private String modiDate;					//수정일
	private int modiAdminIdx;					//수정 관리자 아이디 고유번호
	private String delDate;						//삭제일
	private int delAdminIdx;					//삭제 관리자 아이디 고유번호
	private CommonFile commonFile;				//첨부파일
	private int hit;							//조회수
	
	public Faq() {
		super();
	}

	@Override
	public String toString() {
		return "Faq [idx=" + idx + ", name=" + name + ", question=" + question + ", answer=" + answer + ", delYn="
				+ delYn + ", regDate=" + regDate + ", regAdminIdx=" + regAdminIdx + ", modiDate=" + modiDate
				+ ", modiAdminIdx=" + modiAdminIdx + ", delDate=" + delDate + ", delAdminIdx=" + delAdminIdx
				+ ", commonFile=" + commonFile + ", hit=" + hit + "]";
	}

	public Faq(int idx, String name, String question, String answer, String delYn, String regDate, int regAdminIdx,
			String modiDate, int modiAdminIdx, String delDate, int delAdminIdx, CommonFile commonFile, int hit) {
		super();
		this.idx = idx;
		this.name = name;
		this.question = question;
		this.answer = answer;
		this.delYn = delYn;
		this.regDate = regDate;
		this.regAdminIdx = regAdminIdx;
		this.modiDate = modiDate;
		this.modiAdminIdx = modiAdminIdx;
		this.delDate = delDate;
		this.delAdminIdx = delAdminIdx;
		this.commonFile = commonFile;
		this.hit = hit;
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

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
