<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

				<c:forEach items="${param}" var="item">
					<c:if test="${item.key ne 'reqPage'}">
						<c:set var="paramValue" value="${paramValue}${item.key}=${item.value}&" />
					</c:if>
				</c:forEach>

				<c:choose>
					<c:when test="${prevGrpPageNo==currentPage}">
						<a href="javascript:return false;" class="btn_page prev">
					</c:when>
					<c:otherwise>
						<a href="?${paramValue}reqPage=${prevGrpPageNo}" class="btn_page prev">
					</c:otherwise>
				</c:choose>
					<span class="hide">이전 페이지</span>
				</a>

				<span class="page">
					<c:forEach var="boundaryStart" begin="${startGrpPageNo}" end="${endGrpPageNo}" step="1" varStatus="status">
						<c:choose>
							<c:when test="${status.current==currentPage}">
								<a href="javascript:return false;" class="on">${status.current}</a>
							</c:when>
							<c:otherwise>
								<a href="?${paramValue}reqPage=${status.current}">${status.current}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</span>
				
				<c:choose>
					<c:when test="${nextGrpPageNo==currentPage}">
						<a href="javascript:return false;" class="btn_page next">
					</c:when>
					<c:otherwise>
						<a href="?${paramValue}reqPage=${nextGrpPageNo}" class="btn_page next">
					</c:otherwise>
				</c:choose>
					<span class="hide">다음 페이지</span>
				</a>