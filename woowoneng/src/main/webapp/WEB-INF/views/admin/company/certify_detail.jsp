<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("인증현황");
		
		//수정이면
		if("${!empty certify}" != "false")	$("#f").attr("action","modiCertify.do");
		
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
				location.href = "certify_list.do";
			}
						
		}
		
	}
	
	//저장
	function addCertify(){
		
		if($("#idx").val() == "0"){
			if(!confirm("저장하시겠습니까?"))	return;	
		}else{
			if(!confirm("수정하시겠습니까?"))	return;
		}
		
		if(!nullChkAlert($("#certifyName").val() ,"인증서명은 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#certifyNum").val() ,"인증번호는 필수 입력 항목입니다."))	return;

		if($(".file_img a img").length <= 0){
			alert("이미지는 필수 입력 항목입니다.");
			return;
		}

		postFormAjax("f", saveAjaxReturn);
		
	}
	
	//삭제
	function delCertify(){
		
		if(!confirm("삭제하시겠습니까?"))	return;			
		
		$.get("delCertify.do?idx=${certify.idx}", function(jsonData){
			
			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				location.href="certify_list.do";
			}
			
		});
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>인증현황</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="location.href='certify_list.do'">목록</button>
					<button type="button" class="btn_base" onClick="addCertify();">${!empty certify?'수정':'저장'}</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
				<form action="addCertify.do" method="post" name="f" id="f">
					<input type="hidden" name="idx" id="idx" value="${!empty certify?certify.idx:'0'}">
					<!-- table -->
					<table border="1">
						<caption>인증현황</caption>
						<colgroup>
							<col style="width:110px;">
							<col style="width:255px;">
							<col style="width:100px;">
							<col style="width:240px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="4">인증현황 ${!empty certify?'수정하기':'등록하기'}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">ㆍ인증서명<span class="imp">*</span></th>
								<td><input type="text" value="${certify.certifyName}" id="certifyName" title="" name="certifyName" style="width:220px"></td>
								<th scope="row">ㆍ인증번호<span class="imp">*</span></th>
								<td><input type="text" value="${certify.certifyNum}" id="certifyNum" title="" name="certifyNum" style="width:220px"></td>
							</tr>
							<tr>
								<th scope="row">ㆍ이미지<span class="imp">*</span></th>
								<td>
									<span class="b_file">
										<em class="txt">이미지 파일을 등록해 주세요</em>
										<img src="/admin/images/btn_file.gif" onclick="$(this).next().click();" alt="파일선택">
										<input class="contImg" type="file" name="file" id="file" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="COMPANY/CERTIFY">
										<input type="hidden" id="fileType" name="fileType" value="LIST_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="0">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty certify.commonFile?certify.commonFile.idx:'0'}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty certify.commonFile}">
												<a href="/${filePath}/${certify.commonFile.fileGroup}/${certify.commonFile.fileSysName}" target="_blank">
													<img alt="첨부파일" src="/${filePath}/${certify.commonFile.fileGroup}/${certify.commonFile.fileSysName}">
												</a>
											</c:when>
											<c:otherwise>
												<a href="#none"></a>
											</c:otherwise>											
										</c:choose>
									</span>
								</td>
								<th scope="row"></th>
								<td></td>
							</tr>
							
						</tbody>
					</table>
					<!-- //table -->
				</form>
			</div>

			<div class="btn_wrap cen line">
				<c:if test="${!empty certify}">
					<button class="btn_base red" onClick="delCertify()">삭제</button>				
				</c:if>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>

		</div>