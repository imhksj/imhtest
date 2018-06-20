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

			<!-- Push menu -->
			<ul class="menu">
				<c:if test="${!empty cateList}">
					<c:forEach items="${cateList}" var="item">
						<c:if test="${item.depthNum eq 2}">
							<li><a href="/product/product_list.do?category1=${item.topIdx}&category2=${item.idx}">${item.cName}</a>
								<ul>
									<c:forEach items="${cateList}" var="item2">
										<c:if test="${item2.depthNum eq 3 && item2.pidx eq item.idx}">
											<li><a href="/product/product_list.do?category1=${item.topIdx}&category2=${item.idx}&category3=${item2.idx}">${item2.cName}</a></li>
										</c:if>
									</c:forEach>
								</ul>
							</li>
						</c:if>
					</c:forEach>
				</c:if>
				<li><a href="#">기타</a>
					<ul>
						<li><a href="/etc/clean.do">클린사업</a></li>
						<li><a href="/customer/qna_form.do">채용문의</a></li>
						<li><a href="/etc/provide.do">정부조달사업</a></li>
						<li><a href="/customer/qna_form.do">협력업체 지원</a></li>
					</ul>
				</li>
			</ul>
			<!-- //Push menu -->

			<div class="menu_foot">
				<p class="tel"><a href="tel:051-647-7182">고객센터 051-647-7182</a></p>
				<p class="tel"><a href="tel:051-647-7183">고객센터 051-647-7183</a></p>
				<p class="logo"><span class="hide">WOOWONENG Logo</span></p>
				<p class="copy">COPYRIGHT 2017 (주)우원이엔지. ALL RIGHT RESERVED.</p>
			</div>
		</div>

	</div>
</div>