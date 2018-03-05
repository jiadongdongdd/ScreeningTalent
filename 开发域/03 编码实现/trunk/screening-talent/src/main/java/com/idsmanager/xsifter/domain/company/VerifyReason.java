package com.idsmanager.xsifter.domain.company;

public enum VerifyReason {
	VERIFYFALSE("未通过审核"),
	CONTACTSFALSE("联系人姓名虚假"),
	UPLOADFILEFALSE("上传的证件虚假"),
	PHONEFALSE("手机不合法");

	private String label;

	VerifyReason(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

}
