package com.idsmanager.xsifter.web.validation;

import java.util.Date;

import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.utils.MatchUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.idsmanager.xsifter.domain.member.TelIntentionPosition;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.service.MemberService;
import com.idsmanager.xsifter.service.dto.menber.RecruitmentFormDto;

@Component
public class RecruitmentFormDtoValidator implements Validator {

	@Autowired
	private MemberService memberService;

	@Override
	public boolean supports(Class<?> clazz) {
		return RecruitmentFormDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		RecruitmentFormDto formDto = (RecruitmentFormDto) target;

		validateTime(formDto, errors);
		validatePosition(formDto, errors);

		if (formDto.getTelIntention() != null
				&& formDto.getTelIntention().isEnabled()) {
			final String invitationTime = formDto.getInvitationTime();
			if (!MatchUtils.isDate(invitationTime)) {
				errors.rejectValue("invitationTime", null, "面试时间不能为空");
				return;
			}

			if (!MatchUtils.isDate(invitationTime)) {
				errors.rejectValue("invitationTime", null, "面试时间格式错误");
			} else {
				final String telInvitationTime = formDto.getTelInvitationTime();
				long inviteTime = DateUtils.getDate(invitationTime,
						"yyyy-MM-dd").getTime();
				if (MatchUtils.isDate(telInvitationTime)) {
					long telTime = DateUtils.getDate(telInvitationTime,
							"yyyy-MM-dd").getTime();
					if (inviteTime < telTime) {
						errors.rejectValue("invitationTime", null,
								"面试时间应大于电话邀约时间");
					}
				}
			}

			validateIntentionScore(formDto, errors);

		}

		validateEntryDate(formDto, errors);

	}

	private void validateIntentionScore(RecruitmentFormDto formDto,
			Errors errors) {
		boolean joinInterview = formDto.isJoinInterview();
		if (joinInterview) {
			String score = formDto.getIntentionScore();
			if (StringUtils.isNotEmpty(score)) {
				try {
					Integer.valueOf(score);
				} catch (NumberFormatException e) {
					errors.rejectValue("intentionScore", null, "面试分数格式不正确");
				}
			}
		}
	}

	private void validateEntryDate(RecruitmentFormDto formDto, Errors errors) {

		if (formDto.getTelIntention() != null
				&& formDto.getTelIntention().isEnabled()
				&& formDto.isJoinInterview() && formDto.getAgreeEntry() != null
				&& formDto.getAgreeEntry().isEnabled()
				&& formDto.getEntrySuccess() != null
				&& formDto.getEntrySuccess().isEnabled()) {

			final String entryDate = formDto.getEntryDate();
			if (StringUtils.isEmpty(entryDate)) {
				errors.rejectValue("entryDate", null, "入职时间不能为空");
				return;
			}

			if (!MatchUtils.isDate(entryDate)) {
				errors.rejectValue("entryDate", null, "入职时间格式错误");
			} else {
				long entryTime = DateUtils.getDate(entryDate, "yyyy-MM-dd")
						.getTime();
				Turnover turnover = this.memberService
						.findTurnoverByUuid(formDto.getMemberUuid());
				if (turnover != null) {
					if (turnover.getTurnoverDate() != null) {
						long turnTime = turnover.getTurnoverDate().getTime();
						if (turnTime < entryTime) {
							errors.rejectValue("entryDate", null, "入职时间应小于离职时间");
						}
					}
				}

				String invitationTime = formDto.getInvitationTime();
				if (MatchUtils.isDate(invitationTime)) {
					long inviTime = DateUtils.getDate(invitationTime,
							"yyyy-MM-dd").getTime();
					if (entryTime < inviTime) {
						errors.rejectValue("entryDate", null, "入职时间应大于面试时间");
					}
				}

			}

		}

	}

	public void validateTime(RecruitmentFormDto formDto, Errors errors) {
		final String telInvitationTime = formDto.getTelInvitationTime();
		if (StringUtils.isEmpty(telInvitationTime)) {
			errors.rejectValue("telInvitationTime", null, "电话邀约时间不能为空");
			return;
		}

		if (!MatchUtils.isDate(telInvitationTime)) {
			errors.rejectValue("telInvitationTime", null, "电话邀约时间格式错误");
		} else {

			final String memberUuid = formDto.getMemberUuid();
			Turnover turnover = this.memberService
					.findTurnoverByUuid(memberUuid);

			if (turnover != null) {
				if (turnover.getTurnoverDate() != null) {
					long turnTime = turnover.getTurnoverDate().getTime();
					long telTime = DateUtils.getDate(telInvitationTime,
							"yyyy-MM-dd").getTime();
					if (turnTime < telTime) {
						errors.rejectValue("telInvitationTime", null,
								"电话邀约时间应小于离职时间");
					}
				}

			}

		}

	}

	public void validatePosition(RecruitmentFormDto formDto, Errors errors) {
		final String position = formDto.getTelIntentionPosition();
		if (StringUtils.isEmpty(position)) {
			errors.rejectValue("telIntentionPosition", null, "电话面试职位不能为空");
		}
	}

}
