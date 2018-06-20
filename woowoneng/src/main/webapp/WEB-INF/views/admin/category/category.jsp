<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("카테고리관리");
		
		//카테고리 분류 셀렉트박스 체인지 이벤트
		$("#sel_01").change(function(){
			location.href = "?cType=" + $(this).val();
		});
		
		$("[name='addBtn']").each(function(index){
			$(this).click(function(){
				addCategory(index, this);
			});
		});
		
		setSelected($("#sel_01"), "${param.cType}");
		$("#sel_01").prev().html($("#sel_01 option:selected").html());

	});
	
	//카테고리 뎁스별 조회
	function getCategory(obj){
		
		var listObj = $(obj).closest(".list");
		var cType = $("#sel_01").val();
		var pidx = $(obj).find("button").val();
		
		$(listObj).find("li").removeClass("on");
		$(obj).addClass("on");
		
		$.post("getCategoryDepth.do", {cType:cType, pidx:pidx}, function(jsonData){
			
			var cateList = jsonData.cateList;
			var cateLihtml = "";
			var clickEvent = listObj.next().next().hasClass("list")?' onClick="getCategory(this);"':'';
			
			if(cateList != null && cateList.length > 0){
				${item.cName}
				for(i in cateList){
					cateLihtml += '<li' + clickEvent + '>' +
										'<span class="txt">' + cateList[i].cName + '</span>' +
										'<input type="text" value="' + cateList[i].cName + '" id="" title="" name="cName" style="width:112px; display:none">' +
										'<button onClick="modiCategory(this)" value="' + cateList[i].idx + '" type="button" class="btn_base">수정</button>' +
										'<button onClick="delCategory(this)" value="' + cateList[i].idx + '" type="button" class="btn_base red">삭제</button>' +
									'</li>';
				}
				
			}

			listObj.next().find("ul").html(cateLihtml);	
			
		});
		
	}
	
	//카테고리 추가
	function addCategory(depthNum, obj){
		
		var pidx = 0;
		var topIdx = 0;
		var cName = $(obj).prev().val();
		var cType = $("#sel_01").val();
		
		if(!nullChkAlert(cType ,"카테고리 분류를 선택해주세요.."))	return;
		
		if(depthNum > 0){
			pidx = $(".list").eq(depthNum-1).find("li[class='on']").find("button").val();
			topIdx = $(".list").eq(0).find("li[class='on'] button").val();
		}
		
		if(pidx == null || topIdx == null){
			alert("카테고리" + depthNum + " 를 선택해주세요.");
			return;
		}

		$.post("addCategory.do", {cType:cType, cName:cName, depthNum:depthNum+1, topIdx:topIdx, pidx:pidx}, function(jsonData){

			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){

				var clickEvent = $(".list").eq(depthNum).next().hasClass("list")?' onClick="getCategory(this);"':'';
				var cateLihtml = '<li' + clickEvent + '>' +
										'<span class="txt">' + cName + '</span>' +
										'<input type="text" value="' + cName + '" id="" title="" name="cName" style="width:112px; display:none">' +
										'<button onClick="modiCategory(this)" value="' + jsonData.IDX + '" type="button" class="btn_base">수정</button>' +
										'<button onClick="delCategory(this)" value="' + jsonData.IDX + '" type="button" class="btn_base red">삭제</button>' +
									'</li>';
				
				$(".list").eq(depthNum).find("ul").append(cateLihtml);
				
				$(obj).prev().val("");
				
			}
			
		});
		
	}

	//카테고리 수정
	function modiCategory(obj){

		if($(obj).html() == "수정"){
			
			$(obj).prev().show();
			$(obj).html("저장");
			$(obj).next().html("취소");
			
		}else{
			
			$.post("modiCategory.do", {idx:$(obj).val(), cName:$(obj).prev().val()}, function(jsonData){
			
				alert(jsonData.RETURN_MSG);
				
				if(jsonData.RETURN_CODE == "0000"){
					
					$(obj).prev().hide();
					$(obj).siblings("span").html($(obj).prev().val());
					$(obj).html("수정");
					$(obj).next().html("삭제");
					
				}
				
			});
		}
		
	}
	
	//카테고리 삭제
	function delCategory(obj){

		if($(obj).html() == "취소"){
			
			$(obj).prev().html("수정");
			$(obj).siblings("input").val($(obj).siblings("span").html()).hide();
			$(obj).html("삭제");
			
		}else{
			
			$.post("delCategory.do", {idx:$(obj).val()}, function(jsonData){
			
				alert(jsonData.RETURN_MSG);
				
				if(jsonData.RETURN_CODE == "0000"){
					
					location.reload();
					
				}
				
			});
			
		}
		
	}
	
</script>

		<div class="content">
			<div class="h_area">
				<h2>카테고리관리</h2>
			</div>

			<div class="btn_cate">
				<span class="select" style="width:160px">
					<label for="sel_01">카테고리 분류 선택</label>
					<select id="sel_01" name="cType" style="">
						<option value="">카테고리 분류 선택</option>
						<option value="PRODUCT">제품 카테고리</option>
						<option value="NOTICE">공지사항 카테고리</option>
						<option value="CATALOG">카달로그 카테고리</option>
					</select>
				</span>
			</div>
			
			<div class="cate_wrap">
				<div class="list" id="depth1">
					<h3>카테고리1</h3>
					<ul>
						<c:if test="${!empty cateList && cateList.size() > 0}">
							<c:forEach items="${cateList}" var="item">
								<li onClick="getCategory(this);">
									<span class="txt">${item.cName}</span>
									<input type="text" value="${item.cName}" id="" title="" name="cName" style="width:112px; display:none">
									<button onClick="modiCategory(this)" value="${item.idx}" type="button" class="btn_base">수정</button>
									<button onClick="delCategory(this)" value="${item.idx}" type="button" class="btn_base red">삭제</button>
								</li>
							</c:forEach>
						</c:if>
					</ul>

					<div class="btn_wrap">
						<input type="text" value="" id="" title="" name="" style="width:153px">
						<button class="btn_base base" name="addBtn">추가</button>
					</div>
				</div>

				<div class="list">
					<h3>카테고리2</h3>
					<ul></ul>

					<div class="btn_wrap">
						<input type="text" value="" id="" title="" name="" style="width:153px">
						<button class="btn_base base" name="addBtn">추가</button>
					</div>
				</div>

				<div class="list">
					<h3>카테고리3</h3>
					<ul></ul>

					<div class="btn_wrap">
						<input type="text" value="" id="" title="" name="" style="width:153px">
						<button class="btn_base base" name="addBtn">추가</button>
					</div>
				</div>
			</div>

		</div>