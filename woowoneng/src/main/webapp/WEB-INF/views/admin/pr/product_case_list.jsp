<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("납품사례");
		
	});
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>납품사례</h2>
				<div class="al_right">
					<button type="button" class="btn_base" onClick="location.href='product_case_detail.do?reqPage=${param.reqPage}'">등록</button>
				</div>
			</div>

			<!-- List -->
			<div class="site_list">
				<ul>
					<c:if test="${!empty productCaseList}">
						<c:forEach items="${productCaseList}" var="item" varStatus="vs">
						
							<li>
								<a href="product_case_detail.do?idx=${item.idx}&reqPage=${param.reqPage}">
									<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
									<span class="tit">${item.vendor}</span>
									<span class="t_info">${item.deliverDate}</span>
								</a>
							</li>
						
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<!-- //List -->

			<!-- page -->
			<div class="pager_wrap">
				<c:if test="${!empty productCaseList && productCaseList.size() > 0}">
					<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
				</c:if>
			</div>
			<!-- //page -->

		</div>