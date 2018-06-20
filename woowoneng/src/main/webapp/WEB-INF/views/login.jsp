<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/login.css">

	<div class="content">
		<div class="login_top">
			<p>우원이엔지 웹사이트는 아이디를 부여받은<br />협력업체에서만 로그인이 가능합니다.</p>
		</div>

		<div class="login">
			<dl>
				<dt><label for="id">아이디</label></dt>
				<dd><input type="text" id="memberId" name="memberId" class="txt" title="아이디 입력란" placeholder="아이디를 입력해주세요" style="width:100%"></dd>
				<dt><label for="pass">비밀번호</label></dt>
				<dd><input type="password" id="memberPwd" name="memberPwd" class="txt" title="비밀번호 입력란" placeholder="비밀번호를 입력해주세요" style="width:100%"></dd>
			</dl>

			<div class="btn_wrap">
				<button type="button" class="btn_login" onClick="loginChk($(this).closest('.login'), 'MOBILE');">로그인</button>
			</div>

			<p class="t_info">아이디 및 비밀번호가 기억나지 않는다면?<br>우원이엔지 고객센터로 연락주시기 바랍니다.</p>
			<p class="tel"><span>고객센터 051-647-7182~3</span></p>
		</div>

	</div>
