<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
		<!-- LNB -->
		<div class="lnb">
			<ul>
				<li><a href="/admin/member/member_list.do">회원관리</a></li>
				<li><a href="/admin/order/order_list.do">주문관리</a></li>
				<li><a href="/admin/product/product_list.do">제품관리</a></li>
				<li><a href="/admin/customer/request_list.do">고객관리</a></li>
				<li class="site">
					<a href="#">회사소개</a>
					<ul>
						<li><a href="/admin/company/certify_list.do">인증현황</a></li>
						<li><a href="/admin/company/client_list.do">주요고객</a></li>
					</ul>
				</li>
				<li class="site">
					<a href="#">홍보센터</a>
					<ul>
						<li><a href="/admin/pr/exhibition_list.do">전시회</a></li>
						<li><a href="/admin/pr/press_list.do">언론보도</a></li>
						<li><a href="/admin/pr/ad_list.do">광고홍보</a></li>
						<li><a href="/admin/pr/product_case_list.do">납품사례</a></li>
					</ul>
				</li>
				<li class="site">
					<a href="#">고객센터</a>
					<ul>
						<li><a href="/admin/customer/notice_list.do">공지사항</a></li>
						<li><a href="/admin/customer/faq_list.do">FAQ</a></li>
					</ul>
				</li>
				<li><a href="/admin/category/category.do">카테고리관리</a></li>
			</ul>
			
			<ul class="order" id="orderInfo" style="display:none">
				<li><span class="bul"></span>신규주문</li>
				<li><span class="bul black"></span>주문확인</li>
				<li><span class="bul yellow"></span>납품완료</li>
				<li><span class="bul red"></span>주문취소</li>
			</ul>
			
			<ul class="order" id="requestInfo" style="display:none">
				<li><span class="bul"></span>신규</li>
				<li><span class="bul black"></span>검토</li>
				<li><span class="bul yellow"></span>완료</li>
			</ul>
		</div>
		<!-- //LNB -->