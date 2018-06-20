<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("전시회");

	});

</script>

		<div class="content">
			<div class="h_area">
				<h2>전시회</h2>
				<div class="al_right">
					<button type="button" class="btn_base" onClick="location.href='exhibition_detail.do?reqPage=${param.reqPage}'">등록</button>
				</div>
			</div>

			<!-- List -->
			<div class="site_list">
				<ul>
					<c:if test="${!empty exhibitionList}">
						<c:forEach items="${exhibitionList}" var="item" varStatus="vs">
						
							<li>
								<a href="exhibition_detail.do?idx=${item.idx}&reqPage=${param.reqPage}">
									<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
									<span class="tit">${item.exhibitionName}</span>
									<span class="t_info">${item.exhibitionDate}</span>
									<span class="t_info">${item.region}</span>
								</a>
							</li>
						
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<!-- //List -->

			<!-- page -->
			<div class="pager_wrap">
				<c:if test="${!empty exhibitionList && exhibitionList.size() > 0}">
					<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
				</c:if>
			</div>
			<!-- //page -->

		</div>