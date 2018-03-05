package com.idsmanager.xsifter.domain.log;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.idsmanager.xsifter.domain.AbstractDomain;

@Document(collection = "Log")
public class Log extends AbstractDomain {

	private static final long serialVersionUID = 8261483536849888752L;

	private String operatorId;// 操作Id
	private String operator;// 操作人
	private String entityClass;// 实体类型
	private Object entity;// 实体
	private String entityUuid;// 实体标识
	private String entityName;// 实体显示的名称
	private List<String> operateField;// 操作属性
	private OperateType operateType;// 操作类型
	private String operateDetail;// 操作详情

	public Log() {
		super();
	}

	public String getOperatorId() {
		return operatorId;
	}

	public Log setOperatorId(String operatorId) {
		this.operatorId = operatorId;
		return this;
	}

	public String getOperator() {
		return operator;
	}

	public Log setOperator(String operator) {
		this.operator = operator;
		return this;

	}

	public OperateType getOperateType() {
		return operateType;
	}

	public Log setOperateType(OperateType operateType) {
		this.operateType = operateType;
		return this;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public Log setEntityClass(String entityClass) {
		this.entityClass = entityClass;
		return this;
	}

	public List<String> getOperateField() {
		return operateField;
	}

	public Log setOperateField(List<String> operateField) {
		this.operateField = operateField;
		return this;
	}

	public String getOperateDetail() {
		return operateDetail;
	}

	public Log setOperateDetail(String operateDetail) {
		this.operateDetail = operateDetail;
		return this;
	}

	public Object getEntity() {
		return entity;
	}

	public Log setEntity(Object entity) {
		this.entity = entity;
		return this;
	}

	public String getEntityUuid() {
		return entityUuid;
	}

	public Log setEntityUuid(String entityUuid) {
		this.entityUuid = entityUuid;
		return this;
	}

	public String getEntityName() {
		return entityName;
	}

	public Log setEntityName(String entityName) {
		this.entityName = entityName;
		return this;
	}

	@Override
	public String toString() {
		return "Log [operatorId=" + operatorId + ", operator=" + operator
				+ ", entityClass=" + entityClass + ", entity=" + entity
				+ ", entityUuid=" + entityUuid + ", entityName=" + entityName
				+ ", operateField=" + operateField + ", operateType="
				+ operateType + ", operateDetail=" + operateDetail + "]";
	}

}
