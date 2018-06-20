<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("주문관리");
		
		$("#orderInfo").show();
		
		setChecked("procType", "${param.procType}", 0);
		$("[name='procType']").change(function(){
			location.href = "?procType=" + $(this).val();
		});
		
	});
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>주문관리</h2>
				<div class="al_right">
					<input type="radio" id="rdo_01" title="" name="procType" value="">
					<label for="rdo_01">전체</label>
					<input type="radio" id="rdo_02" title="" name="procType" value="신규주문">
					<label for="rdo_02">신규주문</label>
					<input type="radio" id="rdo_03" title="" name="procType" value="주문확인">
					<label for="rdo_03">주문확인</label>
					<input type="radio" id="rdo_04" title="" name="procType" value="납품완료">
					<label for="rdo_04">납품완료</label>
					<input type="radio" id="rdo_05" title="" name="procType" value="주문취소">
					<label for="rdo_05">주문취소</label>
				</div>
			</div>
			
			<div class="table_wrap">
				<!-- table -->
				<table border="1">
					<caption>주문관리 리스트</caption>
					<colgroup>
						<col style="width:35px;">
						<col style="width:25px;">
						<col style="width:85px;">
						<col style="width:125px;">
						<col style="width:55px;">
						<col style="width:100px;">
						<col style="width:100px;">
						<col style="width:180px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">NO</th>
							<th scope="col"></th>
							<th scope="col">발주일</th>
							<th scope="col">모델명</th>
							<th scope="col">주문수량</th>
							<th scope="col">상호</th>
							<th scope="col">담당자</th>
							<th scope="col">요청사항</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty ordersList}">
							<c:forEach items="${ordersList}" var="item" varStatus="vs">
							
								<tr>
									<td>${listStartNo-vs.index}</td>
									<td>
										<c:choose>
											<c:when test="${item.procType eq '신규주문'}">
												<span class="bul"></span>	
											</c:when>
											<c:when test="${item.procType eq '주문확인'}">
												<span class="bul black"></span>	
											</c:when>
											<c:when test="${item.procType eq '납품완료'}">
												<span class="bul yellow"></span>	
											</c:when>
											<c:when test="${item.procType eq '주문취소'}">
												<span class="bul red"></span>	
											</c:when>
										</c:choose>
									</td>
									<td>
										<fmt:parseDate var="regDate" value="${item.regDate}" pattern="yyyy-MM-dd"/>
										<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
									</td>
									<td><a href="order_detail.do?idx=${item.idx}&reqPage=${param.reqPage}&procType=${param.procType}">${item.product.prodName}</a></td>
									<td>${item.amount}</td>
									<td>${item.member.companyName}</td>
									<td>${item.tptb}</td>
									<td>${item.comment}</td>
								</tr>
							
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<!-- //table -->
			</div>

			<!-- page -->
			<div class="pager_wrap">
				<c:if test="${!empty ordersList && ordersList.size() > 0}">
					<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
					<button class="btn_base print" onClick="printOutPut();">프린트</button>
				</c:if>
			</div>
			<!-- //page -->

		</div>