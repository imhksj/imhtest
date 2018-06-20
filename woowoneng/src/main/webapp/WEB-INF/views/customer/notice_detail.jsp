<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/cust.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("공지사항");
		$("#customerLnb").show();

	});

</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">공지사항</h2>
		<p class="t_info">NOTICE</p>
	</div>
	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산합니다.</p>

	<div class="table_wrap">
		<!-- table -->
		<table border="1">
			<caption>공지사항</caption>
			<colgroup>
				<col style="width:130px;">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">제목</th>
					<td>${notice.title}</td>
				</tr>
				<tr>
					<th scope="row">등록일</th>
					<td>${notice.noticeDate}</td>
				</tr>
				<tr>
					<th scope="row">작성자</th>
					<td>${notice.name}</td>
				</tr>
				<tr>
					<th scope="row">조회</th>
					<td>${notice.hit}</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="detail_wrap">
							<c:if test="${!empty notice.commonFile}">
								<span class="img"><img alt="첨부파일" src="/${filePath}/${notice.commonFile.fileGroup}/${notice.commonFile.fileSysName}"></span>
							</c:if>
							<p>${fn:replace(notice.content,LF,'<br />')}</p>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row">이전글</th>
					<td>
						<c:choose>
							<c:when test="${!empty noticePrev}">
								<a href="?idx=${noticePrev.idx}&reqPage=${param.reqPage}">${noticePrev.title}</a>
							</c:when>
							<c:otherwise>
								이전글이 없습니다.							
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th scope="row">다음글</th>
					<td>
						<c:choose>
							<c:when test="${!empty noticeNext}">
								<a href="?idx=${noticeNext.idx}&reqPage=${param.reqPage}">${noticeNext.title}</a>
							</c:when>
							<c:otherwise>
								다음글이 없습니다.							
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
		<!-- //table -->
	</div>
	
	<div class="btn_wrap right">
		<a href="notice_list.do?reqPage=${param.reqPage}" class="btn_base">목록</a>
	</div>
	
</div>