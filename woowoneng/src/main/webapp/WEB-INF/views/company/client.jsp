<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/intro.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("주요고객");
		$("#companyLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">주요고객</h2>
		<p class="t_info">ORGANIZATION</p>
	</div>

	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구와 최고의 제품으로 성원에 보답하겠습니다.</p>

	<div class="major_wrap">
		<ul>
			<c:if test="${!empty clientList}">
				<c:forEach items="${clientList}" var="item" varStatus="vs">
					<li>
						<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
						<p class="t_txt">${item.clientName}</p>
					</li>
				</c:forEach>
			</c:if>
		</ul>

	</div>

</div>