package com.idsmanager.xsifter.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idsmanager.commons.utils.paginated.PaginatedLoader;
import com.idsmanager.xsifter.domain.security.SecurityUtils;
import com.idsmanager.xsifter.domain.user.Privilege;
import com.idsmanager.xsifter.domain.user.User;
import com.idsmanager.xsifter.domain.user.UserRepository;
import com.idsmanager.xsifter.domain.wx.order.WxOrder;
import com.idsmanager.xsifter.domain.wx.order.WxOrderRepository;
import com.idsmanager.xsifter.service.WxOrderService;
import com.idsmanager.xsifter.service.business.wx.MyWxOrderCreater;
import com.idsmanager.xsifter.service.dto.wx.order.WxOrderPaginated;

@Service("wxOrderService")
public class WxOrderServiceImpl implements WxOrderService {

	@Autowired
	private WxOrderRepository wxOrderRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public String createOrder(String uuid) {
		MyWxOrderCreater creater = new MyWxOrderCreater(uuid);
		return creater.create();
	}

	@Override
	public WxOrderPaginated loadMyWxOrderList(WxOrderPaginated paginated) {
		final Map<String, Object> map = paginated.queryMap();
		User user = userRepository.findByGuid(SecurityUtils.currentUserGuid());
		if (user != null) {
			map.put("role", Privilege.ADMIN);
		}
		return paginated.load(new PaginatedLoader<WxOrder>() {

			@Override
			public long loadTotalSize() {
				return wxOrderRepository.totalWxOrderList(map);
			}

			@Override
			public List<WxOrder> loadList() {
				return wxOrderRepository.findOrderList(map);
			}
		});
	}

}
