package com.idsmanager.xsifter.service.dto.menber;

import java.util.List;

import com.idsmanager.xsifter.service.dto.AbstractDto;

/*
 * 错误数据信息
 */
public class ErrorDataDto extends AbstractDto {

	private static final long serialVersionUID = 4154622350546375520L;

	private int sheet;// 工作区
	private int row;// 行
	private List<Integer> column;// 列
	private String value;// 错误值

	public ErrorDataDto() {
		super();
	}

	public int getSheet() {
		return sheet;
	}

	public void setSheet(int sheet) {
		this.sheet = sheet;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public List<Integer> getColumn() {
		return column;
	}

	public void setColumn(List<Integer> column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
