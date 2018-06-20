<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("회원관리");

		//수정이면
		if("${!empty member}" != "false")	$("#f").attr("action","modiMember.do");
		
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
	            $(imgView).after('<button type="button" onClick="delImg(this);"><span class="hide">삭제</span></button>');
	        }
	        reader.readAsDataURL(fileObj.files[0]);
	        
	    }
		
	}

	//이미지 삭제
	function delImg(obj){
		
		var fileObj = $(obj).closest("span").prev().find("input[type='file']");
		$(fileObj).replaceWith($(fileObj).clone(true));

		$(obj).closest("span").prev().find(".txt").html("이미지를 등록해 주세요");
		$(obj).closest("span").html('<a href="#none"></a>');
		
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
				location.href = "member_list.do";
			}
						
		}
		
	}


	//회원저장
	function addMember(){
		
		if($("#idx").val() == "0"){
			if(!confirm("저장하시겠습니까?"))	return;	
		}else{
			if(!confirm("수정하시겠습니까?"))	return;
		}
		
		if(!nullChkAlert($("#memberId").val() ,"아이디는 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#memberPwd").val() ,"비밀번호는 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#companyName").val() ,"상호는 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#region").val() ,"지역은 필수 입력 항목입니다."))	return;
		
		var fileDelIdx = "";
		
		//삭제된 파일이 있는지 검사
		$("input[type='file']").each(function(){

			if($(this).nextAll("#fileIdx").val() != "0" && $(this).closest("span").next().find("a img").length <= 0){
				fileDelIdx += "," +	$(this).nextAll("#fileIdx").val();		
			}
			
		});
		
		if(fileDelIdx != "")	$("#fileDelIdx").val(fileDelIdx.substr(1));
		
		postFormAjax("f", saveAjaxReturn);
		
	}
	
	//상품삭제
	function delMember(){
		
		if(!confirm("삭제하시겠습니까?"))	return;	
		
		$.get("delMember.do?idx=${member.idx}", function(jsonData){
			
			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				fnGoList();
			}
			
		});
		
	}
	
	//리스트 이동
	function fnGoList(){
		
		location.href = "member_list.do?reqPage=${param.reqPage}"
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>회원관리</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="fnGoList()">목록</button>
					<button type="button" class="btn_base" onClick="addMember()">${!empty member?'수정':'저장'}</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
				<form name="f" id="f" action="addMember.do" method="post">
					<input type="hidden" name="fileDelIdx" id="fileDelIdx" value="">
					<input type="hidden" name="idx" id="idx" value="${!empty member?member.idx:'0'}">
					<!-- table -->
					<table border="1">
						<caption>컨텐츠 관리 수정</caption>
						<colgroup>
							<col style="width:110px;">
							<col style="width:255px;">
							<col style="width:100px;">
							<col style="width:240px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="4">협력업체 ${!empty member?'수정하기':'등록하기'}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">ㆍ아이디<span class="imp">*</span></th>
								<td><input type="text" id="memberId" title="" name="memberId" style="width:220px" value="${member.memberId}" /></td>
								<th scope="row">ㆍ비밀번호<span class="imp">*</span></th>
								<td><input type="text" id="memberPwd" title="" name="memberPwd" style="width:220px" value="${member.memberPwd}" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ상호<span class="imp">*</span></th>
								<td><input type="text" id="companyName" title="" name="companyName" style="width:220px" value="${member.companyName}" /></td>
								<th scope="row">ㆍ지역<span class="imp">*</span></th>
								<td><input type="text" id="region" title="" name="region" style="width:220px" value="${member.region}" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ사업자등록번호</th>
								<td><input type="text" id="businessLicNum" title="" name="businessLicNum" style="width:220px" value="${member.businessLicNum}" /></td>
								<th scope="row">ㆍ이메일</th>
								<td><input type="text" id="email" title="" name="email" style="width:220px" value="${member.email}" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ전화번호<span class="imp">*</span></th>
								<td><input type="text" id="tel" title="" name="tel" style="width:220px" value="${member.tel}" /></td>
								<th scope="row">ㆍ팩스번호</th>
								<td><input type="text" id="fax" title="" name="fax" style="width:220px" value="${member.fax}" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ사업장 주소</th>
								<td colspan="3"><input type="text" id="companyAddr" title="" name="companyAddr" style="width:575px" value="${member.companyAddr}" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ사업자등록증</th>
								<td>
									<span class="b_file">
										<em class="txt">${!empty BUSINESS_LIC_IMG?BUSINESS_LIC_IMG.fileOrgName:'이미지를 등록해 주세요'}</em>
										<img src="/admin/images/btn_file.gif" onclick="document.getElementById('businessLicImg').click();" alt="파일선택">
										<input type="file" name="businessLicImg" id="businessLicImg" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="ADMIN/MEMBER">
										<input type="hidden" id="fileType" name="fileType" value="BUSINESS_LIC_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="0">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty BUSINESS_LIC_IMG?BUSINESS_LIC_IMG.idx:'0'}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty BUSINESS_LIC_IMG}">
												<a href="/${filePath}/${BUSINESS_LIC_IMG.fileGroup}/${BUSINESS_LIC_IMG.fileSysName}" target="_blank">
													<img alt="사업자등록증" src="/${filePath}/${BUSINESS_LIC_IMG.fileGroup}/${BUSINESS_LIC_IMG.fileSysName}">
												</a>
												<button type="button" onClick="delImg(this);"><span class="hide">삭제</span></button>
											</c:when>
											<c:otherwise>
												<a href="#none"></a>
											</c:otherwise>
										</c:choose>
									</span>
								</td>
								<th scope="row">ㆍ통장사본</th>
								<td>
									<span class="b_file">
										<em class="txt">${!empty BANKBOOK_COPY_IMG?BANKBOOK_COPY_IMG.fileOrgName:'이미지를 등록해 주세요'}</em>
										<img src="/admin/images/btn_file.gif" onclick="document.getElementById('bankbookCopyImg').click();" alt="파일선택">
										<input type="file" name="bankbookCopyImg" id="bankbookCopyImg" placeholder="ddd" onchange="fileTypeChk(this);">
										<input type="hidden" id="fileGroup" name="fileGroup" value="ADMIN/MEMBER">
										<input type="hidden" id="fileType" name="fileType" value="BANKBOOK_COPY_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="0">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty BANKBOOK_COPY_IMG?BANKBOOK_COPY_IMG.idx:'0'}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty BANKBOOK_COPY_IMG}">
												<a href="/${filePath}/${BANKBOOK_COPY_IMG.fileGroup}/${BANKBOOK_COPY_IMG.fileSysName}" target="_blank">
													<img alt="사업자등록증" src="/${filePath}/${BANKBOOK_COPY_IMG.fileGroup}/${BANKBOOK_COPY_IMG.fileSysName}">
												</a>
												<button type="button" onClick="delImg(this);"><span class="hide">삭제</span></button>
											</c:when>
											<c:otherwise>
												<a href="#none"></a>
											</c:otherwise>
										</c:choose>
									</span>
								</td>
							</tr>
							<tr>
								<th scope="row">ㆍ담당자 1</th>
								<td><input type="text" id="tptb1" title="" name="tptb1" style="width:220px" value="${member.tptb1}" /></td>
								<th scope="row">ㆍ휴대폰번호</th>
								<td><input type="text" id="tptb1Phone" title="" name="tptb1Phone" style="width:220px" value="${member.tptb1Phone}" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ담당자 2</th>
								<td><input type="text" id="tptb2" title="" name="tptb2" style="width:220px" value="${member.tptb2}" /></td>
								<th scope="row">ㆍ휴대폰번호</th>
								<td><input type="text" id="tptb2Phone" title="" name="tptb2Phone" style="width:220px" value="${member.tptb2Phone}" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ비고</th>
								<td colspan="3"><textarea placeholder="내용을 입력해주세요." name="note" id="note" rows="" cols="" style="width:583px; height:190px">${member.note}</textarea></td>
							</tr>
						</tbody>
					</table>
					<!-- //table -->
				</form>
			</div>

			<div class="btn_wrap cen line">
				<c:if test="${!empty member}">
					<button class="btn_base red" onClick="delMember()">삭제</button>				
				</c:if>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>
			
		</div>