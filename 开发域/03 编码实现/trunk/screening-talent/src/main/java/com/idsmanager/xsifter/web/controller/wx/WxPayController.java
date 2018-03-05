package com.idsmanager.xsifter.web.controller.wx;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idsmanager.commons.utils.wx.pay.WxSignatureUtil;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryRequestParams;
import com.idsmanager.xsifter.domain.wx.pay.OrderQueryResponseParams;
import com.idsmanager.xsifter.service.WxPayService;
import com.idsmanager.xsifter.service.dto.wx.pay.ProductFormDto;

@Controller
@RequestMapping("/public/wx/")
public class WxPayController {

	@Autowired
	private WxPayService wxPayService;

	/**
	 * 支付回调URL
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "callback", method = RequestMethod.POST)
	@ResponseBody
	public String callBack(HttpServletRequest request) {
		String xml = null;
		try {
			xml = wxPayService.nativePayReplyXML(request);
			return xml;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xml;
	}

	/**
	 * 异步回调地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "callback_notify", method = RequestMethod.POST)
	@ResponseBody
	public String callBackNotify(HttpServletRequest request) {
		String xml = null;
		try {
			xml = wxPayService.callBackNotifyReplyXML(request);
			// 改变订单状态
		} catch (IOException e) {
			e.printStackTrace();
		}

		return xml;

	}

	/**
	 * 订单查询
	 * 
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = "orderQuery", method = RequestMethod.GET)
	public String orderQuery(Model model) {
		OrderQueryRequestParams requestParams = new OrderQueryRequestParams();
		requestParams.setAppid("wxffa009d7df70383c");
		requestParams.setMch_id("1313578201");
		requestParams.setOut_trade_no("e3937467382247959f94c0786ffa02c4");

		OrderQueryResponseParams orderQuery;

		try {
			orderQuery = wxPayService.orderQuery(requestParams);
			System.out.println("订单查询结果：" + orderQuery);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ProductFormDto formDto = new ProductFormDto();
		model.addAttribute("formDto", formDto);

		return "product/product_form";

	}*/

}
