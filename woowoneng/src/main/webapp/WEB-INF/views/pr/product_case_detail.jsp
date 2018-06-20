<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/prom.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("납품사례");
		$("#prLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">납품사례</h2>
		<p class="t_info">PORTFOLIO</p>
	</div>

	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산합니다.</p>

	<div class="table_wrap">
		<!-- table -->
		<table border="1">
			<caption>회사개요</caption>
			<colgroup>
				<col style="width:130px;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">납품처</th>
					<td>${productCase.vendor}</td>
				</tr>
				<tr>
					<th scope="row">납품일</th>
					<td>${productCase.deliverDate}</td>
				</tr>
				<tr>
					<th scope="row">조회</th>
					<td>${productCase.hit}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="detail_wrap">
							<c:if test="${!empty productCase.commonFileList}">
								<c:forEach items="${productCase.commonFileList}" var="item">
									<c:if test="${item.fileType ne 'LIST_IMG'}">
										<span class="img"><img alt="" src="/${filePath}/${item.fileGroup}/${item.fileSysName}"></span>
									</c:if>
								</c:forEach>
							</c:if>
							<p>${fn:replace(productCase.content,LF,'<br />')}</p>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">이전글</th>
					<td>
						<c:choose>
							<c:when test="${!empty productCasePrev}">
								<a href="?idx=${productCasePrev.idx}&reqPage=${param.reqPage}">${productCasePrev.vendor}</a>
							</c:when>
							<c:otherwise>
								이전글이 없습니다.							
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th scope="row">다음글</th>
					<td>
						<c:choose>
							<c:when test="${!empty productCaseNext}">
								<a href="?idx=${productCaseNext.idx}&reqPage=${param.reqPage}">${productCaseNext.vendor}</a>
							</c:when>
							<c:otherwise>
								다음글이 없습니다.							
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //table -->
	</div>
	
	<div class="btn_wrap right">
		<a href="product_case_list.do?reqPage=${param.reqPage}" class="btn_base">목록</a>
	</div>
	
</div>