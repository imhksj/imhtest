package com.woowoneng.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woowoneng.common.service.MailService;
import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.entity.Orders;
import com.woowoneng.service.OrdersService;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : OrdersController.java
 * 기        능 : 주문하기 Controller
 * -----------------------------------------------------------------------------
 *                              변경 사항				                     
 * -----------------------------------------------------------------------------
 *    변경일자       	                             변경자(작성자)                 	  변경 내역                 
 * -------------     	--------------------------       ------------------------
 * 2016. 12. 28                		임명호                                          최 초 작 성                          
 * ==============================================================================
 * 
 * @author MyungHo Lim
 *
 */

@Controller
@RequestMapping({ "/order/" })
public class OrdersController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private MailService mailService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * 상품 > 주문 > 등록
	 * @param Orders
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addOrder.do")
	public ModelAndView addOrder(@Valid Orders orders, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "주문되었습니다.";

        if (bindingResult.hasErrors()) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
        if(req.getSession().getAttribute("member") != null){
        	orders.setMemberIdx((int) req.getSession().getAttribute("memberIdx"));
        }else{
        	view.addObject("RETURN_CODE", "0003");
			view.addObject("RETURN_MSG", "로그인 후 주문가능합니다.");
			return view;     
        }
        
		int cnt = ordersService.addOrder(orders);
		
		if(cnt <= 0){
			RETURN_CODE = "0002";
			RETURN_MSG = "주문에 실패하였습니다.";
		}
		
		Map<String, Object> commonMap = new HashMap<String, Object>();
		commonMap.put("subject", param.getString("productName") + "주문이 들어왔습니다.");
		
		String content = "담당자 : " + orders.getTptb() + "<br><br>" +
						 "주문수랑 : " + orders.getAmount() + "개<br><br>" +
						 "배송주소 : " + orders.getDeliverAddr() + "<br><br>" +
						 "요청사항 : " + orders.getComment().replaceAll("\n", "<br>");
		
		commonMap.put("mailContent", content);
		
		mailService.sendMail(commonMap);
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
}
