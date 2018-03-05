package com.idsmanager.xsifter.web.controller.enterprise;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.WriterException;
import com.idsmanager.xsifter.service.WxOrderService;
import com.idsmanager.xsifter.service.WxPayService;

@Controller
@RequestMapping("/enterprise/wx/")
public class EnterpriseWxPayController {

	@Autowired
	private WxPayService wxPayService;

	@Autowired
	private WxOrderService wxOrderService;

	/**
	 * 生成支付二维码
	 * 
	 * @param response
	 */
	@RequestMapping(value = "payQR/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public void getQR(@PathVariable("uuid") String uuid,
			HttpServletResponse response) {
		try {
			String orderNo = wxOrderService.createOrder(uuid);
			wxPayService.getQrCode(response, orderNo);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
