<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/main.css">

<script language="Javascript">
	
	window.onload = function(){
		var mailSlider = $('.slide_main').bxSlider({
							auto:true,
							pause:4000,
							autoControls:false,
							pager:false,
							controls: true
						});
		$(".bx-prev, .bx-next").click(function(){
			mailSlider.startAuto();
		})
	}
	
</script>

<div class="content">
	<div class="main_visual">
		<ul class="slide_main">
			<li><a href="/company/company_introduce.do"><img src="/images/content/img_main_slide01.jpg" alt=""></a></li>
			<li><a href="/business/industrial.do"><img src="/images/content/img_main_slide02.jpg" alt=""></a></li>
			<li><a href="/business/laboratory.do"><img src="/images/content/img_main_slide03.jpg" alt=""></a></li>
			<li><a href="/business/technology_lab.do"><img src="/images/content/img_main_slide04.jpg" alt=""></a></li>
		</ul>
	</div>

	<div class="board_wrap">
		<div class="list">
			<h2>전시회</h2>
			<div class="img">
				<ul>
					<c:if test="${!empty exhibitionList}">
						<c:forEach items="${exhibitionList}" var="item">
							<li>
								<a href="/pr/exhibition_detail.do?idx=${item.idx}">
									<img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}">
								</a>
							</li>
						</c:forEach>				
					</c:if>
				</ul>
			</div>
		</div>

		<div class="list">
			<h2>공지사항</h2>
			<ul>
				<c:if test="${!empty noticeList}">
					<c:forEach items="${noticeList}" var="item">
						<li>
							<a href="/customer/notice_detail.do?idx=${item.idx}">
								<span class="${item.categorys.cName eq '공지'?'notice':'news'}">${item.categorys.cName}</span>
								${item.title}
							</a>
						</li>
					</c:forEach>
				</c:if>
			</ul>
		</div>

		<div class="list">
			<h2>납품사례</h2>
			<div class="img">
				<ul>
					<c:if test="${!empty productCaseList}">
						<c:forEach items="${productCaseList}" var="item">
							<li>
								<a href="/pr/product_case_detail.do?idx=${item.idx}">
									<img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}">
								</a>
							</li>
						</c:forEach>				
					</c:if>
				</ul>
			</div>
		</div>

		<div class="list">
			<h2>언론보도자료</h2>
			<ul>
				<c:if test="${!empty pressList}">
					<c:forEach items="${pressList}" var="item">
						<li><a href="/pr/press_detail.do?idx=${item.idx}">· [${item.pressName}] ${item.title}</a></li>
					</c:forEach>
				</c:if>
			</ul>
		</div>
	</div>

</div>