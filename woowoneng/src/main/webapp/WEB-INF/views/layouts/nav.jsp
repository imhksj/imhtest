<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<nav>
	<div class="nav_wrap">
		<ul>
			<li><a href="/company/company_introduce.do">회사소개</a>
				<ul>
					<li><a href="/company/company_introduce.do">회사개요</a></li>
					<li><a href="/company/ceo_introduce.do">대표 인사말</a></li>
					<li><a href="/company/history.do">연혁</a></li>
					<li><a href="/company/organization.do">조직도</a></li>
					<li><a href="/company/certificicate.do">인증현황</a></li>
					<li><a href="/company/client.do">주요고객</a></li>
					<li><a href="/company/location.do">오시는길</a></li>
				</ul>
			</li>
			<li ><a id="topNavBusinessLink" href="/business/industrial.do">사업부문</a>
				<ul>
					<li><a href="/business/industrial.do">산업용 가구 및 기계장비</a></li>
					<li><a href="/business/laboratory.do">실험실 가구 및 실험장비</a></li>
					<li><a href="/business/technology_lab.do">기술연구소</a></li>
				</ul>
			</li>
			<li><a href="/pr/exhibition_list.do">홍보센터</a>
				<ul>
					<li><a href="/pr/exhibition_list.do">전시회</a></li>
					<li><a href="/pr/press_list.do">언론보도</a></li>
					<li><a href="/pr/ad_list.do">광고홍보</a></li>
					<li><a href="/pr/product_case_list.do">납품사례</a></li>
				</ul>
			</li>
			<li><a href="/customer/notice_list.do">고객센터</a>
				<ul>
					<li><a href="/customer/notice_list.do">공지사항</a></li>
					<li><a href="/customer/faq.do">FAQ</a></li>
					<li><a href="/customer/qna_list.do">문의하기</a></li>
					<li><a href="/customer/as_list.do">A/S 요청</a></li>
					<li><a href="/customer/catalog_list.do">카탈로그</a></li>
				</ul>
			</li>
		</ul>
	</div>

	<div class="nav_bg"></div>
</nav>