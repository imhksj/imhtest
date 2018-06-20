package com.woowoneng.admin.controller;

import java.util.List;

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

import com.woowoneng.common.utils.ParamUtil;
import com.woowoneng.common.utils.StringUtil;
import com.woowoneng.entity.Orders;
import com.woowoneng.admin.service.AdminOrdersService;
import com.woowoneng.common.utils.CommonUtil;
import com.woowoneng.common.utils.PagedList;

/*
 * =============================================================================
 * 프로젝트명 : woowoneng
 * 화   일  명 : AdminOrderController.java
 * 기        능 : 어드민 주문관리 Controller
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
@RequestMapping({ "/admin/order/" })
public class AdminOrdersController {

	private Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private AdminOrdersService adminOrdersService;
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 주문관리 > 리스트
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "order_list.do")
	public ModelAndView ordersList(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		int reqPage = param.getInt("reqPage", 1);	//현재 페이지
		int pageSize = 25;							//페이지당 뿌려질 리스트 갯수
		
		PagedList<Orders> ordersList = adminOrdersService.getSelectList(param, reqPage, pageSize);
		
		view = ordersList.setPageModel(view, ordersList, pageSize);
		
		view.addObject("ordersList", ordersList);
		
		view.setViewName("admin/order/order_list");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 주문관리 > 상세
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "order_detail.do")
	public ModelAndView ordersDetail(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);
		
		if(param.getInt("idx") > 0){

			Orders order = adminOrdersService.getSelect(param);
			view.addObject("order", order);
			
		}
		
		view.setViewName("admin/order/order_detail");
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 주문관리 > 수정
	 * @param Orders
	 * @param BindingResult
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "modiOrder.do")
	public ModelAndView modiOrder(@Valid Orders orders, BindingResult bindingResult, HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		String RETURN_CODE = "0000";
		String RETURN_MSG = "수정되었습니다.";

        if (bindingResult.hasErrors() || orders.getIdx() <= 0) {
        	view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "필수 입력 항목 중 누락된 항목이 있습니다.");
			return view;            
        }
		
        orders.setModiAdminIdx((int) req.getSession().getAttribute("adminIdx"));
        
		int cnt = adminOrdersService.modiOrder(orders);
		
		if(cnt <= 0){
			RETURN_CODE = "0002";
			RETURN_MSG = "수정에 실패하였습니다.";
		}
		
		view.addObject("RETURN_CODE", RETURN_CODE);
		view.addObject("RETURN_MSG", RETURN_MSG);
		
		return view;
		
	}
	
	/*
	 * @author MyungHo Lim
	 * 
	 * ADMIN > 주문관리 > 처리변경
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateProcType.do")
	public ModelAndView updateProcType(HttpServletRequest req, HttpServletResponse rep) throws Exception{
		
		ModelAndView view = new ModelAndView();
		view.setViewName("jsonView");
		
		ParamUtil<String, Object> param = new ParamUtil<String, Object>(req);

		if(param.getInt("idx") <= 0 || "".equals(param.getString("procType"))){

			view.addObject("RETURN_CODE", "0001");
			view.addObject("RETURN_MSG", "잘못된 데이타입니다.");
			return view;
		}
		
		param.put("modiAdminIdx", (int) req.getSession().getAttribute("adminIdx"));
		
		int cnt = adminOrdersService.updateProcType(param);
		
		if(cnt > 0){
			view.addObject("RETURN_CODE", "0000");
			view.addObject("RETURN_MSG", "상태를 변경하였습니다.");
		}else{
			view.addObject("RETURN_CODE", "0002");
			view.addObject("RETURN_MSG", "상태를 변경에 실패하였습니다.");
		}
		
		return view;
		
	}
	
}
