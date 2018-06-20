<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title> WOOWONENG 관리자 </title>
	<meta name="keywords" content="WOOWONGENG">
	<meta name="description" content="WOOWONGENG">
	<link rel="apple-touch-icon" sizes="57x57" href="/images/favicon_admin/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="/images/favicon_admin/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="/images/favicon_admin/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="/images/favicon_admin/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="/images/favicon_admin/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="/images/favicon_admin/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="/images/favicon_admin/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="/images/favicon_admin/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="/images/favicon_admin/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="/images/favicon_admin/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="/images/favicon_admin/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="/images/favicon_admin/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/favicon_admin/favicon-16x16.png">
	<link rel="manifest" href="/images/favicon_admin/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/images/favicon_admin/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
	<link rel="stylesheet" type="text/css" href="/admin/css/common.css">
	<link rel="stylesheet" type="text/css" href="/admin/css/content.css">
	<!-- 공통js -->
	<script type="text/javascript" src="/js/libs/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="/js/libs/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/libs/jquery.form.js"></script>
	<script type="text/javascript" src="/js/libs/jquery.validate.js"></script>
	<script type="text/javascript" src="/js/libs/printThis.js"></script>
	<!-- //공통js -->
	
	<script type="text/javascript">

		$(document).ready(function(){
			
			/* select */
			var select = $("select");

			select.change(function(){
				var select_name = $(this).children("option:selected").text();
				$(this).siblings("label").text(select_name);
			});
			
			/* Admin */
			$('.lnb .site > a').click(function() {
				$(this).parent().toggleClass('on');
			});
			
		})
	
		//LNB 버튼 셋팅
		function lmbSet(lmbName){
			
			$(".lnb li a").each(function(){
				if($(this).html() == lmbName){
					$(this).closest("li").addClass("on");
					$(this).closest(".site").toggleClass('on');
					return false;
				}
			})
		}
		
		//로그아웃
		function logout(){
			
			$.get("/admin/logoutProc.do", function(jsonData){
				
				alert(jsonData.RETURN_MSG);
				
				if(jsonData.RETURN_CODE == "0000"){
					location.href = "/admin/login.do";
				}
				
			});
			
		}
		
	</script>
</head>

<body>
<div id="wrap">
	<!-- Skip Navigation -->
	<nav class="skip_nav">
		<a href="#container">본문바로가기</a>
	</nav>
	<!-- //Skip Navigation -->

	<!-- Header -->
	<tiles:insertAttribute name="header" />
	<!-- //Header -->

	<section id="container">
		<!-- LNB -->
		<tiles:insertAttribute name="lnb" />
		<!-- //LNB -->

		<!-- Contents -->
		<tiles:insertAttribute name="body" />
		<!-- //Contents -->
	</section>
	
	<!-- Footer -->
	<tiles:insertAttribute name="footer" />
	<!-- //Footer -->
</div>
</body>
</html>