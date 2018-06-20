package com.woowoneng.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : Category.java
 * 기        능 : 카테고리 EntityBean  
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

public class Category {

	private int idx;			//고유번호
	@NotNull @Size(min=1)
	private String cName;		//카테고리 이름
	@NotNull @Size(min=1)
	private String cType;		//카테고리 타입
	@NotNull
	private int topIdx;			//최상위 카테고리 고유번호
	@NotNull
	private int pidx;			//상위 카테고리 고유번호
	@NotNull
	private int depthNum;		//뎁스 번호
	private String delYn;		//삭제여부
	private String regDate;		//등록일
	
	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Category [idx=" + idx + ", cName=" + cName + ", cType=" + cType + ", topIdx=" + topIdx + ", pidx="
				+ pidx + ", depthNum=" + depthNum + ", delYn=" + delYn + ", regDate=" + regDate + "]";
	}

	public Category(int idx, String cName, String cType, int topIdx, int pidx, int depthNum, String delYn,
			String regDate) {
		super();
		this.idx = idx;
		this.cName = cName;
		this.cType = cType;
		this.topIdx = topIdx;
		this.pidx = pidx;
		this.depthNum = depthNum;
		this.delYn = delYn;
		this.regDate = regDate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcType() {
		return cType;
	}

	public void setcType(String cType) {
		this.cType = cType;
	}

	public int getTopIdx() {
		return topIdx;
	}

	public void setTopIdx(int topIdx) {
		this.topIdx = topIdx;
	}

	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
	}

	public int getDepthNum() {
		return depthNum;
	}

	public void setDepthNum(int depthNum) {
		this.depthNum = depthNum;
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
	
}
