<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//로그인 버튼
		$(".btn_login").click(function(){
			loginChk();
		});
		
	});

	function loginChk(){
		
		$.post($("#f").attr("action"), $("#f").serialize(), function(jsonData){

			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				location.href = "/admin/member/member_list.do";
			}
			
		}, "json");
		
	}

</script>

	
		<div class="content">
			<!-- 로그인 -->
			<div class="login_wrap">
				<div class="login">
					<form id="f" action="loginProc.do" method="post">
						<dl>
							<dt><label for="id">관리자 아이디</label></dt>
							<dd><input type="text" name="adminId" id="adminId" class="txt" title="아이디 입력란" placeholder="아이디를 입력해주세요" style="width:228px"></dd>
							<dt><label for="pass">관리자 비밀번호</label></dt>
							<dd><input type="password" name="adminPassword" id="pass" class="txt" title="비밀번호 입력란" placeholder="비밀번호를 입력해주세요" style="width:228px"></dd>
						</dl>
	
						<div class="btn_wrap">
							<button type="button" class="btn_login">로그인</button>
						</div>
					</form>
				</div>
			</div>
			<!-- //로그인 -->
		</div>