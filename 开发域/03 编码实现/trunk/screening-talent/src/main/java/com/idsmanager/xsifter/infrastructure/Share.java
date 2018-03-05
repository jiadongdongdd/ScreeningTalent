package com.idsmanager.xsifter.infrastructure;
/**
 * 分享类
 *类名称
 *创建人 dushaofei
 *创建时间：2016-2-15 下午2:19:11 
 *类描述：
 *@version
 */
public class Share {
	//qzone
	private static String qzoneUrl;
	
	private static String qzoneText;
	
	private static String qzoneProjectLink;
	
	private static String qzoneImgLink;
	
	//sina
	private static String sinaUrl;
	
	private static String sinaText;
	
	private static String sinaProjectLink;
	
	private static String sinaImgLink;
	
	public Share() {
		
	}

	public static String getQzoneText() {
		return qzoneText;
	}

	public void setQzoneText(String qzoneText) {
		Share.qzoneText = qzoneText;
	}

	public static String getQzoneProjectLink() {
		return qzoneProjectLink;
	}

	public void setQzoneProjectLink(String qzoneProjectLink) {
		Share.qzoneProjectLink = qzoneProjectLink;
	}

	public static String getQzoneImgLink() {
		return qzoneImgLink;
	}

	public void setQzoneImgLink(String qzoneImgLink) {
		Share.qzoneImgLink = qzoneImgLink;
	}

	public static String getSinaText() {
		return sinaText;
	}

	public void setSinaText(String sinaText) {
		Share.sinaText = sinaText;
	}

	public static String getSinaProjectLink() {
		return sinaProjectLink;
	}

	public void setSinaProjectLink(String sinaProjectLink) {
		Share.sinaProjectLink = sinaProjectLink;
	}

	public static String getSinaImgLink() {
		return sinaImgLink;
	}

	public void setSinaImgLink(String sinaImgLink) {
		Share.sinaImgLink = sinaImgLink;
	}

	public static String getQzoneUrl() {
		qzoneUrl += "?summary="+qzoneText + "&url="+qzoneProjectLink + "&pics="+qzoneImgLink;
		return qzoneUrl;
	}

	public void setQzoneUrl(String qzoneUrl) {
		Share.qzoneUrl = qzoneUrl;
	}

	public static String getSinaUrl() {
		sinaUrl += "?title="+sinaText + "&url=" + sinaProjectLink + "&pic="+sinaImgLink;
		return sinaUrl;
	}

	public  void setSinaUrl(String sinaUrl) {
		Share.sinaUrl = sinaUrl;
	}
	
}
