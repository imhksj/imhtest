<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("주요고객");
		
		//수정이면
		if("${!empty client}" != "false")	$("#f").attr("action","modiClient.do");
		
	});
	
	//파일 타입 검증
	function fileTypeChk(fileObj){
		
		if(fileTypeCheck($(fileObj).val(), "image")){
			$(fileObj).siblings(".txt").html($(fileObj).val());	
		}else{
			$(fileObj).replaceWith($(fileObj).clone(true));
			$(fileObj).siblings(".txt").html("이미지를 등록해 주세요");
			alert("이미지 파일이 아닙니다.");
			return;
		}
		
		if (fileObj.files && fileObj.files[0]) {
			
	        var reader = new FileReader();

	        reader.onload = function (e) {
	            var imgHtml = '<img src="' + e.target.result + '" alt="">';
	            var imgView = $(fileObj).closest("span").next().find("a");
	            $(imgView).html(imgHtml);
	            $(imgView).attr("href", e.target.result);
	        }
	        reader.readAsDataURL(fileObj.files[0]);
	        
	    }
		
	}
	
	//ajax저장후 리턴 함수
	function saveAjaxReturn(jsonData){
		
		alert(jsonData.RETURN_MSG);
		
		if(jsonData.RETURN_CODE == "0000"){
			
			//수정이면
			if($("#idx").val() != "0"){
				location.reload();
			//신규등록이면
			}else{
				location.href = "client_list.do";
			}
						
		}
		
	}
	
	//저장
	function addClient(){
		
		if($("#idx").val() == "0"){
			if(!confirm("저장하시겠습니까?"))	return;	
		}else{
			if(!confirm("수정하시겠습니까?"))	return;
		}
		
		if(!nullChkAlert($("#clientName").val() ,"주요고객명은 필수 입력 항목입니다."))	return;

		if($(".file_img a img").length <= 0){
			alert("이미지는 필수 입력 항목입니다.");
			return;
		}

		postFormAjax("f", saveAjaxReturn);
		
	}
	
	//삭제
	function delClient(){
		
		if(!confirm("삭제하시겠습니까?"))	return;			
		
		$.get("delClient.do?idx=${client.idx}", function(jsonData){
			
			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				location.href="client_list.do";
			}
			
		});
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>주요고객</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="location.href='client_list.do'">목록</button>
					<button type="button" class="btn_base" onClick="addClient();">${!empty client?'수정':'저장'}</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
				<form action="addClient.do" method="post" name="f" id="f">
					<input type="hidden" name="idx" id="idx" value="${!empty client?client.idx:'0'}">
					<!-- table -->
					<table border="1">
						<caption>주요고객 수정</caption>
						<colgroup>
							<col style="width:110px;">
							<col style="width:255px;">
							<col style="width:100px;">
							<col style="width:240px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="4">주요고객 ${!empty client?'수정하기':'등록하기'}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">ㆍ주요고객명<span class="imp">*</span></th>
								<td><input type="text" value="${client.clientName}" id="clientName" title="" name="clientName" style="width:220px"></td>
								<th scope="row">ㆍ이미지<span class="imp">*</span></th>
								<td>
									<span class="b_file">
										<em class="txt">이미지 파일을 등록해 주세요</em>
										<img src="/admin/images/btn_file.gif" onclick="$(this).next().click();" alt="파일선택">
										<input class="contImg" type="file" name="file" id="file" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="COMPANY/CLIENT">
										<input type="hidden" id="fileType" name="fileType" value="LIST_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="0">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty client.commonFile?client.commonFile.idx:'0'}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty client.commonFile}">
												<a href="/${filePath}/${client.commonFile.fileGroup}/${client.commonFile.fileSysName}" target="_blank">
													<img alt="첨부파일" src="/${filePath}/${client.commonFile.fileGroup}/${client.commonFile.fileSysName}">
												</a>
											</c:when>
											<c:otherwise>
												<a href="#none"></a>
											</c:otherwise>											
										</c:choose>
									</span>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- //table -->
				</form>
			</div>

			<div class="btn_wrap cen line">
				<c:if test="${!empty client}">
					<button class="btn_base red" onClick="delClient()">삭제</button>				
				</c:if>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>

		</div>