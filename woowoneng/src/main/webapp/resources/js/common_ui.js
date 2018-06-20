
/* Common */
jQuery(function($){
	/* Admin */
	$('.lnb .site > a').click(function() {
		$(this).parent().toggleClass('on');
	});

	/* select */
	var select = $("select");

	select.change(function(){
		var select_name = $(this).children("option:selected").text();
		$(this).siblings("label").text(select_name);
	});

	$('.faq_list > ul > li > a').click(function() {
		$(this).parent().toggleClass('on');
		$(this).next('.answ').slideToggle(200);
		
		//조회수 증가
		if($(this).parent().hasClass("on")){
			var faqIdx = $(this).attr("id");
			faqIdx = faqIdx.replace("faqIdx_","");
			$.get("updateFaqHit.do?idx="+faqIdx);
		}		
		
	});

	$('#order.layer_pop .btn_closed').click(function() {
		$('.layer_pop').css('display', 'none');
		$('.layer_dim').removeClass('on');
		$("#orderForm input").val("");
		$("#orderForm textarea").val("");
	});

});



/* Madia */
jQuery(function($){

	// Made
	var lastWidth = 0;
	var resize = function(){
		var currentWidth = $(this).width();
		if(lastWidth==0){
			//initial
			var status = currentWidth<818 ? 'mobile' : (currentWidth>=818 ? 'pc' : '');
		}else{
			var status = currentWidth < lastWidth ? 'mobile' : (currentWidth > lastWidth ? 'pc' : '');
		}
		
		if(status=='mobile' && currentWidth < 818 && (lastWidth > 818 || lastWidth == 0) ){
			// Mobile
			
			/* Gnb */
			$('.util_menu .menu_wrap .btn_menu').click(function() {
				$('.util_menu .menu_wrap .menu_list').addClass('open');
				$('body').addClass('hidden');
			});
			$('.util_menu .menu_wrap .menu_list .btn_closed').click(function() {
				$('.util_menu .menu_wrap .menu_list').removeClass('open');
				$('body').removeClass('hidden');
			});

			/* search */
			$('.menu_list .search_wrap input').click(function() {
				$('.menu_list .search_wrap').addClass('on');
			});
			$('#wrap').click(function() {
				$('.menu_list .search_wrap').removeClass('on');
			});
			$(".search_wrap input, .search_wrap button").click(function(e){ return false; });

			/* Layer unbind */
			$(".util_list .u01 a, .util_list .u02 a, .util_list .u03 a" ).unbind("click");

			//모바일 상품 메뉴 사이즈 조정
			$(".menu_list .menu > li:nth-child(even)").each(function(idx){
				
				if($(this).find("a").html() != "기타"){
					
					if($(this).height() > $(this).prev().height()){
						$(this).prev().height($(this).height());
					}else{
						$(this).height($(this).prev().height());
					}
					
				}
			
			});
			
			//top nav 메뉴중 사업부문 모바일일때 링크 변경
			$("#topNavBusinessLink").attr("href","/business/business_info.do");

		}else if(status=='pc' && currentWidth >= 818  && (lastWidth < 818 || lastWidth==0) ){
			// PC

			/* Gnb */
			$('.nav_wrap').mouseenter(function() {
				$('nav').addClass('on');
			});

			$('.util_menu, #container').mouseenter(function() {
				$('nav').removeClass('on');
			});

			/* search */
			$('.util_menu .search_wrap input').click(function() {
				$('.util_menu .search_wrap').addClass('on');
			});
			$('#wrap').click(function() {
				$('.util_menu .search_wrap').removeClass('on');
			});
			$(".search_wrap input, .search_wrap button").click(function(e){ return false; });

			/* Layer */
			$('.top_util .log').click(function() {
				$('#login.layer_pop').slideDown(200);
				$('.layer_dim').addClass('on');
			});
			$('.layer_pop .btn_closed').click(function() {
				$('.layer_pop').slideUp(200);
				$('.layer_dim').removeClass('on');
			});

			$('.util_list .u01 a').click(function() {
				$('#inquire.layer_pop').slideDown(200);
				$('.layer_dim').addClass('on');
			});
			$('.util_list .u02 a').click(function() {
				$('#as.layer_pop').slideDown(200);
				$('.layer_dim').addClass('on');
			});
			$('.util_list .u03 a').click(function() {
				$('#catalog.layer_pop').slideDown(200);
				$('.layer_dim').addClass('on');
			});
			
			//top nav 메뉴중 사업부문 PC일때 링크 변경
			$("#topNavBusinessLink").attr("href","/business/industrial.do");			

		}
	 lastWidth = currentWidth;
	}

	$(window).resize(function(){
	 resize();
	}).triggerHandler('resize');
});

