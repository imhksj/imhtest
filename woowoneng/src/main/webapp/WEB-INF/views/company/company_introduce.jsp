<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/intro.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("회사개요");
		$("#companyLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">회사개요</h2>
		<p class="t_info">COMPANY PROFILE</p>
	</div>

	<p class="cont_info"><span>The Best Technology, Best WOOWON ENG<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산하고 있습니다.</p>

	<span class="w100"><img src="/images/content/img_sum_01.jpg" alt="" ></span>

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
					<th scope="row">회사명</th>
					<td>주식회사 우원이엔지</td>
				</tr>
				<tr>
					<th scope="row">설립일</th>
					<td>2007년 12월 03일</td>
				</tr>
				<tr>
					<th scope="row">사업자등록번호</th>
					<td>606-86-06863</td>
				</tr>
				<tr>
					<th scope="row">대표자명</th>
					<td>이정율</td>
				</tr>
				<tr>
					<th scope="row">사업부문</th>
					<td>산업용가구, 실험실가구, 기타 구조용 금속재품 제조업</td>
				</tr>
				<tr>
					<th scope="row">사업장</th>
					<td>부산광역시 강서구 미음동 미음산업단지 4로 119&nbsp;&nbsp;&nbsp;TEL : 051-647-7182~3&nbsp;&nbsp;&nbsp;FAX : 051-647-7148</td>
				</tr>
			</tbody>
		</table>
		<!-- //table -->
	</div>

</div>