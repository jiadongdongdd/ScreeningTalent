package com.idsmanager.xsifter.service.business.wx;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.idsmanager.commons.utils.wx.pay.ErrorUtil;
import com.idsmanager.commons.utils.wx.pay.WxPayUtil;
import com.idsmanager.commons.utils.wx.pay.WxSignatureUtil;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.wx.order.WxOrder;
import com.idsmanager.xsifter.domain.wx.order.WxOrderRepository;
import com.idsmanager.xsifter.domain.wx.pay.Merchant;
import com.idsmanager.xsifter.domain.wx.pay.MerchantRepository;
import com.idsmanager.xsifter.domain.wx.pay.WxPayCallBackInput;
import com.idsmanager.xsifter.domain.wx.pay.WxPayPackage;
import com.idsmanager.xsifter.domain.wx.product.Product;
import com.idsmanager.xsifter.domain.wx.product.ProductRepository;

public class MyWxCallBackHandler {

	private static final Logger LOG = LoggerFactory
			.getLogger(MyWxCallBackHandler.class);

	private transient MerchantRepository merchantRepository = BeanProvider
			.getBean(MerchantRepository.class);

	private transient ProductRepository productRepository = BeanProvider
			.getBean(ProductRepository.class);

	private transient WxOrderRepository wxOrderRepository = BeanProvider
			.getBean(WxOrderRepository.class);

	private HttpServletRequest request;

	public MyWxCallBackHandler(HttpServletRequest request) {
		super();
		this.request = request;
	}

	public String handle() throws IOException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException,
			NoSuchAlgorithmException {

		String result = "";

		List<Merchant> list = this.merchantRepository.findMerchantList();

		if (list == null || list.size() == 0) {
			LOG.debug("系统未设置商户数据");
			throw new IllegalStateException("系统未设置商户数据");
		}

		Merchant merchant = list.get(0);
		// 获取参数
		WxPayCallBackInput input = WxPayUtil.convertRequest(request
				.getInputStream());
		System.out.println("微信支付回调输入参数：" + input);
		LOG.debug("Get WXPay CallBack Input Param{}", input);
		// 验证签名
		boolean validate = WxSignatureUtil.validateSignature(input, merchant);

		// 返回参数
		if (!validate) {
			result = ErrorUtil.validateSignFail();
			System.out.println("验证签名失败");
			LOG.debug("Validate Sign Result:Fail");
			return result;
		}
		System.out.println("验证签名成功");
		LOG.debug("Validate Sign Result:Success");

		// 获取订单信息
		WxOrder order = this.wxOrderRepository.findOrderByOrderNo(input
				.getProduct_id());

		// 获取商品信息
		Product product = this.productRepository.findProductByUUID(order
				.getProductId());

		// 统一下单获取预支付ID
		WxPayPackage payPackage = WxPayUtil.getPayPackage(input, product,
				merchant);
		System.out.println("统一下单package：" + payPackage);
		LOG.debug("WxPay package {}", payPackage);
		String prePayId = WxPayUtil.getPrePayId(payPackage, merchant);
		System.out.println("预交易prepay_id：" + prePayId);
		LOG.debug("prepay_id{}", prePayId);

		// 返回参数
		if ("".equals(prePayId)) {
			result = ErrorUtil.getPrePayIdFail();
			System.out.println("预交易prepay_id失败");
			LOG.debug("Get prepay_id Fail");
			return result;
		}

		result = WxPayUtil.getSuccessXml(input, prePayId, merchant);
		System.out.println("返回参数：" + result);
		LOG.debug("Return param to WXPay{}", result);

		return result;

	}

}
