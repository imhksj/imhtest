<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<div class="top_util">
	<ul>
		<li class="busi01"><a href="#">클린사업</a></li>
		<li class="busi02"><a href="#">정부조달사업</a></li>
		<li class="login">
			<c:choose>
				<c:when test="${empty sessionScope.member || sessionScope.member eq ''}">
					<a href="#" class="log">로그인</a>	
				</c:when>
				<c:otherwise>
					<a href="javascript:logout();">로그아웃</a>
				</c:otherwise>
			</c:choose>
		</li>
	</ul>
</div>

<div class="util_menu">
	<div class="menu_wrap">
		<h1><a href="/"><span class="hide">WOOWONENG</span></a></h1>
		<ul class="util_list">
			<li class="u01"><a href="#">문의하기</a></li>
			<li class="u02"><a href="#">A/S 요청</a></li>
			<li class="u03"><a href="#">카탈로그</a></li>
			<li class="search">
				<div class="search_wrap">
					<input type="text" id="" title="" name="" value="" />
					<button type="button" class="btn_search" onClick="fnGoSearchList(this);">확인</button>
				</div>
			</li>
		</ul>

		<button type="button" class="btn_menu"><span class="hide">Menu Open</span></button>
		<div class="menu_list">
			<button type="button" class="btn_closed"><span class="hide">Menu Closed</span></button>
			<div class="list_top">
				<c:choose>
					<c:when test="${empty sessionScope.member || sessionScope.member eq ''}">
						<p class="t_info">로그인해주세요.</p>
					</c:when>
					<c:otherwise>
						<p class="t_info">반갑습니다. <span>${sessionScope.companyName}</span> 파트너님</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="search_wrap">
				<input type="search" id="" title="" name="" value="" />
				<button type="button" class="btn_search" onClick="fnGoSearchList(this);">확인</button>
			</div>

			<ul class="util_list">
				<li class="u01"><a href="/customer/qna_form.do"><span>문의하기</span></a></li>
				<li class="u02"><a href="/customer/as_form.do"><span>A/S 요청</span></a></li>
				<li class="u03"><a href="/customer/catalog_form.do"><span>카탈로그</span></a></li>
				<c:choose>
					<c:when test="${empty sessionScope.member || sessionScope.member eq ''}">
						<li class="u04"><a href="/login.do"><span>로그인</span></a></li>	
					</c:when>
					<c:otherwise>
						<li class="u05"><a href="javascript:logout();"><span>로그아웃</span></a></li>
					</c:otherwise>
				</c:choose>
			</ul>

			<!-- menu -->
			<ul class="menu">
				<li><a href="#">회사소개</a>
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
				<li><a href="#">사업부문</a>
					<ul>
						<li><a href="/business/industrial.do">산업용 가구 및 기계장비</a></li>
						<li><a href="/business/laboratory.do">실험실 가구 및 실험장비</a></li>
						<li><a href="/business/technology_lab.do">기술연구소</a></li>
					</ul>
				</li>
				<li><a href="#">홍보센터</a>
					<ul>
						<li><a href="/pr/exhibition_list.do">전시회</a></li>
						<li><a href="/pr/press_list.do">언론보도</a></li>
						<li><a href="/pr/ad_list.do">광고홍보</a></li>
						<li><a href="/pr/product_case_list.do">납품사례</a></li>
					</ul>
				</li>
				<li><a href="#">고객센터</a>
					<ul>
						<li><a href="/customer/notice_list.do">공지사항</a></li>
						<li><a href="/customer/faq.do">FAQ</a></li>
						<li><a href="/customer/qna_list.do">문의하기</a></li>
						<li><a href="/customer/as_list.do">A/S 요청</a></li>
						<li><a href="/customer/catalog_list.do">카탈로그</a></li>
					</ul>
				</li>
				<li><a href="#">기타</a>
					<ul>
						<li><a href="/etc/clean.do">클린사업</a></li>
						<li><a href="/customer/qna_form.do">채용문의</a></li>
						<li><a href="/etc/provide.do">정부조달사업</a></li>
						<li><a href="/customer/qna_form.do">협력업체 지원</a></li>
					</ul>
				</li>
			</ul>
			<!-- //menu -->

			<div class="menu_foot">
				<p class="tel"><a href="tel:051-647-7182">고객센터 051-647-7182</a></p>
				<p class="tel"><a href="tel:051-647-7183">고객센터 051-647-7183</a></p>
				<p class="logo"><span class="hide">WOOWONENG Logo</span></p>
				<p class="copy">COPYRIGHT 2017 (주)우원이엔지. ALL RIGHT RESERVED.</p>
			</div>
		</div>

	</div>
</div>