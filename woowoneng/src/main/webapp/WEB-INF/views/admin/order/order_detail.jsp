<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	if("${empty order?true:false}" == "true"){
		alert("없는 데이타입니다.");
		history.back();
	}
	
	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("주문관리");
		
		$("#orderInfo").show();
		
		$("input[type='text']").prop("readonly", true);
		
		$("input[name='amount'], input[name='tptb'], input[name='deliverAddr']").prop("readonly", false);
		
	});

	//상태값 변경
	function updateProcType(procType, obj){
		
		$.post("updateProcType.do",{idx:"${order.idx}", procType:procType}, function(jsonData){
			
			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				
				$("button[name='procType']").attr("class","btn_base line");
				
				if($(obj).html() == "주문확인"){
					
					$(obj).addClass("black");
					
				}else if($(obj).html() == "주문취소"){
					
					$(obj).addClass("red");
					
				}else if($(obj).html() == "납품완료"){
					
					$(obj).addClass("yellow");
					
				}
				
			}
			
		});
		
	}
	
	//리스트 이동
	function fnGoList(){
		
		location.href = "order_list.do?reqPage=${param.reqPage}&procType=${param.procType}"
		
	}
	
	//ajax저장후 리턴 함수
	function saveAjaxReturn(jsonData){
		
		alert(jsonData.RETURN_MSG);
		
		if(jsonData.RETURN_CODE == "0000"){
			
			location.reload();
						
		}
		
	}
	
	//주문관리 수정
	function modiOrders(){

		if(!confirm("수정하시겠습니까?"))	return;

		if(!nullChkAlert($("#amount").val() ,"주문수량은 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#tptb").val() ,"담당자는 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#deliverAddr").val() ,"납품주소는 필수 입력 항목입니다."))	return;
		
		postFormAjax("f", saveAjaxReturn);
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>주문관리</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="fnGoList()">목록</button>
					<button type="button" class="btn_base" onClick="modiOrders()">수정</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
				<form action="modiOrder.do" method="post" id="f" name="f">
					<input type="hidden" name="idx" value="${order.idx}">
					<!-- table -->
					<table border="1">
						<caption>주문관리 수정</caption>
						<colgroup>
							<col style="width:110px;">
							<col style="width:255px;">
							<col style="width:100px;">
							<col style="width:240px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="4">주문관리 수정하기</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">ㆍ모델명<span class="imp">*</span></th>
								<td>${order.product.prodName}</td>
								<th scope="row">ㆍ주문수량<span class="imp">*</span></th>
								<td><input type="text" value="${order.amount}" id="amount" title="" name="amount" style="width:220px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ발주일</th>
								<td>${order.regDate}</td>
								<th scope="row">ㆍ당담자<span class="imp">*</span></th>
								<td><input type="text" value="${order.tptb}" id="tptb" title="" name="tptb" style="width:220px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ납품주소<span class="imp">*</span></th>
								<td colspan="3"><input value="${order.deliverAddr}" type="text" id="deliverAddr" title="" name="deliverAddr" style="width:575px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ제품이미지</th>
								<td><span class="prod_img"><img alt="" src="/${filePath}/${order.product.commonFile.fileGroup}/${order.product.commonFile.fileSysName}"></span></td>
								<th scope="row">ㆍ요청사항</th>
								<td><textarea name="comment" id="comment" rows="" cols="" style="width:228px; height:120px">${order.comment}</textarea></td>
							</tr>
							<tr>
								<td colspan="4" class="line"><span></span></td>
							</tr>
							<tr>
								<th scope="row">ㆍ상호</th>
								<td>${order.member.companyName}</td>
								<th scope="row">ㆍ사업자등록번호</th>
								<td>${order.member.businessLicNum}</td>
							</tr>
							<tr>
								<th scope="row">ㆍ전화번호</th>
								<td>${order.member.tel}</td>
								<th scope="row">ㆍ팩스번호</th>
								<td>${order.member.fax}</td>
							</tr>
							<tr>
								<th scope="row">ㆍ사업장주소</th>
								<td colspan="3">${order.member.companyAddr}</td>
							</tr>
							<tr>
								<th scope="row">ㆍ담당자 1</th>
								<td>${order.member.tptb1}</td>
								<th scope="row">ㆍ휴대폰번호</th>
								<td>${order.member.tptb1Phone}</td>
							</tr>
							<tr>
								<th scope="row">ㆍ담당자 2</th>
								<td>${order.member.tptb2}</td>
								<th scope="row">ㆍ휴대폰번호</th>
								<td>${order.member.tptb1Phone}</td>
							</tr>
						</tbody>
					</table>
					<!-- //table -->
				</form>
			</div>

			<div class="btn_wrap cen line">
				<button name="procType" class="btn_base ${order.procType eq '주문확인'?'black':'line'}" onClick="updateProcType('주문확인',this)">주문확인</button>
				<button name="procType" class="btn_base ${order.procType eq '주문취소'?'red':'line'}" onClick="updateProcType('주문취소',this)">주문취소</button>
				<button name="procType" class="btn_base ${order.procType eq '납품완료'?'yellow':'line'}" onClick="updateProcType('납품완료',this)">납품완료</button>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>

		</div>