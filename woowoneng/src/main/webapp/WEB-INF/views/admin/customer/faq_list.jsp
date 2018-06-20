<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("FAQ");
		
	});

	//등록
	function fnGoWrite(){
		
		location.href = "faq_detail.do?reqPage=${reqPage}";
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>자주묻는 질문</h2>
				<div class="al_right">
					<button type="button" class="btn_base" onClick="fnGoWrite();">+ 등록</button>
				</div>
			</div>
			
			<div class="table_wrap">
				<!-- table -->
				<table border="1">
					<caption>게시판관리 리스트</caption>
					<colgroup>
						<col style="width:35px;">
						<col style="width:460px;">
						<col style="width:70px;">
						<col style="width:95px;">
						<col style="width:45px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">NO</th>
							<th scope="col">질문</th>
							<th scope="col">작성자</th>
							<th scope="col">등록일</th>
							<th scope="col">조회</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty faqList}">
							<c:forEach items="${faqList}" var="item" varStatus="vs">
								<tr>
									<td>${vs.index+1}</td>
									<td class="al_left"><a href="faq_detail.do?idx=${item.idx}&reqPage=${param.reqPage}">${item.question}</a></td>
									<td>${item.name}</td>
									<td>
										<fmt:parseDate var="regDate" value="${item.regDate}" pattern="yyyy-MM-dd"/>
										<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
									</td>
									<td>${item.hit}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<!-- //table -->
			</div>

			<!-- page -->
			<div class="pager_wrap">
				<c:if test="${!empty faqList && faqList.size() > 0}">
					<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
				</c:if>
			</div>
			<!-- //page -->

		</div>