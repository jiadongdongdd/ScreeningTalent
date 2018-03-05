package com.idsmanager.xsifter.service.dto.position;

import com.idsmanager.xsifter.domain.position.MemberPosition;
import com.idsmanager.xsifter.service.dto.AbstractDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by LZW on 2016/9/20.
 */
public class PositionDto extends AbstractDto {
    private static final long serialVersionUID = -13462539500081774L;

    private String positionName;// 职位名称
    private String positionTypeUuid;// 职位行业uuid
    private String industry;// 职位行业
    private String level;// 职位级别

    public PositionDto() {
    }

    public PositionDto(MemberPosition memberPosition) {
        super(memberPosition);
        this.positionName = memberPosition.getPositionName();
        this.positionTypeUuid = memberPosition.getPositionTypeUuid();
        this.industry = memberPosition.getIndustry();
        this.level = memberPosition.getLevel();
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionTypeUuid() {
        return positionTypeUuid;
    }

    public void setPositionTypeUuid(String positionTypeUuid) {
        this.positionTypeUuid = positionTypeUuid;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public static List<PositionDto> toDtos(List<MemberPosition> list) {
        List<PositionDto> dtos = new ArrayList<>(list.size());
        dtos.addAll(list.stream().map(PositionDto::new).collect(Collectors.toList()));
        return dtos;
    }
}
