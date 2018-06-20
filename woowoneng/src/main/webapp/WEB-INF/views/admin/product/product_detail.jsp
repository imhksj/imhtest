<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("상품관리");
		
		//수정이면
		if("${!empty product}" != "false")	$("#f").attr("action","modiProduct.do");
		
		//카테고리1 셀렉트박스 변경시 카테고리2 셀렉트박스 셋팅
		$("#sel_01").change(function(){
			
			//가존 카테고리 option 내용 삭제
			$("#sel_02 option").remove();
			$("#sel_03 option").remove();
			
			//카테고리2 셋팅
			$("#category2Copy option[pidx-data='"+$(this).val()+"']").clone().prependTo("#sel_02");
			
			//카테고리 2, 3 기본 셋팅
			$("#category2Copy option:eq(0)").clone().prependTo("#sel_02");
			$("#category3Copy option:eq(0)").clone().prependTo("#sel_03");
			
			//카테고리 2, 3 첫번째로 기본 선택
			$("#sel_02 option:eq(0)").prop("selected", true);
			$("#sel_02").trigger("change");
			$("#sel_03 option:eq(0)").prop("selected", true);
			$("#sel_03").trigger("change");

		});
		
		//카테고리2 셀렉트박스 변경시 카테고리3 셀렉트박스 셋팅
		$("#sel_02").change(function(){
			
			//카테고리 3 option 삭제
			$("#sel_03 option").remove();
			
			//카테고리3 셋팅
			$("#category3Copy option[pidx-data='"+$(this).val()+"']").clone().prependTo("#sel_03");
			
			//카테고리 3 기본 셋팅
			$("#category3Copy option:eq(0)").clone().prependTo("#sel_03");
			
			//3 첫번째로 기본 선택
			$("#sel_03 option:eq(0)").prop("selected", true);
			$("#sel_03").trigger("change");
			
		});
		
		//수정시 선택사양 값 셋팅
		setChecked("prodChoicSpec", "${product.prodChoicSpec}", 1);
		
		//수정시 카테고리1 셋팅
		setSelected($("#sel_01"), "${product.category1}");
		$("#sel_01").trigger("change");
		
		//수정시 카테고리2 셋팅
		setSelected($("#sel_02"), "${product.category2}");
		$("#sel_02").trigger("change");
		
		//수정시 카테고리3 셋팅
		setSelected($("#sel_03"), "${product.category3}");
		$("#sel_03").trigger("change");
		
				
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
	            $(".preview .img").html(imgHtml);
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
				location.href = "product_list.do";
			}
						
		}
		
	}
	
	//상품저장, 수정
	function addProd(){
		
		if($("#idx").val() == "0"){
			if(!confirm("저장하시겠습니까?"))	return;	
		}else{
			if(!confirm("수정하시겠습니까?"))	return;
		}

		if($("#sel_01 option").length > 1 && $("#sel_01").val() == "0"){
			alert("카테고리1는 필수 입력 항목입니다.");
			return;
		}
		if($("#sel_02 option").length > 1 && $("#sel_02").val() == "0"){
			alert("카테고리2는 필수 입력 항목입니다.");
			return;
		}
		if($("#sel_03 option").length > 1 && $("#sel_03").val() == "0"){
			alert("카테고리3는 필수 입력 항목입니다.");
			return;
		}
		
		if(!nullChkAlert($("#prodNum").val() ,"제품번호는 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#prodName").val() ,"제품명은 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#prodSize").val() ,"사이즈는 필수 입력 항목입니다."))	return;
		if(!nullChkAlert($("#prodSpec1").val() ,"제품사양 1은 필수 입력 항목입니다."))	return;
		
		if($(".preview .img img").length <= 0){
			alert("제품이미지는 필수 입력 항목입니다.");
			return;
		}
		
		postFormAjax("f", saveAjaxReturn);
		
	}
	
	//상품삭제
	function delProd(){
		
		if(!confirm("삭제하시겠습니까?"))	return;			
		
		$.get("delProduct.do?idx=${product.idx}", function(jsonData){
			
			alert(jsonData.RETURN_MSG);
			
			if(jsonData.RETURN_CODE == "0000"){
				fnGoList();
			}
			
		});
		
	}
	
	//리스트 이동
	function fnGoList(){
		
		location.href = "product_list.do?reqPage=${param.reqPage}&category1=${param.category1}&category2=${param.category2}&category3=${param.category3}";
		
	}
	
</script>

<select id="category2Copy" style="display:none">
	<option value="0">카테고리2 선택</option>
	<c:forEach items="${cateList}" var="item">
		<c:if test="${item.depthNum eq 2}">
			<option value="${item.idx}" pidx-data="${item.pidx}">${item.cName}</option>	
		</c:if>
	</c:forEach>
</select>
<select id="category3Copy" style="display:none">
	<option value="0">카테고리3 선택</option>
	<c:forEach items="${cateList}" var="item">
		<c:if test="${item.depthNum eq 3}">
			<option value="${item.idx}" pidx-data="${item.pidx}">${item.cName}</option>	
		</c:if>
	</c:forEach>
</select>

		<div class="content">
			<div class="h_area">
				<h2>제품관리</h2>
				<div class="al_right">
					<button type="button" class="btn_base gray" onClick="fnGoList();">목록</button>
					<button type="button" class="btn_base" onClick="addProd();">${!empty product?'수정':'저장'}</button>
				</div>
			</div>
			
			<div class="table_wrap detail">
				<form name="f" id="f" action="addProduct.do" method="post">
					<input type="hidden" name="idx" id="idx" value="${!empty product?product.idx:'0'}">
					<!-- table -->
					<table border="1">
						<caption>컨텐츠 관리 수정</caption>
						<colgroup>
							<col style="width:110px;">
							<col style="width:355px;">
							<col style="width:240px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col" colspan="3">제품정보 ${!empty product?'수정하기':'등록하기'}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">ㆍ카테고리<span class="imp">*</span></th>
								<td colspan="2">
									<span class="select" style="width:180px">
										<label for="sel_01">카테고리1 선택</label>
										<select id="sel_01" name="category1" style="">
											<option value="0">카테고리1 선택</option>
											<c:forEach items="${cateList}" var="item">
												<c:if test="${item.depthNum eq 1}">
													<option value="${item.idx}">${item.cName}</option>	
												</c:if>
											</c:forEach>
										</select>
									</span>
									<span class="select" style="width:180px">
										<label for="sel_02">카테고리2 선택</label>
										<select id="sel_02" name="category2" style="">
											<option value="0">카테고리2 선택</option>
										</select>
									</span>
									<span class="select" style="width:180px">
										<label for="sel_03">카테고리3 선택</label>
										<select id="sel_03" name="category3" style="">
											<option value="0">카테고리3 선택</option>
										</select>
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="3" class="line"><span></span></td>
							</tr>
							<tr>
								<th scope="row">ㆍ제품번호<span class="imp">*</span></th>
								<td><input type="text" id="prodNum" title="" name="prodNum" placeholder="제품번호를 입력해주세요" value="${product.prodNum}" style="width:300px" /></td>
								<td rowspan="8">
									<div class="preview_wrap">
										<p class="tit">미리보기</p>
										<div class="preview">
											<span class="img">
												<c:if test="${!empty product.commonFile && product.commonFile.fileSysName ne ''}">
													<img alt="" src="/${filePath}/${product.commonFile.fileGroup}/${product.commonFile.fileSysName}">
												</c:if>
											</span>
											<div class="t_wrap">
												<p class="prod_n">${!empty product?product.prodName:'제품명'}</p>
												<p id="prodSpec_1">${!empty product?product.prodSpec1:'제품사양1'}</p>
												<p id="prodSpec_2">${!empty product?product.prodSpec2:'제품사양2'}</p>
												<p id="prodSpec_3">${!empty product?product.prodSpec3:'제품사양3'}</p>
												<p class="prod_s">${!empty product?product.prodSize:'사이즈'}</p>
											</div>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">ㆍ제품명<span class="imp">*</span></th>
								<td><input type="text" id="prodName" title="" name="prodName" value="${product.prodName}" placeholder="제품명을 입력해주세요" style="width:300px" onChange="$('.prod_n').html(this.value)" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ사이즈<span class="imp">*</span></th>
								<td><input type="text" id="prodSize" title="" name="prodSize" value="${product.prodSize}" placeholder="예) W1400XD500XH2000" style="width:300px" onChange="$('.prod_s').html(this.value)" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ제품사양 1<span class="imp">*</span></th>
								<td><input type="text" id="prodSpec1" title="" name="prodSpec1" value="${product.prodSpec1}" placeholder="제품사양을 입력해주세요" style="width:300px" onChange="$('#prodSpec_1').html(this.value)" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ제품사양 2</th>
								<td><input type="text" id="" title="" name="prodSpec2" value="${product.prodSpec2}" placeholder="제품사양을 입력해주세요" style="width:300px" onChange="$('#prodSpec_2').html(this.value)" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ제품사양 3</th>
								<td><input type="text" id="" title="" name="prodSpec3" value="${product.prodSpec3}" placeholder="제품사양을 입력해주세요" style="width:300px" onChange="$('#prodSpec_3').html(this.value)" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ제품사양 4</th>
								<td><input type="text" id="" title="" name="prodSpec4" value="${product.prodSpec4}" placeholder="제품사양을 입력해주세요" style="width:300px" /></td>
							</tr>
							<tr>
								<th scope="row">ㆍ제품이미지<span class="imp">*</span></th>
								<td>
									<span class="b_file type2">
										<em class="txt">이미지를 등록해주세요</em>
										<img src="/admin/images/btn_file_type2.gif" onclick="document.getElementById('prodImg').click();" alt="파일선택">
										<input type="file" name="prodImg" id="prodImg" placeholder="ddd" onchange="fileTypeChk(this)">
										<input type="hidden" id="fileGroup" name="fileGroup" value="PRODUCT">
										<input type="hidden" id="fileType" name="fileType" value="PRODUCT_IMG">
										<input type="hidden" id="orderNum" name="orderNum" value="0">
										<input type="hidden" id="fileIdx" name="fileIdx" value="${!empty product.commonFile?product.commonFile.idx:'0'}">
									</span>
								</td>
							</tr>
							<tr>
								<th scope="row">ㆍ선택사양</th>
								<td colspan="2">
									<span class="rdo"><input type="radio" id="rdo_01" title="" name="prodChoicSpec" value="클린사업장 지원제품" /> <label for="rdo_01">클린사업장 지원제품</label></span>
									<span class="rdo"><input type="radio" id="rdo_02" checked="checked" title="" name="prodChoicSpec" value="" /> <label for="rdo_02">없음</label></span><br />
									<span class="rdo"><input type="radio" id="rdo_03" title="" name="prodChoicSpec" value="정부조달사업 등록제품" /> <label for="rdo_03">정부조달사업 등록제품</label></span>
								</td>
							</tr>
						</tbody>
					</table>
					<!-- //table -->
				</form>
			</div>

			<div class="btn_wrap cen line">
				<c:if test="${!empty product}">
					<button class="btn_base red" onClick="delProd()">삭제</button>				
				</c:if>
				<button class="btn_base print fl_right" onClick="printOutPut();">프린트</button>
			</div>

		</div>