<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("고객관리");
		$("#requestInfo").show();
		
		//수정시 카테고리3 셋팅
		setSelected($("#sel_01"), "${param.requestType}");
		
		setChecked("procType", "${param.procType}", 0);
		$("[name='procType']").change(function(){
			location.href = "?requestType=${param.requestType}&procType=" + $(this).val();
		});
		
	});
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>고객관리</h2>
				<div class="al_right">
					<input type="radio" id="rdo_01" title="" name="procType" value="">
					<label for="rdo_01">전체</label>
					<input type="radio" id="rdo_02" title="" name="procType" value="신규">
					<label for="rdo_02">신규</label>
					<input type="radio" id="rdo_03" title="" name="procType" value="검토">
					<label for="rdo_03">검토</label>
					<input type="radio" id="rdo_04" title="" name="procType" value="완료">
					<label for="rdo_04">완료</label>
					<span class="select" style="width:100px">
						<label for="sel_01">${!empty param.requestType?param.requestType:'전체'}</label>
						<select id="sel_01" name="requestType" style="" onChange="location.href='?requestType='+this.value">
							<option value="">전체</option>
							<option value="문의하기">문의하기</option>
							<option value="카탈로그">카탈로그</option>
							<option value="A/S요청">A/S요청</option>
						</select>
					</span>
				</div>
			</div>
			
			<div class="table_wrap">
				<!-- table -->
				<table border="1">
					<caption>고객관리 리스트</caption>
					<colgroup>
						<col style="width:35px;">
						<col style="width:30px;">
						<col style="width:85px;">
						<col style="width:390px;">
						<col style="width:70px;">
						<col style="width:95px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">NO</th>
							<th scope="col"></th>
							<th scope="col">카테고리</th>
							<th scope="col">연락처</th>
							<th scope="col">작성자</th>
							<th scope="col">등록일</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty requestList}">
							<c:forEach items="${requestList}" var="item" varStatus="vs">
							
								<tr>
									<td>${listStartNo-vs.index}</td>
									<td>
										<c:choose>
											<c:when test="${item.procType eq '신규'}">
												<span class="bul"></span>
											</c:when>
											<c:when test="${item.procType eq '검토'}">
												<span class="bul black"></span>
											</c:when>
											<c:when test="${item.procType eq '완료'}">
												<span class="bul yellow"></span>
											</c:when>
										</c:choose>
									</td>
									<td>${item.requestType}</td>
									<td class="al_left">
										<a href="request_detail.do?idx=${item.idx}&reqPage=${param.reqPage}&requestType=${param.requestType}">${item.tel}</a>
									</td>
									<td>${item.name}</td>
									<td>
										<fmt:parseDate var="regDate" value="${item.regDate}" pattern="yyyy-MM-dd"/>
										<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
									</td>
								</tr>
								
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<!-- //table -->
			</div>

			<!-- page -->
			<div class="pager_wrap">
				<c:if test="${!empty requestList && requestList.size() > 0}">
					<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
					<button class="btn_base print" onClick="printOutPut();">프린트</button>
				</c:if>
			</div>
			<!-- //page -->

		</div>