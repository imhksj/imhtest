<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<div class="lnb" id="companyLnb" style="display:none">
	<ul>
		<li><a href="/company/company_introduce.do">회사개요</a></li>
		<li><a href="/company/ceo_introduce.do">대표 인사말</a></li>
		<li><a href="/company/history.do">연혁</a></li>
		<li><a href="/company/organization.do">조직도</a></li>
		<li><a href="/company/certificicate.do">인증현황</a></li>
		<li><a href="/company/client.do">주요고객</a></li>
		<li><a href="/company/location.do">오시는길</a></li>
	</ul>
</div>

<div class="lnb" id="businessLnb" style="display:none">
	<ul>
		<li><a href="/business/industrial.do">산업용가구 부문</a></li>
		<li><a href="/business/laboratory.do">실험실가구 부문</a></li>
		<li><a href="/business/technology_lab.do">기술연구소</a></li>
	</ul>
</div>

<div class="lnb" id="prLnb" style="display:none">
	<ul>
		<li><a href="/pr/exhibition_list.do">전시회</a></li>
		<li><a href="/pr/press_list.do">언론보도</a></li>
		<li><a href="/pr/ad_list.do">광고홍보</a></li>
		<li><a href="/pr/product_case_list.do">납품사례</a></li>
	</ul>
</div>

<div class="lnb" id="customerLnb" style="display:none">
	<ul>
		<li><a href="/customer/notice_list.do">공지사항</a></li>
		<li><a href="/customer/faq.do">FAQ</a></li>
		<li><a href="/customer/qna_list.do">문의하기</a></li>
		<li><a href="/customer/as_list.do">A/S 요청</a></li>
		<li><a href="/customer/catalog_list.do">카탈로그</a></li>
	</ul>

	<ul class="info" id="qnaInfo" style="display:none">
		<li><span class="bul"></span>신규문의</li>
		<li><span class="bul black"></span>내부검터</li>
		<li><span class="bul yellow"></span>답변완료</li>
	</ul>
	
	<ul class="info" id="asInfo" style="display:none">
		<li><span class="bul"></span>신규신청</li>
		<li><span class="bul black"></span>신청확인</li>
		<li><span class="bul yellow"></span>A/S 완료</li>
	</ul>
	
	<ul class="info" id="catalogInfo" style="display:none">
		<li><span class="bul"></span>신규신청</li>
		<li><span class="bul black"></span>신청확인</li>
		<li><span class="bul yellow"></span>발송완료</li>
	</ul>
</div>

<div class="lnb" id="policyLnb" style="display:none">
	<ul>
		<li><a href="/policy/service.do">이용약관</a></li>
		<li><a href="/policy/privacy.do">개인정보처리방침</a></li>
		<li><a href="/policy/email_reject_notice.do">이메일무단수집거부</a></li>
	</ul>
</div>