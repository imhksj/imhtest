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

	<div class="prom_list">
		<ul>
			<c:if test="${!empty pressList}">
				<c:forEach items="${pressList}" var="item" varStatus="vs">
					<li>
						<a href="press_detail.do?idx=${item.idx}&reqPage=${reqPage}">
							<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
							<span class="tit">${item.title}</span>
							<span class="t_info">${item.pressDate}</span>
							<span class="t_info">${item.pressName}</span>
						</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>

	<!-- page -->
	<div class="pager_wrap">
		<c:if test="${!empty pressList && pressList.size() > 0}">
			<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
		</c:if>
	</div>
	<!-- //page -->

</div>