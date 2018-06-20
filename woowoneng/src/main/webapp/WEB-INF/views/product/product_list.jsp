<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/css/prod.css">
<script type="text/javascript">

	var totalPage = ${totalPage};
	var totalCount = ${totalCount};
	var reqPage = 2;
	var category1 = "${param.category1}";
	var category2 = "${param.category2}";
	var category3 = "${param.category3}";
	var prodChoicSpec = "${param.prodChoicSpec}";

	$(document).ready(function(){
		
		//주문수량 입력폼 숫자만 입력되고 그외 문자는 삭제 된다.
		$("#amount").keyup(function(){
			if(!$.isNumeric($(this).val())){
				alert("수량은 숫자만 입력이 가능합니다.");
			}
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		})
		
		if(totalCount <= 0 || totalPage < 2)	$("#loadListBtn").hide();
		
		$("input[name='prodChoicSpec']").change(function(){
			location.href = "?category1=${param.category1}&prodChoicSpec=" + $(this).val();;
		});
		
		setChecked("prodChoicSpec", "${param.prodChoicSpec}", 0);
		
	});
	
	//상품정보 로드
	function fnLoadList(){
		
		if(totalPage >= reqPage){
			
			$.get( "productListAjx.do", {reqPage:reqPage, category1:category1, category2:category2, category3:category3, prodChoicSpec:prodChoicSpec}, function(htmlData){
				
				$(".prod_list ul li:last").after(htmlData);
				
				reqPage++;
			  
			});
			
		}
		
		if(totalPage == reqPage)	$("#loadListBtn").hide();
		
	}
	
	//주문하기 레이어팝업 오픈
	function fnOrderLayerPopOpen(obj, productIdx){
		
		$('#order.layer_pop').css('display', 'block');
		$('.layer_dim').addClass('on');
		
		var prodImg = $(obj).find(".img");
		var prodInfo = $(obj).find(".t_wrap");
		
		$(".prod_wrap .img").html(prodImg.html());
		$(".prod_wrap .t_wrap p").remove();
		$(".prod_wrap .t_wrap button").before(prodInfo.html());
		$("#productIdx").val(productIdx);
	}
	
	//ajax저장후 리턴 함수
	function saveProdAjaxReturn(jsonData){
		
		alert(jsonData.RETURN_MSG);
		
		if(jsonData.RETURN_CODE == "0000"){
			
			$("#order .btn_closed").click();
			
			$("#orderForm input").val("");
			$("#orderForm textarea").val("");
			
		}
		
	}
	
	//주문하기
	function addOrder(){
		
		if(!confirm("주문하시겠습니까?"))	return;
		
		if(!nullChkAlert($("#amount").val() ,"주문수량은 필수 입력 항목입니다."))	 return;
		if(!nullChkAlert($("#tptb").val() ,"담당자는 필수 입력 항목입니다.")) return;
		if(!nullChkAlert($("#deliverAddr").val() ,"배송주소는 필수 입력 항목입니다."))	return;
		if(productIdx == "0")	alert("정보가 잘못되었습니다.");
		$("#orderForm #idx").val(0);
		$("#productName").val($("#orderForm .prod_n").val());
		
		postFormAjax("orderForm", saveProdAjaxReturn);
		
	}
	
</script>

<div class="content">
	<div class="top_area">
		<h2 class="t_tit">${cateName}</h2>
		<p class="t_info">${categoryLocation}</p>

		<div class="fl_right">
			<span class="rdo"><input type="radio" id="rdo_01" checked="checked" title="" value="" name="prodChoicSpec"> <label for="rdo_01">전체제품</label></span>
			<c:choose>
				<c:when test="${param.category1 eq '113'}">
					<span class="rdo"><input type="radio" id="rdo_02" title="" value="클린사업장 지원제품" name="prodChoicSpec"> <label for="rdo_02">클린사업장 지원제품</label></span>			
				</c:when>
				<c:when test="${param.category1 eq '115'}">
					<span class="rdo"><input type="radio" id="rdo_02" title="" value="정부조달사업 등록제품" name="prodChoicSpec"> <label for="rdo_02">정부조달사업 등록제품</label></span>			
				</c:when>
			</c:choose>
		</div>
	</div>

	<div class="prod_list">
		<ul>
			<c:if test="${!empty productList}">
				<c:forEach items="${productList}" var="item" varStatus="vs">
					<li>
						<c:choose>
							<c:when test="${empty sessionScope.member || sessionScope.member eq ''}">
								<a href="javascript:;" onClick="alert('로그인 후 주문가능합니다.');">	
							</c:when>
							<c:otherwise>
								<a href="javascript:;" onClick="fnOrderLayerPopOpen(this, ${item.idx});">
							</c:otherwise>
						</c:choose>
							<span class="img"><img alt="" src="/${filePath}/${item.commonFile.fileGroup}/${item.commonFile.fileSysName}"></span>
							<div class="t_wrap">
								<p class="prod_n">${item.prodName}</p>
								<p>${item.prodSpec1}</p>
								<p>${item.prodSpec2}</p>
								<p>${item.prodSpec3}</p>
								<p class="prod_s">${item.prodSize}</p>
							</div>
						</a>
					</li>
				</c:forEach>					
			</c:if>
		</ul>
	</div>
	
	<div class="btn_wrap cen" id="loadListBtn">
		<button type="button" class="btn_more" onClick="fnLoadList();"><span class="hide">더보기</span></button>
	</div>
	
</div>

<!-- Layer popup 문의하기 -->
<div class="layer_pop" id="order" style="display:none">
	<p class="t_txt">우원이엔지 제품 주문하기</p>
	<button type="button" class="btn_closed"><span class="hide">닫기</span></button>
	<div class="layer_content">
		<div class="fl_wrap">
			<div class="fl_left">
				<div class="prod_wrap">
					<span class="img"><img src="/admin/images/prod/sample_prod_01.jpg" alt=""></span>
					<div class="t_wrap">
						<button type="button" class="btn_app pc" onClick="addOrder();">주문하기</button>
					</div>
					
					<div class="btn_wrap mo">
						<button type="button" class="btn_app" onClick="addOrder();">주문하기</button>
					</div>
				</div>
			</div>

			<div class="fl_right">
				<div class="table_wrap">
					<form name="orderForm" id="orderForm" action="/order/addOrder.do" method="post">
						<input type="hidden" name="idx" id="idx" value="0">
						<input type="hidden" name="productIdx" id="productIdx" value="0">
						<input type="hidden" name="productName" id="productName" value="">
						<!-- table -->
						<table border="1">
							<caption>주문하기</caption>
							<colgroup>
								<col class="w65" style="width:65px;">
								<col class="w95" style="width:135px;">
								<col class="w55" style="width:65px;">
								<col class="w85" style="width:135px;">
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">주문수량</th>
									<td><input type="text" id="amount" value="" title="" name="amount" style="width:95px" /></td>
									<th scope="row">담당자</th>
									<td><input type="text" id="tptb" value="" title="" name="tptb" style="width:95px" /></td>
								</tr>
								<tr>
									<th scope="row">배송주소</th>
									<td colspan="3">
										<div class="int_wrap">
											<input type="text" id="deliverAddr" title="" name="deliverAddr" value="" style="width:295px" />
											<textarea name="comment" id="comment" rows="" cols="" style="width:295px; height:76px" placeholder="요청사항을 입력해주세요"></textarea>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //table -->
					</form>
				</div>
			</div>
		</div>

	</div>
</div>
<!-- //Layer popup 문의하기 -->