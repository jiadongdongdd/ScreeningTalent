package com.idsmanager.xsifter.domain.wx.order;

import java.util.List;
import java.util.Map;

import com.idsmanager.xsifter.domain.Repository;

public interface WxOrderRepository extends Repository {

	WxOrder findOrderByOrderNo(String orderNo);

	void saveWxOrder(WxOrder order);

	long totalWxOrderList(Map<String, Object> map);

	List<WxOrder> findOrderList(Map<String, Object> map);

}
