<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("공지사항");
		
		//수정이면
		if("${!empty notice}" != "false")	$("#f").attr("action","modiNotice.do");
		
		//수정시 카테고리3 셋팅
		setSelected($("#sel_01"), "${notice.category}");
		$("#sel_01").trigger("change");
		
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
				location.href = "case_list.do";
			}
						
		}
		
	}
	
	//저장
	function addNotice(){
		
		if($("#idx").val() == "0"){
			if(!confirm("저장하시겠습니까?"))	return;	
		}else{
			if(!confirm("수정하시겠습니까?"))	return;
		}
		
		if(!nullChkAlert($("#title").val() ,"제목은 필수 입력 항목입니다."))	 return;
		if(!nullChkAlert($("#name").val() ,"작성자는 필수 입력 항목입니다.")) return;
		if(!nullChkAlert($("#sel_01").val() ,"구분을 선택해주세요."))	return;
		if(!nullChkAlert($("#content").val() ,"내용은 필수 입력 항목입니다.")) return;

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
	
	//삭제
	function delNotice(){
		
		if(!confirm("삭제하시겠습니까?"))	return;			
		
		$.get("delNotice.do?idx=${notice.idx}", function(jsonData){
			
			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				fnGoList();
			}
			
		});
		
	}
	
	//리스트 이동
	function fnGoList(){
		
		location.href = "notice_list.do?reqPage=${param.reqPage}&category=${param.category}";
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>공지사항</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="fnGoList();">목록</button>
					<button type="button" class="btn_base" onClick="addNotice();">${!empty notice?'수정':'저장'}</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
				<form action="addNotice.do" method="post" name="f" id="f">
					<input type="hidden" name="fileDelIdx" id="fileDelIdx" value="">
					<input type="hidden" name="idx" id="idx" value="${!empty notice?notice.idx:'0'}">
					<!-- table -->
					<table border="1">
						<caption>공지사항</caption>
						<colgroup>
							<col style="width:110px;">
							<col style="width:255px;">
							<col style="width:100px;">
							<col style="width:240px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="4">공지 / 소식 ${!empty notice?'수정하기':'등록하기'}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">ㆍ제목<span class="imp">*</span></th>
								<td colspan="3"><input type="text" value="${notice.title}" id="title" title="" name="title" style="width:575px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ작성자<span class="imp">*</span></th>
								<td><input type="text" value="${notice.name}" id="name" title="" name="name" style="width:220px" /></td>
								<th scope="row">ㆍ구분<span class="imp">*</span></th>
								<td>
									<span class="select" style="width:238px">
										<label for="sel_01">선택</label>
										<select id="sel_01" name="category" style="">
											<option value="">선택</option>
											<c:forEach items="${cateList}" var="item">
												<option value="${item.idx}" pidx-data="${item.pidx}">${item.cName}</option>	
											</c:forEach>
										</select>
									</span>
								</td>
							</tr>
							
							<tr>
								<th scope="row">ㆍ내용<span class="imp">*</span></th>
								<td colspan="3"><textarea name="content" id="content" placeholder="내용을 입력해주세요." rows="" cols="" style="width:583px; height:370px">${notice.content}</textarea></td>
							</tr>
							<tr>
								<th scope="row">ㆍ등록일</th>
								<td><input type="text" value="${notice.noticeDate}" id="noticeDate" title="" name="noticeDate" style="width:220px"></td>
								<th scope="row">ㆍ이미지 파일</th>
								<td>
									<span class="b_file">
										<em class="txt">이미지 파일을 등록해 주세요</em>
										<img src="/admin/images/btn_file.gif" onclick="$(this).next().click();" alt="파일선택">
										<input class="contImg" type="file" name="file" id="file" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="CUSTOMER/NOTICE">
										<input type="hidden" id="fileType" name="fileType" value="FILE">
										<input type="hidden" id="orderNum" name="orderNum" value="0">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty notice.commonFile?notice.commonFile.idx:'0'}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty notice.commonFile}">
												<a href="/${filePath}/${notice.commonFile.fileGroup}/${notice.commonFile.fileSysName}" target="_blank">
													<img alt="첨부파일" src="/${filePath}/${notice.commonFile.fileGroup}/${notice.commonFile.fileSysName}">
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
						</tbody>
					</table>
					<!-- //table -->
				</form>
			</div>

			<div class="btn_wrap cen line">
				<c:if test="${!empty notice}">
					<button class="btn_base red" onClick="delNotice()">삭제</button>				
				</c:if>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>

		</div>