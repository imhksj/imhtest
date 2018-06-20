<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("회원관리");
		
	});
	
	//등록
	function fnGoWrite(){
		
		location.href = "member_detail.do?reqPage=${reqPage}";
		
	}

</script>

		<div class="content">
			<div class="h_area">
				<h2>회원관리</h2>
				<div class="al_right">
					<button onClick="fnGoWrite();" type="button" class="btn_base">+ 등록</button>
				</div>
			</div>
			
			<div class="table_wrap">
				<!-- table -->
				<table border="1">
					<caption>회원관리 리스트</caption>
					<colgroup>
						<col style="width:35px;">
						<col style="width:110px;">
						<col style="width:100px;">
						<col style="width:100px;">
						<col style="width:95px;">
						<col style="width:180px;">
						<col style="width:85px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">NO</th>
							<th scope="col">아이디</th>
							<th scope="col">지역</th>
							<th scope="col">상호</th>
							<th scope="col">전화번호</th>
							<th scope="col">이메일</th>
							<th scope="col">등록일</th>
						</tr>
					</thead>
					<tbody>
						
						<c:if test="${!empty memberList && memberList.size() > 0}">
							<c:forEach items="${memberList}" var="item" varStatus="vs">
								<tr>
									<td>${listStartNo-vs.index}</td>
									<td><a href="member_detail.do?idx=${item.idx}">${item.memberId}</a></td>
									<td>${item.region}</td>
									<td>${item.companyName}</td>
									<td>${item.tel}</td>
									<td>${item.email}</td>
									<td>
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
				<c:if test="${!empty memberList && memberList.size() > 0}">
					<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
					<button class="btn_base print" onClick="printOutPut();">프린트</button>
				</c:if>
			</div>
			<!-- //page -->

		</div>