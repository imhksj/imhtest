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
	<link rel="stylesheet" type="text/css" href="/admin/css/login.css">
	<!-- 공통js -->
	<script type="text/javascript" src="/js/libs/jquery-1.12.3.js"></script>
	<script type="text/javascript" src="/js/libs/jquery-ui.js"></script>
	<script type="text/javascript" src="/js/common.js"></script>
	<script type="text/javascript" src="/js/libs/jquery.form.js"></script>
	<script type="text/javascript" src="/js/libs/jquery.validate.js"></script>
	<!-- //공통js -->
</head>

<body>
<div id="wrap">
	<!-- Header -->
	<section id="header">
		<div class="header">
			<h1><a href="#"><span class="hide">WOOWONGENG Administrator Login</span></a></h1>
		</div>
	</section>
	<!-- //Header -->

	<!-- 로그인 -->
	<section id="container">
		<tiles:insertAttribute name="body" />
	</section>
	<!-- //로그인 -->

	<section id="footer">
		<div class="footer">
			<p>copyright ⓒ 2017. (주)우원이엔지. ALL RIGHT RESERVED.</p>
		</div>
	</section>

</div>

</body>
</html>