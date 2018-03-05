package com.idsmanager.xsifter.service;

import com.idsmanager.xsifter.service.dto.wx.order.WxOrderPaginated;

public interface WxOrderService {

	String createOrder(String uuid);

	WxOrderPaginated loadMyWxOrderList(WxOrderPaginated paginated);

}
