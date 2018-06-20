<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/intro.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("인증현황");
		$("#companyLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">인증현황</h2>
		<p class="t_info">CERTIFICICATE</p>
	</div>

	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>(주)우원ENG는 ISO9001, ISO14001, 벤처기업, 이노비즈, 우수 중소기업 인증 등<br />끊임없는 연구와 개발, 그리고 서비스 개선을 통해 여러 기관에서 인정받고 있습니다.</p>

	<div class="certifi_wrap">
		<ul>
			<c:if test="${!empty certifyList}">
				<c:forEach items="${certifyList}" var="item" varStatus="vs">
					
					<li>
						<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
						<p class="t_txt">${item.certifyName}</p>
						<p class="info_txt">${item.certifyNum}</p>
					</li>
					
				</c:forEach>
			</c:if>
		</ul>
	</div>
</div>