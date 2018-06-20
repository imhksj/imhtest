package com.woowoneng.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : Product.java
 * 기        능 : 상품 EntityBean  
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

public class Product {
	
	private int idx;					//고유번호
	@NotNull
	private int category1;				//카테고리1 CATEGORY 고유번호
	private int category2;				//카테고리2 CATEGORY 고유번호
	private int category3;				//카테고리3 CATEGORY 고유번호
	@NotNull @Size(min=1)
	private String prodNum;				//제품번호
	@NotNull @Size(min=1)
	private String prodName;			//제품이름
	@NotNull @Size(min=1)
	private String prodSize;			//제품사이즈
	@NotNull @Size(min=1)
	private String prodSpec1;			//제품사양1
	private String prodSpec2;			//제품사양2
	private String prodSpec3;			//제품사양3
	private String prodSpec4;			//제품사양4
	private String prodChoicSpec;		//선택사양
	private String delYn;				//삭제여부
	private int regAdminIdx;			//등록 관리자 고유번호
	private String regDate;				//등록일
	private int modiAdminIdx;			//수정 관리자 고유번호
	private String modiDate;			//수정일
	private int delAdminIdx;			//삭제 관리자 고유번호
	private String delDate;				//삭제일
	private CommonFile commonFile;		//공통파일 EntityBean
	
	public Product() {
		super();
	}

	@Override
	public String toString() {
		return "Product [idx=" + idx + ", category1=" + category1 + ", category2=" + category2 + ", category3="
				+ category3 + ", prodNum=" + prodNum + ", prodName=" + prodName + ", prodSize=" + prodSize
				+ ", prodSpec1=" + prodSpec1 + ", prodSpec2=" + prodSpec2 + ", prodSpec3=" + prodSpec3 + ", prodSpec4="
				+ prodSpec4 + ", prodChoicSpec=" + prodChoicSpec + ", delYn=" + delYn + ", regAdminIdx=" + regAdminIdx
				+ ", regDate=" + regDate + ", modiAdminIdx=" + modiAdminIdx + ", modiDate=" + modiDate
				+ ", delAdminIdx=" + delAdminIdx + ", delDate=" + delDate + ", commonFile=" + commonFile + "]";
	}

	public Product(int idx, int category1, int category2, int category3, String prodNum, String prodName,
			String prodSize, String prodSpec1, String prodSpec2, String prodSpec3, String prodSpec4,
			String prodChoicSpec, String delYn, int regAdminIdx, String regDate, int modiAdminIdx, String modiDate,
			int delAdminIdx, String delDate, CommonFile commonFile) {
		super();
		this.idx = idx;
		this.category1 = category1;
		this.category2 = category2;
		this.category3 = category3;
		this.prodNum = prodNum;
		this.prodName = prodName;
		this.prodSize = prodSize;
		this.prodSpec1 = prodSpec1;
		this.prodSpec2 = prodSpec2;
		this.prodSpec3 = prodSpec3;
		this.prodSpec4 = prodSpec4;
		this.prodChoicSpec = prodChoicSpec;
		this.delYn = delYn;
		this.regAdminIdx = regAdminIdx;
		this.regDate = regDate;
		this.modiAdminIdx = modiAdminIdx;
		this.modiDate = modiDate;
		this.delAdminIdx = delAdminIdx;
		this.delDate = delDate;
		this.commonFile = commonFile;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getCategory1() {
		return category1;
	}

	public void setCategory1(int category1) {
		this.category1 = category1;
	}

	public int getCategory2() {
		return category2;
	}

	public void setCategory2(int category2) {
		this.category2 = category2;
	}

	public int getCategory3() {
		return category3;
	}

	public void setCategory3(int category3) {
		this.category3 = category3;
	}

	public String getProdNum() {
		return prodNum;
	}

	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdSize() {
		return prodSize;
	}

	public void setProdSize(String prodSize) {
		this.prodSize = prodSize;
	}

	public String getProdSpec1() {
		return prodSpec1;
	}

	public void setProdSpec1(String prodSpec1) {
		this.prodSpec1 = prodSpec1;
	}

	public String getProdSpec2() {
		return prodSpec2;
	}

	public void setProdSpec2(String prodSpec2) {
		this.prodSpec2 = prodSpec2;
	}

	public String getProdSpec3() {
		return prodSpec3;
	}

	public void setProdSpec3(String prodSpec3) {
		this.prodSpec3 = prodSpec3;
	}

	public String getProdSpec4() {
		return prodSpec4;
	}

	public void setProdSpec4(String prodSpec4) {
		this.prodSpec4 = prodSpec4;
	}

	public String getProdChoicSpec() {
		return prodChoicSpec;
	}

	public void setProdChoicSpec(String prodChoicSpec) {
		this.prodChoicSpec = prodChoicSpec;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public int getRegAdminIdx() {
		return regAdminIdx;
	}

	public void setRegAdminIdx(int regAdminIdx) {
		this.regAdminIdx = regAdminIdx;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getModiAdminIdx() {
		return modiAdminIdx;
	}

	public void setModiAdminIdx(int modiAdminIdx) {
		this.modiAdminIdx = modiAdminIdx;
	}

	public String getModiDate() {
		return modiDate;
	}

	public void setModiDate(String modiDate) {
		this.modiDate = modiDate;
	}

	public int getDelAdminIdx() {
		return delAdminIdx;
	}

	public void setDelAdminIdx(int delAdminIdx) {
		this.delAdminIdx = delAdminIdx;
	}

	public String getDelDate() {
		return delDate;
	}

	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}

	public CommonFile getCommonFile() {
		return commonFile;
	}

	public void setCommonFile(CommonFile commonFile) {
		this.commonFile = commonFile;
	}
	
}
