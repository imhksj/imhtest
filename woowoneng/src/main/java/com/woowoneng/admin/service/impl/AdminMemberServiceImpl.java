package com.woowoneng.admin.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;

import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.admin.service.AdminMemberService;
import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.common.utils.PagedList;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.dao.MemberDao;
import com.woowoneng.entity.CommonFile;
import com.woowoneng.entity.Member;
import com.woowoneng.service.CommonFileService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminMemberServiceImpl.java
 * 기        능 : 어드민 회원관리 Service
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

@Service("adminMemberService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminMemberServiceImpl extends AbstractGenericManager implements AdminMemberService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private MemberDao memberDao;
		
	@Autowired
	private CommonFileService commonFileService;
	
	@Override
	public Member getSelect(ParamUtil<String, Object> param) throws Exception{
		
		String[] fileTypeArr = {"BANKBOOK_COPY_IMG", "BUSINESS_LIC_IMG"};
		
		Member member = memberDao.select(param);
		
		if(member != null){
			
			param.clear();
			param.put("pidx", member.getIdx());
			param.put("fileGroup", "ADMIN/MEMBER");
			param.put("fileTypeArr", fileTypeArr);
			
			member.setCommonFileList(commonFileService.select(param));
			
		}
		
		return member;
	}
	
	@Override
	public PagedList<Member> getSelectList(ParamUtil<String, Object> param, int reqPage, int pageSize) throws Exception{
		return (PagedList<Member>) list("com.woowoneng.dao.MemberDao", "selectList", param, reqPage, pageSize);
	}
	
	@Override
	public int addMember(Member member) throws Exception{
		
		int cnt = memberDao.insert(member);

		//DB에 저장이 되었으면
		if(member.getIdx() > 0){
			commonFileService.commonFileAdd(member.getCommonFileList(), member.getIdx());
		}
		
		return cnt;
	}
	
	@Override
	public int modiMember(Member member, ParamUtil<String, Object> param) throws Exception{
		
		int cnt = memberDao.update(member);
		String[] fileDelIdx = param.getString("fileDelIdx").split(",");
		
		if(cnt > 0){
			
			for(CommonFile item : member.getCommonFileList()){
				//수정시 넘어온 파일이 있고 파일 idx가 있으면 기존파일 삭제
				if(!"".equals(item.getFileOrgName()) && item.getIdx() > 0){
					commonFileService.commonFileDel(item);
				}
			}
			
			for(int i = 0; i < fileDelIdx.length; i++){
				if(!"".equals(fileDelIdx[i])){
					commonFileService.commonFileIdxDel(Integer.parseInt(fileDelIdx[i]));
				}
			}
			
			commonFileService.commonFileAdd(member.getCommonFileList(), member.getIdx());
			
		}
		
		return cnt;
	}
	
	@Override
	public int delMember(int idx) throws Exception{
		return memberDao.delete(idx); 
	}
}
