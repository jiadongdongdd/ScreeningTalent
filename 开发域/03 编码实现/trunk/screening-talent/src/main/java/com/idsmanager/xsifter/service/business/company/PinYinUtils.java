package com.idsmanager.xsifter.service.business.company;

import net.sourceforge.pinyin4j.PinyinHelper;

public class PinYinUtils {
	/** 
     * 提取每个汉字的首字母 
     *  
     * @param str 
     * @return String 
     */  
    public static String getPinYinHeadChar(String str) {  
        String convert = "";  
        for (int j = 0; j < str.length(); j++) {  
            char word = str.charAt(j);  
            // 提取汉字的首字母  
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);  
            if (pinyinArray != null) {  
                convert += pinyinArray[0].charAt(0);  
            } else {  
                convert += word;  
            }  
        }  
        return convert;  
    } 
}
