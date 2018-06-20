<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<nav class="pc">
	<div class="nav_wrap m09"> <!-- class m01 ~ m09 -->
		<ul>
			<c:if test="${!empty cateList}">
				<c:forEach items="${cateList}" var="item">
					<c:if test="${item.depthNum eq 2}">
						<li><a href="/product/product_list.do?category1=${item.topIdx}&category2=${item.idx}">${item.cName}</a>
							<ul>
								<c:forEach items="${cateList}" var="item2">
									<c:if test="${item2.depthNum eq 3 && item2.pidx eq item.idx}">
										<li><a href="/product/product_list.do?category1=${item.topIdx}&category2=${item.idx}&category3=${item2.idx}">${item2.cName}</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:if>
				</c:forEach>
			</c:if>
		</ul>
	</div>
</nav>

<nav class="mo">
	<div class="nav_wrap">
		<ul>
			<li><a href="/company/company_introduce.do">회사소개</a></li>
			<li><a href="/business/industrial.do">사업부문</a></li>
			<li><a href="/pr/exhibition_list.do">홍보센터</a></li>
			<li><a href="/customer/notice_list.do">고객센터</a></li>
		</ul>
	</div>
</nav>