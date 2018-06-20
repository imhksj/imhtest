<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<title> WOOWONENG </title>
	<meta name="keywords" content="WOOWONGENG">
	<meta name="description" content="WOOWONGENG">
	<link rel="apple-touch-icon" sizes="57x57" href="/images/favicon/apple-icon-57x57.png">
	<link rel="apple-touch-icon" sizes="60x60" href="/images/favicon/apple-icon-60x60.png">
	<link rel="apple-touch-icon" sizes="72x72" href="/images/favicon/apple-icon-72x72.png">
	<link rel="apple-touch-icon" sizes="76x76" href="/images/favicon/apple-icon-76x76.png">
	<link rel="apple-touch-icon" sizes="114x114" href="/images/favicon/apple-icon-114x114.png">
	<link rel="apple-touch-icon" sizes="120x120" href="/images/favicon/apple-icon-120x120.png">
	<link rel="apple-touch-icon" sizes="144x144" href="/images/favicon/apple-icon-144x144.png">
	<link rel="apple-touch-icon" sizes="152x152" href="/images/favicon/apple-icon-152x152.png">
	<link rel="apple-touch-icon" sizes="180x180" href="/images/favicon/apple-icon-180x180.png">
	<link rel="icon" type="image/png" sizes="192x192"  href="/images/favicon/android-icon-192x192.png">
	<link rel="icon" type="image/png" sizes="32x32" href="/images/favicon/favicon-32x32.png">
	<link rel="icon" type="image/png" sizes="96x96" href="/images/favicon/favicon-96x96.png">
	<link rel="icon" type="image/png" sizes="16x16" href="/images/favicon/favicon-16x16.png">
	<link rel="manifest" href="/images/favicon/manifest.json">
	<meta name="msapplication-TileColor" content="#ffffff">
	<meta name="msapplication-TileImage" content="/images/favicon/ms-icon-144x144.png">
	<meta name="theme-color" content="#ffffff">
	<link rel="stylesheet" type="text/css" href="/css/common.css">
	<!-- 공통js -->
	<script type="text/javascript" src="/js/libs/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="/js/libs/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/page_common.js"></script>
	<script type="text/javascript" src="/js/common_ui.js"></script>
	<script type="text/javascript" src="/js/libs/jquery.form.js"></script>
	<script type="text/javascript" src="/js/libs/jquery.validate.js"></script>
	<script type="text/javascript" src="/js/libs/printThis.js"></script>
	<script type="text/javascript" src="/js/libs/jquery.bxslider.js"></script>
	<!-- //공통js -->
</head>

<body>
<div id="wrap">
	
	<!-- Skip Navigation -->
	<nav class="skip_nav">
		<a href="#container">Skip to content</a>
	</nav>
	<!-- //Skip Navigation -->
	
	<!-- Header -->
	<section id="header">
		<tiles:insertAttribute name="top" />

		<tiles:insertAttribute name="nav" />
	</section>
	<!-- //Header -->

	<section id="container">
		<!-- lnb -->
		<tiles:insertAttribute name="lnb" />
		<!-- //lnb -->
		
		<!-- Contents -->
		<tiles:insertAttribute name="body" />
		<!-- //Contents -->
	</section>

	<!-- common Layer popup -->
	<tiles:insertAttribute name="common_layer_popup" />
	<!-- //Login Layer popup -->

	<!-- Footer -->
	<tiles:insertAttribute name="footer" />
	<!-- //Footer -->
	
	<div class="naver_talk">
		<a href="https://talk.naver.com/ct/wc5vdu" target="_blank">
			<img alt="네이버 톡 아이콘" src="/images/common/naver_talk_ico.png">
		</a>
	</div>
	
</div>

</body>
</html>