<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/cust.css">

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("카탈로그");
		$("#customerLnb").show();
		$("#catalogInfo").show();

	});

	//파일 타입 검증
	function fileTypeChk(fileObj){
		
		if(fileTypeCheck($(fileObj).val(), "image")){
			$(fileObj).prev().find("span").html($(fileObj).val() + '<button type="button" onClick="delFile(this)"><span class="hide">삭제</span></button>');	
		}else{
			$(fileObj).replaceWith($(fileObj).clone(true));
			alert("이미지 파일이 아닙니다.");
			return;
		}
		
	}
	
	//파일 삭제
	function delFile(obj){
		
		var fileObj = $(obj).closest("div").next();
		$(obj).closest("span").html("이미지파일을 등록해주세요.");
		$(fileObj).replaceWith($(fileObj).clone(true));
		
	}

	//ajax저장후 리턴 함수
	function saveQnaAjaxReturn(jsonData, formObj){
		
		alert(jsonData.RETURN_MSG);
		
		if(jsonData.RETURN_CODE == "0000"){
			location.href = "catalog_list.do";
		}
		
	}

	//문의하기 전송
	function addCatalog(){
		
		if(!confirm("저장하시겠습니까?"))	return;	
		
		var formObj = $("#catalogForm"); 
		
		if(!nullChkAlert($("#catalogForm #name").val() ,"성명은 필수 입력 항목입니다."))	 return;
		if(!nullChkAlert($("#catalogForm #tel").val() ,"연락처는 필수 입력 항목입니다."))	 return;
		
		if($(formObj).find("#email1").val() != "" && $(formObj).find("#email2").val() != ""){
			$(formObj).find("#email").val($(formObj).find("#email1").val() + "@" + $(formObj).find("#email2").val());
		}else{
			$(formObj).find("#email").val("");
		}

		if($("#catalogForm #check01:checked").length < 1){
			alert("개인정보 수집 및 이용에 동의를 해야합니다.");
			$("#catalogForm #privacyYn").val("N");
			return;
		}else{
			$("#catalogForm #privacyYn").val("Y");
		}
		
		postFormAjax2("catalogForm", saveQnaAjaxReturn);
		
	}
	
</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">카탈로그</h2>
		<p class="t_info">CATALOG / LEAFLET</p>
	</div>
	<p class="cont_info"><span>New Technologies Make New Values<em>!</em></span>우원이엔지는 끊임없는 연구를 바탕으로 새로운 가치를 생산합니다.</p>

	<div class="table_top">
		<a href="catalog_list.do?reqPage=${param.reqPage}" class="btn_base">목록</a>
	</div>

	<div class="table_wrap detail">
		<form name="catalogForm" id="catalogForm" action="/customer/addCatalog.do" method="post">
			<input type="hidden" name="requestType" id="requestType" value="카탈로그">
			<input type="hidden" name="privacyYn" id="privacyYn" value="Y">
			<input type="hidden" name="email" id="email" value="">
			<input type="hidden" name="idx" id="idx" value="0">
			<input type="hidden" name="catalogName" id="catalogName" value="">
			<!-- table -->
			<table border="1">
				<caption>카탈로그</caption>
				<colgroup>
					<col class="t01" style="width:110px;">
					<col>
				</colgroup>
				<thead>
					<tr>
						<th scope="col" colspan="2">카탈로그 신청</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">• 성명<span class="imp">*</span></th>
						<td><input type="text" value="" id="name" title="" name="name" style="width:565px"></td>
					</tr>
					<tr>
						<th scope="row">• 상호명</th>
						<td><input type="text" value="" id="companyName" title="" name="companyName" style="width:565px"></td>
					</tr>
					<tr>
						<th scope="row">• 연락처<span class="imp">*</span></th>
						<td><input type="text" value="" id="tel" title="" name="tel" style="width:565px"></td>
					</tr>
					<tr>
						<th scope="row">• 이메일</th>
						<td>
							<div class="mail_wrap">
								<input type="text" value="" id="email1" title="" name="email1" style="width:263px">
								<span class="fmr">@</span>
								<input type="text" value="" id="email2" title="" name="email2" style="width:263px">
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">• 주소</th>
						<td><input type="text" value="" id="addr" title="" name="addr" style="width:565px"></td>
					</tr>
					<tr>
						<th scope="row">• 카탈로그</th>
						<td>
							<span class="select" style="width:583px">
								<label for="sel_02">클린사업장 지원품목 카탈로그</label>
								<select id="sel_02" name="catalog" onChange="$('#catalogName').val($(this).find('option:selected').html());">
									<option value="0">카탈로그 선택</option>
									<c:if test="${!empty catalogCateList}">
										<c:forEach items="${catalogCateList}" var="item">
											<option value="${item.idx}">${item.cName}</option>	
										</c:forEach>
									</c:if>
								</select>
							</span>
						</td>
					</tr>
					<tr>
						<th scope="row">• 내용</th>
						<td><textarea id="content" name="content" rows="" cols="" placeholder="내용입력" style="width:563px; height:104px"></textarea></td>
					</tr>
					<tr>
						<th scope="row">• 첨부파일</th>
						<td>
							<div class="b_file">
								<a href="javascript:" class="btn_file" onclick="$(this).nextAll('#file').click();">파일선택</a>
								<div class="txt">
									<span>이미지파일을 등록해주세요.</span>
								</div>
								<input type="file" name="file" id="file" placeholder="ddd" onchange="fileTypeChk(this)">
								<input type="hidden" id="fileGroup" name="fileGroup" value="CUSTOMER/CUSTOMER_REQUEST">
								<input type="hidden" id="fileType" name="fileType" value="FILE">
								<input type="hidden" id="orderNum" name="orderNum" value="0">
								<input type="hidden" id="fileIdx" name="fileIdx" value="0">
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">· 개인정보</th>
						<td>
							<div class="box_info">
								<p>개인정보 항목 : 성명, 상호명, 연락처, 주소<br>수집 및 이용목적 : 제품안내 및 정보제공, 고객상담<br>보유 및 이용기간 : 개인정보 수집일로부터 개인정보 파기 요청시 까지</p>
							</div>
							<span class="chk"><input type="checkbox" id="check01" title="" name=""><label for="check01">개인정보 수집 및 이용에 동의합니다.</label></span>
						</td>
					</tr>
				</tbody>
			</table>
			<!-- //table -->
		</form>
	</div>

	<div class="btn_wrap cen">
		<a href="javascript:addCatalog();" class="btn_base">저장</a>
	</div>

</div>