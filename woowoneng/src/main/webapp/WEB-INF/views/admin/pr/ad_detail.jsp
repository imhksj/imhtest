<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("광고홍보");
		
		//수정이면
		if("${!empty ad}" != "false")	$("#f").attr("action","modiAd.do");
		
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
				location.href = "ad_list.do";
			}
						
		}
		
	}
	
	//저장
	function addAd(){
		
		if($("#idx").val() == "0"){
			if(!confirm("저장하시겠습니까?"))	return;	
		}else{
			if(!confirm("수정하시겠습니까?"))	return;
		}
		
		if(!nullChkAlert($("#media").val() ,"매체는 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#productionDate").val() ,"제작일은 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#note").val() ,"비고는 필수 입력 항목입니다."))	return;
		
		var cnt = 0;
		$(".contImg").each(function(index){
			
			$(this).attr("name", "fileList["+index+"]");
			
			//이미지 정렬순서 셋팅
			if($(this).closest("span").next().find("img").length > 0){
				$(this).nextAll("#orderNum").val(cnt++);
			}
			
		});
		
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
	function delAD(){
		
		if(!confirm("삭제하시겠습니까?"))	return;			
		
		$.get("delAd.do?idx=${ad.idx}", function(jsonData){
			
			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				fnGoList();
			}
			
		});
		
	}
	
	//리스트 이동
	function fnGoList(){
		
		location.href = "ad_list.do?reqPage=${param.reqPage}";
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>광고홍보</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="fnGoList();">목록</button>
					<button type="button" class="btn_base" onClick="addAd();">${!empty ad?'수정':'저장'}</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
				<form action="addAd.do" method="post" name="f" id="f">
					<input type="hidden" name="fileDelIdx" id="fileDelIdx" value="">
					<input type="hidden" name="idx" id="idx" value="${!empty ad?ad.idx:'0'}">
					<!-- table -->
					<table border="1">
						<caption>고객관리 관리 수정</caption>
						<colgroup>
							<col style="width:110px;">
							<col style="width:255px;">
							<col style="width:100px;">
							<col style="width:240px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="4">광고홍보 ${!empty ad?'수정하기':'등록하기'}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">ㆍ매체<span class="imp">*</span></th>
								<td colspan="3"><input type="text" value="${ad.media}" id="media" title="" name="media" style="width:575px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ제작일<span class="imp">*</span></th>
								<td colspan="3"><input type="text" value="${ad.productionDate}" id="productionDate" title="" name="productionDate" style="width:575px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ비고<span class="imp">*</span></th>
								<td colspan="3"><input type="text" value="${ad.note}" id="note" title="" name="note" style="width:575px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ내용</th>
								<td colspan="3"><textarea placeholder="내용입력" id="content" name="content" rows="" cols="" style="width:583px; height:190px">${ad.content}</textarea></td>
							</tr>
							<tr>
								<th scope="row">ㆍ썸네일이미지</th>
								<td>
									<span class="b_file">
										<em class="txt">이미지를 등록해 주세요</em>
										<img src="/admin/images/btn_file.gif" onclick="document.getElementById('listImg').click();" alt="파일선택">
										<input type="file" name="listImg" id="listImg" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="PR/AD">
										<input type="hidden" id="fileType" name="fileType" value="LIST_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="0">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty LIST_IMG?LIST_IMG.idx:0}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty LIST_IMG}">
												<a href="/${filePath}/${LIST_IMG.fileGroup}/${LIST_IMG.fileSysName}" target="_blank">
													<img alt="" src="/${filePath}/${LIST_IMG.fileGroup}/${LIST_IMG.fileSysName}">
												</a>
												<button type="button" onClick="delImg(this);"><span class="hide">삭제</span></button>
											</c:when>
											<c:otherwise>
												<a href="#none"></a>
											</c:otherwise>
										</c:choose>
									</span>
								</td>
								<th scope="row">ㆍ이미지</th>
								<td>
									<span class="b_file">
										<em class="txt">이미지를 등록해 주세요</em>
										<img src="/admin/images/btn_file.gif" onclick="$(this).next().click();" alt="파일선택">
										<input class="contImg" type="file" name="contImg" id="contImg" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="PR/AD">
										<input type="hidden" id="fileType" name="fileType" value="CONT_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="0">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty CONT_IMG0?CONT_IMG0.idx:0}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty CONT_IMG0}">
												<a href="/${filePath}/${CONT_IMG0.fileGroup}/${CONT_IMG0.fileSysName}" target="_blank">
													<img alt="" src="/${filePath}/${CONT_IMG0.fileGroup}/${CONT_IMG0.fileSysName}">
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
								<th scope="row"></th>
								<td></td>
								<th scope="row">ㆍ이미지</th>
								<td>
									<span class="b_file">
										<em class="txt">이미지를 등록해 주세요</em>
										<img src="/admin/images/btn_file.gif" onclick="$(this).next().click();" alt="파일선택">
										<input class="contImg" type="file" name="contImg" id="contImg" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="PR/AD">
										<input type="hidden" id="fileType" name="fileType" value="CONT_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="1">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty CONT_IMG1?CONT_IMG1.idx:0}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty CONT_IMG1}">
												<a href="/${filePath}/${CONT_IMG1.fileGroup}/${CONT_IMG1.fileSysName}" target="_blank">
													<img alt="" src="/${filePath}/${CONT_IMG1.fileGroup}/${CONT_IMG1.fileSysName}">
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
								<th scope="row"></th>
								<td></td>
								<th scope="row">ㆍ이미지</th>
								<td>
									<span class="b_file">
										<em class="txt">이미지를 등록해 주세요</em>
										<img src="/admin/images/btn_file.gif" onclick="$(this).next().click();" alt="파일선택">
										<input class="contImg" type="file" name="contImg" id="contImg" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="PR/AD">
										<input type="hidden" id="fileType" name="fileType" value="CONT_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="2">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty CONT_IMG2?CONT_IMG2.idx:0}">
									</span>
									<span class="file_img">
										<c:choose>
											<c:when test="${!empty CONT_IMG2}">
												<a href="/${filePath}/${CONT_IMG2.fileGroup}/${CONT_IMG2.fileSysName}" target="_blank">
													<img alt="" src="/${filePath}/${CONT_IMG2.fileGroup}/${CONT_IMG2.fileSysName}">
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
				<c:if test="${!empty ad}">
					<button class="btn_base red" onClick="delAd();">삭제</button>				
				</c:if>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>

		</div>