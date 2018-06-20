<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("인증현황");

	});

	
</script>

		<div class="content">
			<div class="h_area">
				<h2>인증현황</h2>
				<div class="al_right">
					<button type="button" class="btn_base" onClick="location.href='certify_detail.do'">등록</button>
				</div>
			</div>

			<!-- List -->
			<div class="site_list authen">
				<ul>
					<c:if test="${!empty certifyList}">
						<c:forEach items="${certifyList}" var="item" varStatus="vs">
							
							<li>
								<a href="certify_detail.do?idx=${item.idx}">
									<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
									<span class="tit">${item.certifyName}</span>
									<span class="t_info">${item.certifyNum}</span>
								</a>
							</li>
							
						</c:forEach>
					</c:if>
				</ul>
			</div>
			<!-- //List -->

		</div>