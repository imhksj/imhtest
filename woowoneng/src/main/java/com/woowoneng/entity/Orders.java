package com.woowoneng.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : Orders.java
 * 기        능 : 주문관리 EntityBean  
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

public class Orders {
	
	private int idx;				//고유번호
	@NotNull
	private int amount;				//수장
	@NotNull @Size(min=1)
	private String tptb;			//담당자
	@NotNull @Size(min=1)
	private String deliverAddr;		//납품주소
	private String comment;			//요청사항
	private int productIdx;			//상품 고유번호
	private int memberIdx;			//회원 고유번호
	private String procType;		//주문 상태 (신규주문:주문확인:주문취소:납품완료)
	private String regDate;			//등록일 (발주일)
	private int modiAdminIdx;		//수정 관리자 아이디 고유번호
	private String modiDate;		//수정일
	private Product product;		//상품 EntityBean
	private Member member;			//회원 EntityBean
	
	public Orders() {
		super();
	}

	@Override
	public String toString() {
		return "Orders [idx=" + idx + ", amount=" + amount + ", tptb=" + tptb + ", deliverAddr=" + deliverAddr
				+ ", comment=" + comment + ", productIdx=" + productIdx + ", memberIdx=" + memberIdx + ", procType="
				+ procType + ", regDate=" + regDate + ", modiAdminIdx=" + modiAdminIdx + ", modiDate=" + modiDate
				+ ", product=" + product + ", member=" + member + "]";
	}

	public Orders(int idx, int amount, String tptb, String deliverAddr, String comment, int productIdx, int memberIdx,
			String procType, String regDate, int modiAdminIdx, String modiDate, Product product, Member member) {
		super();
		this.idx = idx;
		this.amount = amount;
		this.tptb = tptb;
		this.deliverAddr = deliverAddr;
		this.comment = comment;
		this.productIdx = productIdx;
		this.memberIdx = memberIdx;
		this.procType = procType;
		this.regDate = regDate;
		this.modiAdminIdx = modiAdminIdx;
		this.modiDate = modiDate;
		this.product = product;
		this.member = member;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTptb() {
		return tptb;
	}

	public void setTptb(String tptb) {
		this.tptb = tptb;
	}

	public String getDeliverAddr() {
		return deliverAddr;
	}

	public void setDeliverAddr(String deliverAddr) {
		this.deliverAddr = deliverAddr;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getProductIdx() {
		return productIdx;
	}

	public void setProductIdx(int productIdx) {
		this.productIdx = productIdx;
	}

	public int getMemberIdx() {
		return memberIdx;
	}

	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}

	public String getProcType() {
		return procType;
	}

	public void setProcType(String procType) {
		this.procType = procType;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
}
