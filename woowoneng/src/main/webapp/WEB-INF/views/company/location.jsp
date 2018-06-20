<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/intro.css">
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=rTj9FOhxYqHBWwZkl4eS"></script>
<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("오시는길");
		$("#companyLnb").show();
		
		/* Map */
		var map = new naver.maps.Map('map', {
			center: new naver.maps.LatLng(35.1596185, 128.8541227),
			zoom: 10
		});

		var marker = new naver.maps.Marker({
			position: new naver.maps.LatLng(35.1596185, 128.8541227),
			map: map
		});

		var map = new naver.maps.Map('map', mapOptions);
		
	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">오시는길</h2>
		<p class="t_info">LOCATION</p>
	</div>

	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지로 오시는 길을 안내드립니다.</p>

	<div class="map_wrap">
		<div id="map"></div>
	</div>

	<div class="table_wrap">
		<!-- table -->
		<table border="1">
			<caption>오시는길</caption>
			<colgroup>
				<col style="width:130px;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">회사주소</th>
					<td>부산광역시 강서구 미음동 미음산업단지 4로 119</td>
				</tr>
				<tr>
					<th scope="row">대표전화</th>
					<td>TEL : 051-647-7182~3</td>
				</tr>
				<tr>
					<th scope="row">팩스번호</th>
					<td>FAX : 051-647-7148</td>
				</tr>
			</tbody>
		</table>
		<!-- //table -->
	</div>

</div>