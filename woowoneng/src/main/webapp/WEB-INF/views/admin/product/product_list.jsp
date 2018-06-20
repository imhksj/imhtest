<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<script type="text/javascript">

	$(document).ready(function(){
		
		//LNB 메뉴 클래스 셋팅
		lmbSet("제품관리");
		
		//카테고리1 셀렉트박스 변경시
		$("#sel_01").change(function(){
			
			location.href = "?category1=" + $(this).val();
			
		});
		
		//카테고리2 셀렉트박스 변경시
		$("#sel_02").change(function(){
			
			location.href = "?category1=" + $("#sel_01").val() + "&category2=" + $(this).val();
			
		});
		
		//카테고리2 셀렉트박스 변경시
		$("#sel_03").change(function(){
			
			location.href = "?category1=" + $("#sel_01").val() + "&category2=" + $("#sel_02").val() + "&category3=" + $(this).val();
			
		});
		
		//수정시 카테고리1 셋팅
		setSelected($("#sel_01"), "${param.category1}");
		$("#sel_01").prev().html($("#sel_01 option:selected").html());
		
		//가존 카테고리 option 내용 삭제
		$("#sel_02 option").remove();
		$("#sel_03 option").remove();
		
		//카테고리2 셋팅
		$("#category2Copy option[pidx-data='"+$("#sel_01").val()+"']").clone().prependTo("#sel_02");
		//카테고리 2
		$("#category2Copy option:eq(0)").clone().prependTo("#sel_02");
		//카테고리2 selected
		setSelected($("#sel_02"), "${param.category2}");
		$("#sel_02").prev().html($("#sel_02 option:selected").html());
		
		//카테고리3 셋팅
		$("#category3Copy option[pidx-data='"+$("#sel_02").val()+"']").clone().prependTo("#sel_03");
		//카테고리 2
		$("#category3Copy option:eq(0)").clone().prependTo("#sel_03");
		//카테고리3 selected
		setSelected($("#sel_03"), "${param.category3}");
		$("#sel_03").prev().html($("#sel_03 option:selected").html());
		
	});
	
	function fnGoWrite(){
		
		location.href = "product_detail.do?reqPage=${param.reqPage}&category1=" + $("#sel_01").val() + "&category2=" + $("#sel_02").val() + "&category3=" + $("#sel_03").val();
		
	}
	
</script>

<select id="category2Copy" style="display:none">
	<option value="">카테고리2</option>
	<c:forEach items="${cateList}" var="item">
		<c:if test="${item.depthNum eq 2}">
			<option value="${item.idx}" pidx-data="${item.pidx}">${item.cName}</option>	
		</c:if>
	</c:forEach>
</select>
<select id="category3Copy" style="display:none">
	<option value="">카테고리3</option>
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
					<span class="select" style="width:130px">
						<label for="sel_01">카테고리1</label>
						<select id="sel_01" name="category1" style="">
							<option value="">카테고리1</option>
							<c:forEach items="${cateList}" var="item">
								<c:if test="${item.depthNum eq 1}">
									<option value="${item.idx}" pidx-data="${item.pidx}">${item.cName}</option>	
								</c:if>
							</c:forEach>
						</select>
					</span>

					<span class="select" style="width:120px">
						<label for="sel_02">카테고리2</label>
						<select id="sel_02" name="category2" style="">
							<option value="">카테고리2</option>
						</select>
					</span>

					<span class="select" style="width:150px">
						<label for="sel_03">카테고리3</label>
						<select id="sel_03" name="category3" style="">
							<option value="">카테고리3</option>
						</select>
					</span>

					<button type="button" class="btn_base" onClick="fnGoWrite();">+ 등록</button>
				</div>
			</div>

			<div class="prod_list">
				<ul>
					<c:if test="${!empty productList}">
						<c:forEach items="${productList}" var="item" varStatus="vs">
							<li>
								<a href="product_detail.do?idx=${item.idx}&reqPage=${param.reqPage}&category1=${param.category1}&category2=${param.category2}&category3=${param.category3}">
									<span class="num">${item.prodNum}</span>
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

			<!-- page -->
			<div class="pager_wrap">
				<c:if test="${!empty productList && productList.size() > 0}">
					<jsp:include page="/WEB-INF/views/includes/common_page.jsp" flush="false" />
				</c:if>
			</div>
			<!-- //page -->

		</div>