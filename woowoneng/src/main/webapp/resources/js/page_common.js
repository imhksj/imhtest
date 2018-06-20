var busiSlider01;
var busiSlider02;

$(document).ready(function(){
						
	//문의하기 레이더 팝업 문위하기 버튼 활성화 이벤트
	$("#qnaPopForm #name, #qnaPopForm #tel, #qnaPopForm #content").keyup(function(){

		if($("#qnaPopForm #name").val() != "" && $("#qnaPopForm #tel").val() != ""){
			$("#qnaPopForm .btn_app").prop("disabled", false);
		}else{
			$("#qnaPopForm .btn_app").prop("disabled", true);
		}
		
	});
	
	//AS 레이더 팝업 문위하기 버튼 활성화 이벤트
	$("#asPopForm #name, #asPopForm #tel, #asPopForm #productName").keyup(asPopFormChk);
	$("#asPopForm #sel_01, #asPopForm #sel_02").change(asPopFormChk);

	//카달록 레이더 팝업 문위하기 버튼 활성화 이벤트
	$("#catalogPopForm #name, #catalogPopForm #tel").keyup(catalogPopFormChk);
	$("#catalogPopForm #sel_02").change(catalogPopFormChk);
	
	//클린사업 레이어팝업 클릭이벤트
	$('.top_util .busi01').click(function() {
		
		$('#busi01.layer_pop').fadeIn(200);
		$('.layer_dim').addClass('on');
		
		if(busiSlider01 == null){
			console.log("null이다");
			busiSlider01 = $('.slide_busi01').bxSlider({
				auto:false,
				speed:300,
				pause:7000,
				autoControls:true,
				pager:false,
				controls: true
			});
			
		}else{
			
			busiSlider01.reloadSlider();
			
		}
		
	});
	
	//정부조달사업 레이어팝업 클릭 이벤트
	$('.top_util .busi02').click(function() {
		
		$('#busi02.layer_pop').fadeIn(200);
		$('.layer_dim').addClass('on');
		
		if(busiSlider02 == null){
		
			busiSlider02 = $('.slide_busi02').bxSlider({
				auto:false,
				speed:300,
				pause:7000,
				autoControls:true,
				pager:false,
				controls: true
			});
			
		}else{
			
			busiSlider02.reloadSlider();
			
		}
	});

});

//AS 레이어팝업 폼 체크
function asPopFormChk(){
	
	if($("#asPopForm #name").val() != "" && $("#asPopForm #tel").val() != ""){
		$("#asPopForm .btn_app").prop("disabled", false);
	}else{
		$("#asPopForm .btn_app").prop("disabled", true);
	}
	
}

//카달로그 레이어팝업 폼 체크
function catalogPopFormChk(){
	
	if($("#catalogPopForm #name").val() != "" && $("#catalogPopForm #tel").val() != ""){
		$("#catalogPopForm .btn_app").prop("disabled", false);
	}else{
		$("#catalogPopForm .btn_app").prop("disabled", true);
	}
	
}

//로그인
function loginChk(obj, type){
	
	if(!nullChkAlert($(obj).find("#memberId").val() ,"아이디를 입력해주세요."))	 return;
	if(!nullChkAlert($(obj).find("#memberPwd").val() ,"비밀번호를 입력해주세요."))	 return;
	
	$.post("/member/loginProc.do", {memberId:$(obj).find("#memberId").val(), memberPwd:$(obj).find("#memberPwd").val()}, function(jsonData){

		alert(jsonData.RETURN_MSG);
		
		if(jsonData.RETURN_CODE == "0000"){
			if(type == "PC"){
				location.reload();
			}else{
				location.href = "/";
			}
		}
		
	}, "json");
	
}

//로그아웃
function logout(){
	
	$.get("/member/logoutProc.do", function(jsonData){
		
		alert(jsonData.RETURN_MSG);
		
		if(jsonData.RETURN_CODE == "0000"){
			location.href = "/";
		}
		
	});
	
}

//제품검색
function fnGoSearchList(obj){
	
	if($(obj).prev().val() == ""){
		alert("검색어를 입력해주세요.");
		return;
	}
	
	location.href = "/product/product_search_list.do?searchKeyword=" + $(obj).prev().val();
	
}

//ajax저장후 리턴 함수
function saveAjaxReturn(jsonData, formObj){
	
	alert(jsonData.RETURN_MSG);
	
	if(jsonData.RETURN_CODE == "0000"){
		
		$(formObj).prev().click();
		$(formObj).find("input").val("");
		$(formObj).find("input").prop("checked", false);
		$(formObj).find("textarea").val("");
		
		$(formObj).find("select").each(function(){
			
			$(this).find("option:eq(0)").prop("selected", true);
			$(this).trigger("change");
			
		});
					
	}
	
}

//문의하기, AS요청, 카달로그 요청 전송
function addCustomerRequest(formId){
	
	var formObj = $("#"+formId); 
	
	if($(formObj).find("#email1").val() != "" && $(formObj).find("#email2").val() != ""){
		
		$(formObj).find("#email").val($(formObj).find("#email1").val() + "@" + $(formObj).find("#email2").val());
		
	}
	
	if($(formObj).find("#privacyYn").val() == "N"){
		alert("개인정보 수집 및 이용에 동의를 해야합니다.");
		return;
	}
	
	postFormAjax2(formId, saveAjaxReturn);
	
}

//문의하기, AS요청, 카달로그 요청 개인정보 동의 여부 변경 이벤트
function changePrivacy(obj){

	if($(obj).prop("checked")){
		$(obj).closest("form").find("#privacyYn").val("Y");	
	}else{
		$(obj).closest("form").find("#privacyYn").val("N");
	}
	
}

//LNB 버튼 셋팅
function lmbSet(lmbName){
	
	$(".lnb li a").each(function(){
		if($(this).html() == lmbName){
			$(this).closest("li").addClass("on");
			return false;
		}
	})
}

//레이어 파일 타입 검증
function layerFileTypeChk(fileObj){
	
	if(fileTypeCheck($(fileObj).val(), "image")){
		$(fileObj).prev().find("span").html($(fileObj).val() + '<button type="button" onClick="layerDelFile(this)"><span class="hide">삭제</span></button>');	
	}else{
		$(fileObj).replaceWith($(fileObj).clone(true));
		alert("이미지 파일이 아닙니다.");
		return;
	}
	
}

//파일 삭제
function layerDelFile(obj){
	
	var fileObj = $(obj).closest("div").next();
	$(obj).closest("span").html("이미지파일을 등록해주세요.");
	$(fileObj).replaceWith($(fileObj).clone(true));
	
}