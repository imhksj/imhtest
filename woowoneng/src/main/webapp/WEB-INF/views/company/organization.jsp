<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/intro.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("조직도");
		$("#companyLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">조직도</h2>
		<p class="t_info">ORGANIZATION</p>
	</div>

	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산합니다.</p>

	<div class="organi_wrap">
		<span class="img pc"><img src="/images/content/img_organi_01.jpg" alt="조직도"></span>
		<span class="img mo"><img src="/images/content/img_organi_01_m.jpg" alt="조직도"></span>
	</div>

</div>