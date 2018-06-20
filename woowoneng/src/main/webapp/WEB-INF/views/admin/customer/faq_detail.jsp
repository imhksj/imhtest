<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("FAQ");
		
		//수정이면
		if("${!empty faq}" != "false")	$("#f").attr("action","modiFaq.do");

	});

	//ajax저장후 리턴 함수
	function saveAjaxReturn(jsonData){
		
		alert(jsonData.RETURN_MSG);
		
		if(jsonData.RETURN_CODE == "0000"){
			
			//수정이면
			if($("#idx").val() != "0"){
				location.reload();
			//신규등록이면
			}else{
				location.href = "faq_list.do";
			}
						
		}
		
	}
	
	//저장
	function addFaq(){
		
		if($("#idx").val() == "0"){
			if(!confirm("저장하시겠습니까?"))	return;	
		}else{
			if(!confirm("수정하시겠습니까?"))	return;
		}
		
		if(!nullChkAlert($("#name").val() ,"작성자는 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#question").val() ,"질문은 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#answer").val() ,"답변은 필수 입력 항목입니다."))	return;

		postFormAjax("f", saveAjaxReturn);
		
	}
	
	//삭제
	function delFaq(){
		
		if(!confirm("삭제하시겠습니까?"))	return;			
		
		$.get("delFaq.do?idx=${faq.idx}", function(jsonData){
			
			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				fnGoList();
			}
			
		});
		
	}
	
	//리스트 이동
	function fnGoList(){
		
		location.href = "faq_list.do?reqPage=${param.reqPage}";
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>자주묻는질문</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="fnGoList();">목록</button>
					<button type="button" class="btn_base" onClick="addFaq();">${!empty faq?'수정':'저장'}</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
				<form action="addFaq.do" method="post" name="f" id="f">
					<input type="hidden" name="idx" id="idx" value="${!empty faq?faq.idx:'0'}">
					<!-- table -->
					<table border="1">
						<caption>자주묻는 질문</caption>
						<colgroup>
							<col style="width:110px;">
							<col style="width:255px;">
							<col style="width:100px;">
							<col style="width:240px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="4">자주묻는 질문 ${!empty faq?'수정하기':'등록하기'}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">ㆍ작성자<span class="imp">*</span></th>
								<td colspan="3"><input type="text" value="${faq.name}" id="name" title="" name="name" style="width:575px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ질문<span class="imp">*</span></th>
								<td colspan="3"><textarea name="question" id="question" rows="" cols="" placeholder="질문 내용 입력" style="width:583px; height:100px">${faq.question}</textarea></td>
							</tr>
							<tr>
								<th scope="row">ㆍ답변<span class="imp">*</span></th>
								<td colspan="3"><textarea name="answer" id="answer" rows="" cols="" placeholder="답변 내용 입력" style="width:583px; height:370px">${faq.answer}</textarea></td>
							</tr>
						</tbody>
					</table>
					<!-- //table -->
				</form>
			</div>

			<div class="btn_wrap cen line">
				<c:if test="${!empty faq}">
					<button class="btn_base red" onClick="delFaq()">삭제</button>				
				</c:if>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>

		</div>