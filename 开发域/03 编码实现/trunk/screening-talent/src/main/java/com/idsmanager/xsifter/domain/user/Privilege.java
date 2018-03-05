package com.idsmanager.xsifter.domain.user;

import java.util.Arrays;
import java.util.List;



public enum Privilege {


    ENTERPRISE("Enterprise"),
    ENTERPRISEADMIN("EnterpriseAdmin"),
    ADMIN("Admin");


    private String label;

    Privilege(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getValue() {
        return name();
    }
    public static List<Privilege> availablePrivileges() {
        return Arrays.asList(
        		ENTERPRISE,
        		ENTERPRISEADMIN,
        		ADMIN
        );
    }

}
