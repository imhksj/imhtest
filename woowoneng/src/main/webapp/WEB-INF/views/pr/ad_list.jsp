<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/prom.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("광고홍보");
		$("#prLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">광고홍보</h2>
		<p class="t_info">ADVERTISEMENT</p>
	</div>

	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산합니다.</p>

	<div class="prom_list ad">
		<ul>
			<c:if test="${!empty adList}">
				<c:forEach items="${adList}" var="item" varStatus="vs">
					<li>
						<a href="ad_detail.do?idx=${item.idx}&reqPage=${param.reqPage}">
							<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
							<span class="tit">${item.media}</span>
							<span class="t_info">${item.productionDate}</span>
							<span class="t_info">${item.note}</span>
						</a>
					</li>
				</c:forEach>
			</c:if>
		</ul>
	</div>

	<!-- page -->
	<div class="pager_wrap">
		<c:if test="${!empty adList && adList.size() > 0}">
			<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
		</c:if>
	</div>
	<!-- //page -->

</div>