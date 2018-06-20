package com.woowoneng.entity;

import org.springframework.web.multipart.MultipartFile;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : CommonFile.java
 * 기        능 : 공통파일 EntityBean  
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

public class CommonFile {

	private MultipartFile file;			
	private int idx;					//고유번호
	private String fileFormName;		//파일 폼네임
	private String fileOrgName;			//오리지널 파일명
	private String fileSysName;			//시스템 파일명
	private String fileGroup;			//파일 그룹
	private String fileType;			//파일타입 (LIST_IMG, PDF 등등 직접정의 )
	private int pidx;					//관계 테이블 데이터  IDX(고유번호)
	private String delYn;				//삭제여부 (Y : N)
	private String regDate;				//등록일
	private String delDate;				//삭제일
	private int orderNum;				//정렬순서 번호

	public CommonFile() {
		super();
	}

	@Override
	public String toString() {
		return "CommonFile [file=" + file + ", idx=" + idx + ", fileFormName=" + fileFormName + ", fileOrgName="
				+ fileOrgName + ", fileSysName=" + fileSysName + ", fileGroup=" + fileGroup + ", fileType=" + fileType
				+ ", pidx=" + pidx + ", delYn=" + delYn + ", regDate=" + regDate + ", delDate=" + delDate
				+ ", orderNum=" + orderNum + "]";
	}

	public CommonFile(MultipartFile file, int idx, String fileFormName, String fileOrgName, String fileSysName,
			String fileGroup, String fileType, int pidx, String delYn, String regDate, String delDate, int orderNum) {
		super();
		this.file = file;
		this.idx = idx;
		this.fileFormName = fileFormName;
		this.fileOrgName = fileOrgName;
		this.fileSysName = fileSysName;
		this.fileGroup = fileGroup;
		this.fileType = fileType;
		this.pidx = pidx;
		this.delYn = delYn;
		this.regDate = regDate;
		this.delDate = delDate;
		this.orderNum = orderNum;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getFileFormName() {
		return fileFormName;
	}

	public void setFileFormName(String fileFormName) {
		this.fileFormName = fileFormName;
	}

	public String getFileOrgName() {
		return fileOrgName;
	}

	public void setFileOrgName(String fileOrgName) {
		this.fileOrgName = fileOrgName;
	}

	public String getFileSysName() {
		return fileSysName;
	}

	public void setFileSysName(String fileSysName) {
		this.fileSysName = fileSysName;
	}

	public String getFileGroup() {
		return fileGroup;
	}

	public void setFileGroup(String fileGroup) {
		this.fileGroup = fileGroup;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getPidx() {
		return pidx;
	}

	public void setPidx(int pidx) {
		this.pidx = pidx;
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

	public String getDelDate() {
		return delDate;
	}

	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
}
