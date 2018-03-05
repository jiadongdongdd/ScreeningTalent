package com.idsmanager.commons.utils.wx.pay;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class WxQrCodeUtil {
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;

	public static void encodeQRCode(String content, HttpServletResponse response)
			throws WriterException, IOException {
		if (StringUtils.isEmpty(content)) {
			return;
		}
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map<EncodeHintType, String> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		BitMatrix bitMatrix = null;

		bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE,
				300, 300, hints);
		BufferedImage image = toBufferedImage(bitMatrix);
		ImageIO.write(image, "png", response.getOutputStream());
	}

	/**
	 * 生成二维码内容
	 * 
	 * @param matrix
	 * @return 136
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
			}
		}
		return image;
	}
}
