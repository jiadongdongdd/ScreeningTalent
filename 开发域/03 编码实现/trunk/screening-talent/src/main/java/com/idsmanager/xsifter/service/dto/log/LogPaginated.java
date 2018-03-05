package com.idsmanager.xsifter.service.dto.log;

import java.util.Map;

import com.idsmanager.commons.utils.paginated.DefaultPaginated;
import com.idsmanager.xsifter.domain.log.Log;

public class LogPaginated extends DefaultPaginated<Log> {

	private String operateDetail;// 操作详情
	private String operator;// 操作人
	private String start;// 操作起始时间
	private String end;// 操作截止时间

	private String createTime;// 操作时间
	private String chName;// 职工姓名
	private String companyName;// 公司名称

	@Override
	public Map<String, Object> queryMap() {
		final Map<String, Object> map = super.queryMap();
		map.put("operateDetail", operateDetail);
		map.put("operator", operator);
		map.put("start", start);
		map.put("end", end);
		map.put("createTime", createTime);
		map.put("chName", chName);
		map.put("companyName", companyName);
		return map;
	}

	public String getOperateDetail() {
		return operateDetail;
	}

	public void setOperateDetail(String operateDetail) {
		this.operateDetail = operateDetail;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getChName() {
		return chName;
	}

	public void setChName(String chName) {
		this.chName = chName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}
