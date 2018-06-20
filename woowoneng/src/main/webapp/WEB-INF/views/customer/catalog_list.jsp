<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/cust.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("카탈로그");
		$("#customerLnb").show();
		$("#catalogInfo").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">카탈로그</h2>
		<p class="t_info">CATALOG / LEAFLET</p>
	</div>
	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산합니다.</p>

	<div class="table_top">
		<a href="catalog_form.do?reqPage=${param.reqPage}" class="btn_base">신청하기</a>
	</div>

	<div class="table_wrap list">
		<!-- table -->
		<table border="1">
			<caption>카탈로그</caption>
			<colgroup>
				<col class="w35" style="width:35px;">
				<col class="w25" style="width:25px;">
				<col class="w80" style="width:80px;">
				<col>
				<col class="w90" style="width:90px;">
				<col class="w100" style="width:100px;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col"></th>
					<th scope="col">카테고리</th>
					<th scope="col">상호명</th>
					<th scope="col">성명</th>
					<th scope="col" class="mo">등록일</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty catalogList}">
					<c:forEach items="${catalogList}" var="item" varStatus="vs">
						<tr>
							<td>${listStartNo-vs.index}</td>
							<td>
								<c:choose>
									<c:when test="${item.procType eq '신규'}">
										<span class="bul"></span>
									</c:when>
									<c:when test="${item.procType eq '검토'}">
										<span class="bul black"></span>
									</c:when>
									<c:when test="${item.procType eq '완료'}">
										<span class="bul yellow"></span>
									</c:when>
								</c:choose>
							</td>
							<td>카탈로그</td>
							<td class="al_left">${item.companyName}</td>
							<td>${item.name}</td>
							<td class="mo">
								<fmt:parseDate var="regDate" value="${item.regDate}" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<!-- //table -->
	</div>

	<!-- page -->
	<div class="pager_wrap">
		<c:if test="${!empty catalogList && catalogList.size() > 0}">
			<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
		</c:if>
	</div>
	<!-- //page -->


</div>