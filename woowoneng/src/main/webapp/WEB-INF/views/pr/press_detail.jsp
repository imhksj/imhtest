<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/prom.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("언론보도");
		$("#prLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">언론보도</h2>
		<p class="t_info">PRESS</p>
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
					<th scope="row">기사제목</th>
					<td>${press.title}</td>
				</tr>
				<tr>
					<th scope="row">보도일</th>
					<td>${press.pressDate}</td>
				</tr>
				<tr>
					<th scope="row">언론사</th>
					<td>${press.pressName}</td>
				</tr>
				<tr>
					<th scope="row">조회</th>
					<td>${press.hit}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="detail_wrap">
							<c:if test="${!empty press.commonFileList}">
								<c:forEach items="${press.commonFileList}" var="item">
									<c:if test="${item.fileType ne 'LIST_IMG'}">
										<span class="img"><img alt="" src="/${filePath}/${item.fileGroup}/${item.fileSysName}"></span>
									</c:if>
								</c:forEach>
							</c:if>
							<p>${fn:replace(press.content,LF,'<br />')}</p>
							<c:if test="${!empty press.pressLink && press.pressLink ne ''}">
								<a href="${press.pressLink}" target="_blank" class="btn_go">기사 바로가기</a>							
							</c:if>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">이전글</th>
					<td>
						<c:choose>
							<c:when test="${!empty pressPrev}">
								<a href="?idx=${pressPrev.idx}&reqPage=${param.reqPage}">${pressPrev.title}</a>
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
							<c:when test="${!empty pressNext}">
								<a href="?idx=${pressNext.idx}&reqPage=${param.reqPage}">${pressNext.title}</a>
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
		<a href="press_list.do?reqPage=${param.reqPage}" class="btn_base">목록</a>
	</div>

</div>