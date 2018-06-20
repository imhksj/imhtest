<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<c:if test="${!empty productList}">
	<c:forEach items="${productList}" var="item" varStatus="vs">
		<li>
			<c:choose>
				<c:when test="${empty sessionScope.member || sessionScope.member eq ''}">
					<a href="javascript:;" onClick="alert('로그인 후 주문가능합니다.');">	
				</c:when>
				<c:otherwise>
					<a href="javascript:;" onClick="fnOrderLayerPopOpen(this, ${item.idx});">
				</c:otherwise>
			</c:choose>
				<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
				<div class="t_wrap">
					<p class="prod_n">${item.prodName}</p>
					<p>${item.prodSpec1}</p>
					<p>${item.prodSpec2}</p>
					<p>${item.prodSpec3}</p>
					<p class="prod_s">${item.prodSize}</p>
				</div>
			</a>
		</li>
	</c:forEach>					
</c:if>