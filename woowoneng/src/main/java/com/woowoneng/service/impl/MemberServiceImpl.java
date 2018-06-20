package com.woowoneng.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.woowoneng.common.service.AbstractGenericManager;
import com.woowoneng.dao.MemberDao;
import com.woowoneng.entity.Member;
import com.woowoneng.service.MemberService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : MemberServiceImpl.java
 * 기        능 : 회원 Service
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

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl extends AbstractGenericManager implements MemberService{

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Member getSelectMember(Member member) throws Exception{
		return memberDao.selectMember(member);
	}
	
}
