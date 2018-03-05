package com.idsmanager.xsifter.domain.member;

/**
 * 政治面貌
 * @author thinkpad
 *
 */
public enum PoliticalStatus {
	MASSES("群众"),
	LEAGUE_MEMBER("团员"),
	PARTY_MEMBER("党员");
	
	private String label;
	
	PoliticalStatus(String label) {
		this.label = label;
	}
	
	
	public String getLabel() {
        return label;
    }

}
