<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/cust.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("공지사항");
		$("#customerLnb").show();
		
		//수정시 카테고리3 셋팅
		setSelected($("[name='noticeSelect']"), "${param.category}");
		$("[name='noticeSelect']").prev().html($("[name='noticeSelect']").find(":selected").html());

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">공지사항</h2>
		<p class="t_info">NOTICE</p>
	</div>
	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산합니다.</p>

	<div class="table_top">
		<span class="select" style="width:130px">
			<label for="sel_01">전체</label>
			<select id="sel_01" name="noticeSelect" style="" onChange="location.href='?category='+this.value">
				<option value="">전체</option>
				<c:forEach items="${cateList}" var="item">
					<option value="${item.idx}">${item.cName}</option>	
				</c:forEach>
			</select>
		</span>
	</div>

	<div class="table_wrap list">
		<!-- table -->
		<table border="1">
			<caption>회사개요</caption>
			<colgroup>
				<col class="w40" style="width:40px;">
				<col class="w60" style="width:60px;">
				<col>
				<col class="w80" style="width:80px;">
				<col class="w100" style="width:100px;">
				<col class="w50" style="width:50px;">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col">구분</th>
					<th scope="col">제목</th>
					<th scope="col" class="mo">작성자</th>
					<th scope="col">등록일</th>
					<th scope="col" class="mo">조회</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty noticeList}">
					<c:forEach items="${noticeList}" var="item" varStatus="vs">
						<tr>
							<td>${listStartNo-vs.index}</td>
							<td>${item.categorys.cName}</td>
							<td class="al_left"><a href="notice_detail.do?idx=${item.idx}&reqPage=${param.reqPage}&category=${item.category}">${item.title}</a></td>
							<td class="mo">${item.name}</td>
							<td>
								<fmt:parseDate var="regDate" value="${item.regDate}" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
							</td>
							<td class="mo">${item.hit}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<!-- //table -->
	</div>

	<!-- page -->
	<div class="pager_wrap">
		<c:if test="${!empty pressList && pressList.size() > 0}">
			<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
		</c:if>
	</div>
	<!-- //page -->

</div>