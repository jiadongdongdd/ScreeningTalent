/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.idsmanager.xsifter.service.dto.menber;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.xsifter.domain.member.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 2016/1/30
 *
 * @author Shengzhao Li
 */
public class WorkedFormDto implements Serializable {
    private static final long serialVersionUID = 4257767892669337449L;


    private String memberUuid;

    private String companyGuid;

    //嘉奖
    private WorkedAwards awards;
    //嘉奖备注
    private String awardsRemark;


    // 处分
    private WorkPunished punished;
    // 处分备注
    private String punishedRemark;

	//工作期间是否有职位变化
	private boolean workChange;
	
	//变化时间
	private String changeDateOne;
	private String changeDateTwo;
	private String changeDateThree;
	
	//变化职位
	private String changeJobOne;
	private String changeJobTwo;
	private String changeJobThree;


    private boolean finished;


    public WorkedFormDto() {
    }

    public WorkedFormDto(Worked worked) {
        this.memberUuid = worked.getMemberUuid();
        this.companyGuid = worked.getCompanyGuid();
        this.awards = worked.getAwards();

        this.awardsRemark = worked.getAwardsRemark();
        this.punished = worked.getPunished();
        this.punishedRemark = worked.getPunishedRemark();
        this.workChange = worked.isWorkChange();
        this.changeDateOne = DateUtils.toDate(worked.getChangeDateOne());
        this.changeDateTwo = DateUtils.toDate(worked.getChangeDateTwo());
        this.changeDateThree = DateUtils.toDate(worked.getChangeDateThree());
        this.changeJobOne = worked.getChangeJobOne();
        this.changeJobTwo =worked.getChangeJobTwo();
        this.changeJobThree = worked.getChangeJobThree();
    }

    public WorkedFormDto(String memberGuid) {
        this.memberUuid = memberGuid;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public MonthFixedIncome[] getMonthFixedIncomes() {
        return MonthFixedIncome.values();
    }

    public MonthFloatIncome[] getMonthFloatIncomes() {
        return MonthFloatIncome.values();
    }


    public WorkPunished[] getWorkPunishedList() {
        return WorkPunished.values();
    }

    public WorkedAwards[] getWorkedAwards() {
        return WorkedAwards.values();
    }

    public String getMemberUuid() {
        return memberUuid;
    }

    public void setMemberUuid(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public String getCompanyGuid() {
        return companyGuid;
    }

    public void setCompanyGuid(String companyGuid) {
        this.companyGuid = companyGuid;
    }

    public WorkedAwards getAwards() {
        return awards;
    }

    public void setAwards(WorkedAwards awards) {
        this.awards = awards;
    }

    public String getAwardsRemark() {
        return awardsRemark;
    }

    public void setAwardsRemark(String awardsRemark) {
        this.awardsRemark = awardsRemark;
    }

    public WorkPunished getPunished() {
        return punished;
    }

    public void setPunished(WorkPunished punished) {
        this.punished = punished;
    }

    public String getPunishedRemark() {
        return punishedRemark;
    }

    public void setPunishedRemark(String punishedRemark) {
        this.punishedRemark = punishedRemark;
    }

    public Worked updateFields(Worked worked) {
        return worked.setAwards(this.awards)
                .setAwardsRemark(this.awardsRemark)
                .setPunished(this.punished)
                .setPunishedRemark(this.punishedRemark)
                .setWorkChange(this.workChange)
                .setChangeDateOne(DateUtils.getDate(this.changeDateOne))
                .setChangeDateTwo(DateUtils.getDate(this.changeDateTwo))
                .setChangeDateThree(DateUtils.getDate(this.changeDateThree))
                .setChangeJobOne(this.changeJobOne)
                .setChangeJobTwo(this.changeJobTwo)
                .setChangeJobThree(this.changeJobThree);
                

    }

	public boolean isWorkChange() {
		return workChange;
	}

	public void setWorkChange(boolean workChange) {
		this.workChange = workChange;
	}


	public String getChangeJobOne() {
		return changeJobOne;
	}

	public void setChangeJobOne(String changeJobOne) {
		this.changeJobOne = changeJobOne;
	}

	public String getChangeJobTwo() {
		return changeJobTwo;
	}

	public void setChangeJobTwo(String changeJobTwo) {
		this.changeJobTwo = changeJobTwo;
	}

	public String getChangeJobThree() {
		return changeJobThree;
	}

	public void setChangeJobThree(String changeJobThree) {
		this.changeJobThree = changeJobThree;
	}

	public String getChangeDateOne() {
		return changeDateOne;
	}

	public void setChangeDateOne(String changeDateOne) {
		this.changeDateOne = changeDateOne;
	}

	public String getChangeDateTwo() {
		return changeDateTwo;
	}

	public void setChangeDateTwo(String changeDateTwo) {
		this.changeDateTwo = changeDateTwo;
	}

	public String getChangeDateThree() {
		return changeDateThree;
	}

	public void setChangeDateThree(String changeDateThree) {
		this.changeDateThree = changeDateThree;
	}
}
