<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/common.jsp" %>

<!-- Login Layer popup -->
<div class="layer_pop" id="login" style="display:none">
	<p class="t_txt">[ 우원이엔지 웹사이트는 아이디를 부여받은 협력업체에서만 로그인이 가능합니다. ]</p>
	<button type="button" class="btn_closed"><span class="hide">닫기</span></button>
	<div class="layer_content">
		<div class="login">
			<p class="logo"><img src="/images/common/logo_layer.png" alt="WOOWONENG"></p>
			<dl>
				<dt><label for="id">아이디</label></dt>
				<dd><input type="text" value="" id="memberId" name="memberId" class="txt" title="아이디 입력란" placeholder="아이디를 입력해주세요" style="width:233px"></dd>
				<dt><label for="pass">비밀번호</label></dt>
				<dd><input type="password" value="" id="memberPwd" name="memberPwd" class="txt" title="비밀번호 입력란" placeholder="비밀번호를 입력해주세요" style="width:233px"></dd>
			</dl>

			<div class="btn_wrap">
				<button type="button" class="btn_login" onClick="loginChk($(this).closest('.login'), 'PC');">로그인</button>
			</div>

			<p class="t_info">아이디 및 비밀번호가 기억나지 않는다면?<br />우원이엔지 고객센터로 연락주시기 바랍니다.</p>
			<p class="tel"><span>고객센터 051-647-7182~3</span></p>
		</div>
	</div>
</div>
<!--// Login Layer popup -->

<!-- Layer popup 문의하기 -->
<div class="layer_pop" id="inquire" style="display:none">
	<p class="t_txt">[ 우원이엔지 문의하기 ]</p>
	<button type="button" class="btn_closed"><span class="hide">닫기</span></button>
	<form name="qnaPopForm" id="qnaPopForm" action="/customer/addQna.do" method="post">
		<input type="hidden" name="requestType" id="requestType" value="문의하기">
		<input type="hidden" name="privacyYn" id="privacyYn" value="N">
		<input type="hidden" name="email" id="email" value="">
		<input type="hidden" name="idx" id="idx" value="0">
		<div class="layer_content">
			<div class="fl_wrap">
				<div class="fl_left">
					<div class="table_wrap">
						<!-- table -->
						<table border="1">
							<caption>문의하기</caption>
							<colgroup>
								<col style="width:85px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">· 성명<span class="imp">*</span></th>
									<td><input value="" type="text" id="name" title="" name="name" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 연락처<span class="imp">*</span></th>
									<td><input value="" type="text" id="tel" title="" name="tel" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 주소</th>
									<td><input value="" type="text" id="addr" title="" name="addr" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 개인정보</th>
									<td>
										<div class="box_info">
											<p>개인정보 항목 : 성명, 상호명, 연락처, 주소<br />수집 및 이용목적 : 제품안내 및 정보제공, 고객상담<br />보유 및 이용기간 : 개인정보 수집일로부터 개인정보 파기 요청시 까지</p>
										</div>
										<span class="chk"><input type="checkbox" id="check01" title="" onChange="changePrivacy(this);" /><label for="check01">개인정보 수집 및 이용에 동의합니다.</label></span>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //table -->
					</div>
				</div>
	
				<div class="fl_right">
					<div class="table_wrap">
						<!-- table -->
						<table border="1">
							<caption>문의하기</caption>
							<colgroup>
								<col style="width:85px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">· 상호명</th>
									<td><input type="text" value="" id="companyName" title="" name="companyName" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 이메일</th>
									<td>
										<div class="mail_wrap">
											<input type="text" value="" id="email1" title="" name="email1" style="width:147px">
											<span class="fmr">@</span>
											<input type="text" value="" id="email2" title="" name="email2" style="width:147px">
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row">· 내용</th>
									<td><textarea name="content" id="content" rows="" cols="" style="width:335px; height:84px" placeholder="내용을 입력해주세요."></textarea></td>
								</tr>
								<tr>
									<th scope="row">· 첨부파일</th>
									<td>
										<div class="b_file">
											<a href="javascript:" class="btn_file" onclick="$(this).nextAll('#file').click();">파일선택</a>
											<div class="txt">
												<span>이미지파일을 등록해주세요.</span>
											</div>
											<input type="file" name="file" id="file" placeholder="ddd" onchange="layerFileTypeChk(this)">
											<input type="hidden" id="fileGroup" name="fileGroup" value="CUSTOMER/CUSTOMER_REQUEST">
											<input type="hidden" id="fileType" name="fileType" value="FILE">
											<input type="hidden" id="orderNum" name="orderNum" value="0">
											<input type="hidden" id="fileIdx" name="fileIdx" value="0">
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //table -->
					</div>
				</div>
			</div>
	
			<div class="btn_wrap cen">
				<button type="button" class="btn_app" disabled="disabled" onClick="addCustomerRequest('qnaPopForm');">문의하기</button>
			</div>
	
			<p class="b_txt">※관리자가 요청하는 최소한의 정보를 입력해주시면 문의하기가 활성화됩니다.</p>
		</div>
	</form>
</div>
<!-- //Layer popup 문의하기 -->

<!-- Layer popup A/S 요청하기 -->
<div class="layer_pop" id="as" style="display:none">
	<p class="t_txt">[ 우원이엔지 A/S 요청하기 ]</p>
	<button type="button" class="btn_closed"><span class="hide">닫기</span></button>
	<form name="asPopForm" id="asPopForm" action="/customer/addAs.do" method="post">
		<input type="hidden" name="requestType" id="requestType" value="A/S요청">
		<input type="hidden" name="privacyYn" id="privacyYn" value="N">
		<input type="hidden" name="idx" id="idx" value="0">
		<div class="layer_content">
			<div class="fl_wrap">
				<div class="fl_left">
					<div class="table_wrap">
						<!-- table -->
						<table border="1">
							<caption>A/S 요청하기</caption>
							<colgroup>
								<col style="width:85px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">· 성명<span class="imp">*</span></th>
									<td><input type="text" value="" id="name" title="" name="name" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 연락처<span class="imp">*</span></th>
									<td><input type="text" value="" id="tel" title="" name="tel" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 주소</th>
									<td><input type="text" value="" id="addr" title="" name="addr" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 개인정보</th>
									<td>
										<div class="box_info">
											<p>개인정보 항목 : 성명, 상호명, 연락처, 주소<br />수집 및 이용목적 : 제품안내 및 정보제공, 고객상담<br />보유 및 이용기간 : 개인정보 수집일로부터 개인정보 파기 요청시 까지</p>
										</div>
										<span class="chk"><input type="checkbox" id="check02" title="" onChange="changePrivacy(this);" /><label for="check02">개인정보 수집 및 이용에 동의합니다.</label></span>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //table -->
					</div>
				</div>
	
				<div class="fl_right">
					<div class="table_wrap">
						<!-- table -->
						<table border="1">
							<caption>A/S 요청하기</caption>
							<colgroup>
								<col style="width:85px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">· 상호명</th>
									<td><input type="text" value="" id="companyName" title="" name="companyName" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 구입일</th>
									<td>
										<div class="sel_wrap">
											<span class="select" style="width:166px">
												<label for="sel_01">년도선택</label>
												<select id="sel_01" name="buyYear">
													<option value="">년도선택</option>
													<option value="2017">2017년</option>
													<option value="2016">2016년</option>
													<option value="2015">2015년</option>
													<option value="2014">2014년</option>
													<option value="2013">2013년</option>
													<option value="2012">2012년</option>
													<option value="2011">2011년</option>
													<option value="2010">2010년</option>
												</select>
											</span>
											<span class="select" style="width:166px; margin-left:21px">
												<label for="sel_02">월선택</label>
												<select id="sel_02" name="buyMonth">
													<option value="">월선택</option>
													<option value="01">01월</option>
													<option value="02">02월</option>
													<option value="03">03월</option>
													<option value="04">04월</option>
													<option value="05">05월</option>
													<option value="06">06월</option>
													<option value="07">07월</option>
													<option value="08">08월</option>
													<option value="09">09월</option>
													<option value="10">10월</option>
													<option value="11">11월</option>
													<option value="12">12월</option>
												</select>
											</span>
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row">· 제품명</th>
									<td><input type="text" id="productName" title="" name="productName" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 내용</th>
									<td><textarea name="content" id="content" placeholder="내용을 입력해주세요." rows="" cols="" style="width:335px; height:40px"></textarea></td>
								</tr>
								<tr>
									<th scope="row">· 첨부파일</th>
									<td>
										<div class="b_file">
											<a href="javascript:" class="btn_file" onclick="$(this).nextAll('#file').click();">파일선택</a>
											<div class="txt">
												<span>이미지파일을 등록해주세요.</span>
											</div>
											<input type="file" name="file" id="file" placeholder="ddd" onchange="layerFileTypeChk(this)">
											<input type="hidden" id="fileGroup" name="fileGroup" value="CUSTOMER/CUSTOMER_REQUEST">
											<input type="hidden" id="fileType" name="fileType" value="FILE">
											<input type="hidden" id="orderNum" name="orderNum" value="0">
											<input type="hidden" id="fileIdx" name="fileIdx" value="0">
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //table -->
					</div>
				</div>
			</div>
	
			<div class="btn_wrap cen">
				<button type="button" class="btn_app" disabled="disabled" onClick="addCustomerRequest('asPopForm');">요청하기</button>
			</div>
	
			<p class="b_txt">※관리자가 요청하는 최소한의 정보를 입력해주시면 요청하기가 활성화됩니다.</p>
		</div>
	</form>
</div>
<!-- //Layer popup A/S 요청하기 -->

<!-- Layer popup 카탈로그 -->
<div class="layer_pop" id="catalog" style="display:none">
	<p class="t_txt">[ 우원이엔지 카탈로그 신청하기 ]</p>
	<button type="button" class="btn_closed"><span class="hide">닫기</span></button>
	<form name="catalogPopForm" id="catalogPopForm" action="/customer/addCatalog.do" method="post">
		<input type="hidden" name="requestType" id="requestType" value="카탈로그">
		<input type="hidden" name="privacyYn" id="privacyYn" value="N">
		<input type="hidden" name="email" id="email" value="">
		<input type="hidden" name="idx" id="idx" value="0">
		<input type="hidden" name="catalogName" id="catalogName" value="">
		<div class="layer_content">
			<div class="fl_wrap">
				<div class="fl_left">
					<div class="table_wrap">
						<!-- table -->
						<table border="1">
							<caption>문의하기</caption>
							<colgroup>
								<col style="width:85px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">· 성명<span class="imp">*</span></th>
									<td><input type="text" value="" id="name" title="" name="name" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 연락처<span class="imp">*</span></th>
									<td><input type="text" value="" id="tel" title="" name="tel" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 주소</th>
									<td><input type="text" value="" id="addr" title="" name="addr" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 개인정보</th>
									<td>
										<div class="box_info">
											<p>개인정보 항목 : 성명, 상호명, 연락처, 주소<br />수집 및 이용목적 : 제품안내 및 정보제공, 고객상담<br />보유 및 이용기간 : 개인정보 수집일로부터 개인정보 파기 요청시 까지</p>
										</div>
										<span class="chk"><input type="checkbox" id="check03" title="" name="" onChange="changePrivacy(this);" /><label for="check03">개인정보 수집 및 이용에 동의합니다.</label></span>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //table -->
					</div>
				</div>
	
				<div class="fl_right">
					<div class="table_wrap">
						<!-- table -->
						<table border="1">
							<caption>문의하기</caption>
							<colgroup>
								<col style="width:85px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">· 상호명</th>
									<td><input type="text" value="" id="companyName" title="" name="companyName" style="width:335px" /></td>
								</tr>
								<tr>
									<th scope="row">· 이메일</th>
									<td>
										<div class="mail_wrap">
											<input type="text" value="" id="email1" title="" name="email1" style="width:147px">
											<span class="fmr">@</span>
											<input type="text" value="" id="email2" title="" name="email2" style="width:147px">
										</div>
									</td>
								</tr>
								<tr>
									<th scope="row">· 카탈로그</th>
									<td>
										<span class="select" style="width:355px">
											<label for="sel_02">카탈로그 선택</label>
											<select id="sel_02" name="catalog" onChange="$('#catalogName').val($(this).find('option:selected').html());">
												<option value="0">카탈로그 선택</option>
												<c:if test="${!empty catalogCateList}">
													<c:forEach items="${catalogCateList}" var="item">
														<option value="${item.idx}">${item.cName}</option>	
													</c:forEach>
												</c:if>
											</select>
										</span>
									</td>
								</tr>
								<tr>
									<th scope="row">· 내용</th>
									<td><textarea name="content" id="content" placeholder="내용을 입력해주세요." rows="" cols="" style="width:335px; height:40px"></textarea></td>
								</tr>
								<tr>
									<th scope="row">· 첨부파일</th>
									<td>
										<div class="b_file">
											<a href="javascript:" class="btn_file" onclick="$(this).nextAll('#file').click();">파일선택</a>
											<div class="txt">
												<span>이미지파일을 등록해주세요.</span>
											</div>
											<input type="file" name="file" id="file" placeholder="ddd" onchange="layerFileTypeChk(this)">
											<input type="hidden" id="fileGroup" name="fileGroup" value="CUSTOMER/CUSTOMER_REQUEST">
											<input type="hidden" id="fileType" name="fileType" value="FILE">
											<input type="hidden" id="orderNum" name="orderNum" value="0">
											<input type="hidden" id="fileIdx" name="fileIdx" value="0">
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- //table -->
					</div>
				</div>
			</div>
	
			<div class="btn_wrap cen">
				<button type="button" class="btn_app" disabled="disabled" onClick="addCustomerRequest('catalogPopForm');">신청하기</button>
			</div>
	
			<p class="b_txt">※관리자가 요청하는 최소한의 정보를 입력해주시면 신청하기가 활성화됩니다.</p>
		</div>
	</form>
</div>
<!-- //Layer popup 카탈로그 -->

<!-- Layer popup 클린사업 -->
<div class="layer_pop busi" id="busi01" style="display:none">
	<p class="t_txt">한국산업안전보건공단 클린사업장 지원사업 소개</p>
	<button type="button" class="btn_closed"><span class="hide">닫기</span></button>
	<div class="layer_content">
		<div class="slide_wrap">
			<ul class="slide_busi01">
				<li><img src="/images/content/img_busi01_01.png" alt=""></li>
				<li><img src="/images/content/img_busi01_02.png" alt=""></li>
			</ul>
		</div>
	</div>
</div>
<!-- //Layer popup 클린사업 -->

<!-- Layer popup 정부조달사업 -->
<div class="layer_pop busi" id="busi02" style="display:none">
	<p class="t_txt">나라장터를 이용한 정부조달 구매 방법</p>
	<button type="button" class="btn_closed"><span class="hide">닫기</span></button>
	<div class="layer_content">
		<div class="slide_wrap">
			<ul class="slide_busi02">
				<li><img src="/images/content/img_busi02_01.png" alt=""></li>
				<li><img src="/images/content/img_busi02_02.png" alt=""></li>
				<li><img src="/images/content/img_busi02_03.png" alt=""></li>
				<li><img src="/images/content/img_busi02_04.png" alt=""></li>
				<li><img src="/images/content/img_busi02_05.png" alt=""></li>
			</ul>
		</div>
	</div>
</div>
<!-- //Layer popup 정부조달사업 -->
<div class="layer_dim" style="width:100% !important"></div>
