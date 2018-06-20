var ajaxProgress = '<div id="ajaxProgress" style="position:fixed; top:0; right:0; bottom:0; left:0; z-index:100; background-color:rgba(255, 255, 255, 0.5); display:none">' +
						'<span style="display:block; position:absolute; top:50%; left:50%; margin:-33px 0 0 -150px; width:300px; background:#000; color:#fff; font-size:24px; text-align:center; line-height:66px;">' +
							'처리중...' +
						'</span>' +
					'</div>';

var imgLoadProgress = '<div id="fileLoadProgress" style="position:fixed; top:0; right:0; bottom:0; left:0; z-index:100; background-color:rgba(255, 255, 255, 0.5); display:block">' +
'<span style="display:block; position:absolute; top:50%; left:50%; margin:-33px 0 0 -150px; width:300px; background:#000; color:#fff; font-size:24px; text-align:center; line-height:66px;">' +
	'파일 로드중...' +
'</span>' +
'</div>';


//프린트
function printOutPut(){
	$(".content").printThis();
}


/*
 * @author MyungHo Lim
 * 
 * Form Ajax 전송 (multipart/form-data 전송가능)
 * @param Form name
 * @param callback 함수명
 * @return callback(jsonData)
 */
function postFormAjax(formName, callback){
	
	var form = $("form[name='" + formName + "']")[0];
	
	if(form == null){
		alert("FORM을 찾을수가 없습니다.");
    	return;
	}
	
    var formData = new FormData(form);
    var ajaxUrl =  $(form).attr("action");
   	
    if (callback instanceof Function) {
    	
    	$.ajax({
            url: ajaxUrl,
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            dataType: "json",
            success: function(jsonData){
            	callback(jsonData);
            }
        });
    	
    }else{
    	
    	alert("콜백함수를 찾을수가 없습니다.");
    	return;
    	
    }

}

/*
 * @author MyungHo Lim
 * 
 * Form Ajax 전송 (multipart/form-data 전송가능)
 * @param Form name
 * @param callback 함수명
 * @return callback(jsonData, form)
 */
function postFormAjax2(formName, callback){
	
	var form = $("form[name='" + formName + "']")[0];
	
	if(form == null){
		alert("FORM을 찾을수가 없습니다.");
    	return;
	}
	
    var formData = new FormData(form);
    var ajaxUrl =  $(form).attr("action");
   	
    if (callback instanceof Function) {
    	
    	$.ajax({
            url: ajaxUrl,
            processData: false,
            contentType: false,
            data: formData,
            type: 'POST',
            dataType: "json",
            success: function(jsonData){
            	callback(jsonData, form);
            }
        });
    	
    }else{
    	
    	alert("콜백함수를 찾을수가 없습니다.");
    	return;
    	
    }

}

/*
 * @author MyungHo Lim
 * 
 * Ajax 전송 처리중 화면 스크린 
 */
function ajaxProgressSet(){
	
	$(".content").after(ajaxProgress);
	
	$( document ).ajaxStart(function() {
		$("#ajaxProgress").show();
	});
	$( document ).ajaxStop(function() {
		$("#ajaxProgress").hide();
	});

}

/*
 * @author MyungHo Lim
 * 
 * null 값 체크후 alert 메시지 출력
 * @param String 비교값
 * @param String 메시지
 * @return boolean
 */
function nullChkAlert(str ,msg){
	
	if($.isEmptyObject(str)){
		alert(msg);
		return false;
	}

	return true;
	
}

function nullChkAlert2(str ,msg){
	
	if($.isEmptyObject(str) || str == '0'){
		alert(msg);
		return false;
	}

	return true;
	
}

/*
 * @author MyungHo Lim
 * 
 * 라디오박스 & 체크박스 체크후 checked
 * @param String input 이름
 * @param String 비교 value값
 * @param String checked index (체크된 값이 없을때)
 */
function setChecked(objName, objVal, cnt){

	$("input[name='" + objName + "']").each(function(){
	
		if($(this).val() == objVal){
			$(this).prop("checked", true);
		}else{
			$(this).prop("checked", false);
		}
		
	});
	
	if($("input[name='" + objName + "']:checked").length == 0 && cnt > -1){
		$("input[name='" + objName + "']").eq(cnt).prop("checked", true);
	};
	
}

/*
 * @author MyungHo Lim
 * 
 * 셀렉트박스 selected
 * @param String 셀렉트박스 객체
 * @param String 비교 value값
 */
function setSelected(obj, objVal){
	
	$(obj).find("option").each(function(){
	
		if($(this).val() == objVal){
			$(this).prop("selected", true);
		}else{
			$(this).prop("selected", false);
		}
		
	});
	
}

/*
 * @author MyungHo Lim
 * 
 * 셀렉트박스 selected
 * @param String 셀렉트박스 객체
 * @param String 비교 value값
 * @param String 비교값이 없을때
 */
function setSelectedDef(obj, objVal, defaultVal){
	
	if(objVal == null || objVal == ""){
		console.log("value:"+objVal);
		$(obj).find("option[value='"+defaultVal+"']").prop("selected", true);
		return;
	}
	
	$(obj).find("option").each(function(){
		
		if($(this).val() == objVal){
			$(this).prop("selected", true);
			return false;
		}else{
			$(this).prop("selected", false);
		}
		
	});
	
}


/*
 * @author MyungHo Lim
 * 
 * input 달력 셋팅
 * @param Object input객체
 */
function datePickerSet(obj){

	//달력
	$(obj).datepicker({
		showOn : "both",
		buttonImage : "/admin/images/calender_img.png",
		buttonImageOnly : true,
		buttonText: "Select date",
		dateFormat : 'yy-mm-dd'
	});

	$(obj).datepicker({
		showOn : "both",
		buttonImage : "/admin/images/calender_img.png",
		buttonImageOnly : true,
		buttonText: "Select date",
		dateFormat : 'yy-mm-dd'
	});
	$("img.ui-datepicker-trigger").attr("style","vertical-align:middle;");
	
};

/*
 * @author MyungHo Lim
 * 
 * 체크박스 전체선택
 * @param Object input all체크박스 객체
 * @param String checked 할 input객체 이름
 */
function allChecked(obj, objName){
	$("input[name='"+objName+"']").prop("checked", $(obj).prop("checked"));
}

/*
 * @author MyungHo Lim
 * 
 * select box에 시, 분 option 셋팅
 * @param Object select 객체
 * @param int 시, 분 종료 번호
 * @param String option text에 붙을 텍스트
 */

function timeOptionSet(obj, endNum, str){
	
	var optionHtml;	
	var num;
	
	for(i = 0; i <= endNum; i++){
		num = i;
		if(i < 10)  num = '0' + i;
		optionHtml += '<option value="' + num + '">' + num + str + '</option>';
	}
	
	$(obj).html(optionHtml);
	
}

/*
 * @author Myung Ho Lim
 * 
 * file ext check
 * @param file 객체
 * @param file type
 */
function fileTypeCheck(obj, type){
	if(type == 'image') return /([^\s]+(?=\.(jpg|gif|png))\.\2)/i.test(obj);
	else if(type == 'doc') return /([^\s]+(?=\.(hwp|pdf|doc|ppt|pptx|docx|xls|xlsx|zip))\.\2)/i.test(obj);
	else if(type == 'media') return /([^\s]+(?=\.(avi|mpg|mpeg|asf|wma|flv))\.\2)/i.test(obj);
	else if(type == 'all') return /([^\s]+(?=\.(jpg|gif|png|hwp|pdf|doc|ppt|pptx|docx|xls|xlsx|zip|avi|mpg|mpeg|asf|wma|flv))\.\2)/i.test(obj);
	else false;
}

/*
 * @author Myung Ho Lim
 * 
 * SNS Share
 * @param snsType sns타입
 * @param title 제목
 * @param site_url 공유사이트 url
 */
function shareSNS(snsType, title, site_url){
	var url = "";
	switch(snsType){
		case "kakaostory":
			url = "https://story.kakao.com/share?url="+encodeURIComponent(site_url);
			window.open(url, "kakaoStoryShare", "width=530, height=480, resizable=no, scrollbars=no, status=no");
			break;

		case "twitter":
			url = "https://twitter.com/intent/tweet?text="+title+"&url="+encodeURIComponent(site_url);
			window.open(url, "StoryShare", "width=530, height=445, resizable=no, scrollbars=no, status=no");
			break;
			
		case "facebook":
			url = "http://www.facebook.com/sharer/sharer.php?u="+encodeURIComponent(site_url);
			window.open(url, "facebookShare", "width=530, height=330, resizable=no, scrollbars=no, status=no");
			break;
		case "blog":
			url = "http://share.naver.com/web/shareView.nhn?url=" + encodeURIComponent(site_url) + "&title=" + encodeURIComponent(title);
			window.open(url, "blogShare", "width=530, height=500, resizable=no, scrollbars=no, status=no");
			break;
	}
}

/*
 * @author Lim Myung Ho
 * 
 * 폼 날짜 비교
 * @param 사직일 객체 id
 * @param 종료일 객체 id
 * @param 호출 폼 객체
 */
function changeDate(sDateId, eDateId, obj){
	
	if($("#"+sDateId).val() != null && $("#"+sDateId).val() != "" && $("#"+eDateId).val() != null && $("#"+eDateId).val() != ""){
		
		var sDate = new Date($("#"+sDateId).val());
		var eDate = new Date($("#"+eDateId).val());
		
		if(sDate.getTime() > eDate.getTime()){
			
			if($(obj).attr("id") == "sDate" || $(obj).attr("id") == "startDate"){
				alert("시작일은 종료일 보다 이전 날짜를 선택 하셔야 합니다.");
			}else{
				alert("종료일은 시작일 보다 이후 날짜를 선택 하셔야 합니다.");
			}
			$(obj).val("");
		}
		
	}
		
}

(function($){
	
	//현재 날짜 객체
	$.getNowDate = {
			//현재날짜 Date 객체 리턴
			getNewDate : function(){
				var date = new Date();
				date.setFullYear(date.getFullYear(), date.getMonth()+1, date.getDate());
				return date;
			},
			//입력받은 token 으로 구분자로 현재날짜 String 리턴 
			getDate : function(token){
				return $.getNowDate.getYear() + token + $.getNowDate.getMonth() + token + $.getNowDate.getDay();
			},
			//입력받은 token 으로 구분자로 현재시간 String 리턴
			getTime : function(token){
				var date = $.getNowDate.getNewDate();
				return date.getHours() + token + date.getMinutes() + token +date.getSeconds();
			},
			//현재 년도 String 리턴
			getYear : function(){
				return $.getNowDate.getNewDate().getFullYear();
			},
			//현재 월 String 리턴
			getMonth : function(){
				var month = String($.getNowDate.getNewDate().getMonth());
				if(month.length < 2)	month = "0" + month;
				return month;
			},
			//현재 일 String 리턴
			getDay : function(){
				var day = String($.getNowDate.getNewDate().getDate());
				if(day.length < 2)	day = "0" + day;
				return day;
			}
			
	};
	
	
})(jQuery);

//모바일 브라우저 체크
(function (a) {
    (jQuery.browser = jQuery.browser || {}).mobile = /(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino/i.test(a) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(a.substr(0, 4))
})(navigator.userAgent || navigator.vendor || window.opera);