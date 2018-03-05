package com.idsmanager.xsifter.domain.member;
/**
 * 
 *类名称
 *创建人 dushaofei
 *创建时间：2016-2-25 下午2:45:18 
 *类描述：
 *@version
 */
public enum MemberProcess {

    NORMAL("正常"),
    DATA_REPEAT("数据重复");
    private String label;

    MemberProcess(String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return label;
    }

}
