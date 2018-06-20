<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("공지사항");
		
		//수정시 카테고리3 셋팅
		setSelected($("#sel_01"), "${param.category}");
		$("#sel_01").prev().html($("#sel_01:selected").html());
		
	});
	
	//등록
	function fnGoWrite(){
		
		location.href = "notice_detail.do?reqPage=${reqPage}&category=" + $("#sel_01").val();
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>공지사항</h2>
				<div class="al_right">
					<span class="select" style="width:100px">
						<label for="sel_01">전체</label>
						<select id="sel_01" name="" style="" onChange="location.href='?category='+this.value">
							<option value="">전체</option>
							<c:forEach items="${cateList}" var="item">
								<option value="${item.idx}" pidx-data="${item.pidx}">${item.cName}</option>	
							</c:forEach>
						</select>
					</span>
					<button type="button" class="btn_base" onClick="fnGoWrite();">+ 등록</button>
				</div>
			</div>
			
			<div class="table_wrap">
				<!-- table -->
				<table border="1">
					<caption>게시판관리 리스트</caption>
					<colgroup>
						<col style="width:35px;">
						<col style="width:50px;">
						<col style="width:410px;">
						<col style="width:70px;">
						<col style="width:95px;">
						<col style="width:45px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">NO</th>
							<th scope="col">구분</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">등록일</th>
							<th scope="col">조회</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${!empty noticeList}">
							<c:forEach items="${noticeList}" var="item" varStatus="vs">
								<tr>
									<td>${listStartNo-vs.index}</td>
									<td>${item.categorys.cName}</td>
									<td class="al_left"><a href="notice_detail.do?idx=${item.idx}&reqPage=${param.reqPage}&category=${item.category}">${item.title}</a></td>
									<td>${item.name}</td>
									<td>
										<fmt:parseDate var="regDate" value="${item.regDate}" pattern="yyyy-MM-dd"/>
										<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
									</td>
									<td>${item.hit}</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
				<!-- //table -->
			</div>

			<!-- page -->
			<div class="pager_wrap">
				<c:if test="${!empty noticeList && noticeList.size() > 0}">
					<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
				</c:if>
			</div>
			<!-- //page -->

		</div>