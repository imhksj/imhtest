package com.woowoneng.entity;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일   명 : Admin.java
 * 기         능 : 관리자 EntityBean  
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

public class Admin {

	private int idx;				//고유번호
	private String adminId;			//관리자 아이디
	private String adminPassword;	//관리자 비밀번호
	private String adminAuth;		//관리자 권한
	
	public Admin() {
		super();
	}

	@Override
	public String toString() {
		return "Admin [idx=" + idx + ", adminId=" + adminId
				+ ", adminPassword=" + adminPassword + ", adminAuth="
				+ adminAuth + "]";
	}

	public Admin(int idx, String adminId, String adminPassword, String adminAuth) {
		super();
		this.idx = idx;
		this.adminId = adminId;
		this.adminPassword = adminPassword;
		this.adminAuth = adminAuth;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminAuth() {
		return adminAuth;
	}

	public void setAdminAuth(String adminAuth) {
		this.adminAuth = adminAuth;
	}
	
}
