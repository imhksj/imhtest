<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/cust.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("FAQ");
		$("#customerLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">자주묻는질문</h2>
		<p class="t_info">QUESTION &amp; ANSWER</p>
	</div>
	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산합니다.</p>

	<div class="faq_list">
		<ul>
			<c:forEach items="${faqList}" var="item" varStatus="vs">
				<li>
					<a href="javascript:;" id="faqIdx_${item.idx}"><span>${vs.index+1}. QUESTION</span>${item.question}</a>
					<div class="answ">
						<p><span>ANSWER</span>${fn:replace(item.answer,LF,'<br />')}</p>
					</div>
				</li>
			</c:forEach>
		</ul>
	</div>

</div>