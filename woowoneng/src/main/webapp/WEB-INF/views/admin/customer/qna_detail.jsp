<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("고객관리");
		$("#requestInfo").show();
		
	});

	//상태값 변경
	function updateProcType(procType, obj){
		
		$.post("updateCustomerRequestProcType.do",{idx:"${customerRequest.idx}", procType:procType}, function(jsonData){
			
			alert(jsonData.RETURN_MSG);

			if(jsonData.RETURN_CODE == "0000"){
				
				location.reload();
				
			}
			
		});
		
	}
	
	//리스트 이동
	function fnGoList(){
		
		location.href = "request_list.do?reqPage=${param.reqPage}&requestType=${param.requestType}";
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>고객관리</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="fnGoList();">목록</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
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
							<th scope="col" colspan="4">문의하기</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th scope="row">ㆍ성명<span class="imp">*</span></th>
							<td>${customerRequest.name}</td>
							<th scope="row">ㆍ상호명<span class="imp">*</span></th>
							<td>${customerRequest.companyName}</td>
						</tr>
						<tr>
							<th scope="row">ㆍ연락처<span class="imp">*</span></th>
							<td>${customerRequest.tel}</td>
							<th scope="row">ㆍ이메일<span class="imp">*</span></th>
							<td>${customerRequest.email}</td>
						</tr>
						<tr>
							<th scope="row">ㆍ주소</th>
							<td colspan="3">${customerRequest.addr}</td>
						</tr>
						<tr>
							<th scope="row">ㆍ내용<span class="imp">*</span></th>
							<td colspan="3"><textarea readonly="readonly" name="" rows="" cols="" style="width:583px; height:190px">${customerRequest.content}</textarea></td>
						</tr>
						<tr>
							<th scope="row">ㆍ등록일</th>
							<td>
								<fmt:parseDate var="regDate" value="${customerRequest.regDate}" pattern="yyyy-MM-dd"/>
								<fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd"/>
							</td>
							<th scope="row">ㆍ첨부파일</th>
							<td>
								<c:if test="${!empty customerRequest.commonFile}">
									<span class="file_img">
										<a href="/${filePath}/${customerRequest.commonFile.fileGroup}/${customerRequest.commonFile.fileSysName}" target="_blank">
											<img alt="첨부파일" src="/${filePath}/${customerRequest.commonFile.fileGroup}/${customerRequest.commonFile.fileSysName}">
										</a>
									</span>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- //table -->
			</div>

			<div class="btn_wrap cen line">
				<button class="btn_base ${customerRequest.procType eq '검토'?'yellow':'line'}" onClick="updateProcType('검토',this)">검토</button>
				<button class="btn_base ${customerRequest.procType eq '완료'?'yellow':'line'}" onClick="updateProcType('완료',this)">완료</button>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>

		</div>