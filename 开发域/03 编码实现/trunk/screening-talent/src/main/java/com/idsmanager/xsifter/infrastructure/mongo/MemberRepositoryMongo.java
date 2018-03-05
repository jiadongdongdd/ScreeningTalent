package com.idsmanager.xsifter.infrastructure.mongo;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.idsmanager.commons.utils.mongo.AbstractMongoSupport;
import com.idsmanager.xsifter.domain.IntentionStatus;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.Entry;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberProcess;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.domain.security.SecurityUtils;

@Repository("memberRepository")
public class MemberRepositoryMongo extends AbstractMongoSupport implements
		MemberRepository {

	@Autowired
	private MongoOperations mongoTemplate;

	@Override
	protected MongoOperations mongoTemplate() {
		return this.mongoTemplate;
	}

	@Override
	public void saveMember(Member member) {
		mongoTemplate().save(member);
	}

	@Override
	public void updateBasicMember(Member member) {
		Update update = new Update();
		update.set("chName", member.getChName());
		update.set("enName", member.getEnName());
		update.set("mobile", member.getMobile());

		update.set("idNumber", member.getIdNumber());
		update.set("email", member.getEmail());
		update.set("gender", member.getGender());

		update.set("age", member.getAge());
		update.set("school", member.getSchool());
		update.set("degree", member.getDegree());
		update.set("major", member.getMajor());

		update.set("nation", member.getNation());
		update.set("politicalStatus", member.getPoliticalStatus());
		update.set("origin", member.getOrigin());
		update.set("pinyin", member.getPinyin());

		final Query query = createIDQuery(member.getUuid());
		this.mongoTemplate().updateFirst(query, update, Member.class);
	}

	@Override
	public Member findMemberByUuid(String uuid) {
		Query query = new Query(Criteria.where("uuid").is(uuid));
		return mongoTemplate().findOne(query, Member.class);
	}

	@Override
	public List<Member> findMemberList(Map<String, Object> map) {

		Query query = new Query();
		// addChNamePagination(query, map);
		addPropertyPagination(map, query);
		addQueryConditions(map, query);

		return this.mongoTemplate().find(query, Member.class);

	}

	private void addQueryConditions(Map<String, Object> map, Query query) {

		final String idNumber = (String) map.get("idNumber");
		if (StringUtils.isNotEmpty(idNumber)) {
			query.addCriteria(Criteria.where("idNumber").regex(
					"/*" + idNumber + "/*"));
		}

		final String chName = (String) map.get("chName");
		if (StringUtils.isNotEmpty(chName)) {
			query.addCriteria(Criteria.where("chName").regex(
					"/*" + chName + "/*"));
		}

		final String companyGuid = (String) map.get("companyGuid");
		query.addCriteria(Criteria.where("companyGuid").is(companyGuid));

		final MemberProcess memberProcess = (MemberProcess) map
				.get("memberProcess");
		if (null != memberProcess) {
			query.addCriteria(Criteria.where("memberProcess").is(memberProcess));
		}

	}

	@Override
	public long totalMemberList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);

		addQueryConditions(map, query);
		return this.mongoTemplate().count(query, Member.class);
	}

	@Override
	public Member findMemberByMobile(String mobile) {
		Query query = new Query(Criteria.where("mobile").is(mobile));
		return this.mongoTemplate().findOne(query, Member.class);
	}

	@Override
	public Member findMemberByIDNumber(String idNumber) {
		Query query = new Query(Criteria.where("idNumber").is(idNumber));
		return this.mongoTemplate().findOne(query, Member.class);
	}

	@Override
	public List<Member> findMemberListByWide(Map<String, Object> map) {

		Query query = new Query();

		final MemberProcess memberProcess = (MemberProcess) map
				.get("memberProcess");
		if (null != memberProcess) {
			query.addCriteria(Criteria.where("memberProcess").is(memberProcess));
		}

		// addPagination(query, map);
		addPropertyPagination(map, query);
		addQueryConditionsWide(map, query);
		return this.mongoTemplate().find(query, Member.class);
	}

	private void addQueryConditionsWide(Map<String, Object> map, Query query) {

		final String chName = (String) map.get("chName");
		final String email = (String) map.get("email");
		final String mobile = (String) map.get("mobile");
		final String idNumber = (String) map.get("idNumber");

		if (StringUtils.isEmpty(chName)
				|| (StringUtils.isNotEmpty(chName) && (StringUtils
						.isEmpty(mobile) && StringUtils.isEmpty(email) && StringUtils
							.isEmpty(idNumber)))) {
			query.addCriteria(Criteria.where("companyGuid").is(null)
					.and("uuid").is(null));
		} else {
			if (StringUtils.isNotEmpty(idNumber)) {
				query.addCriteria(Criteria.where("idNumber").is(idNumber));
			}

			if (StringUtils.isNotEmpty(chName)) {
				query.addCriteria(Criteria.where("chName").is(chName));
			}

			if (StringUtils.isNotEmpty(email)) {
				query.addCriteria(Criteria.where("email").is(email));
			}

			if (StringUtils.isNotEmpty(mobile)) {
				query.addCriteria(Criteria.where("mobile").is(mobile));
			}
		}

	}

	@Override
	public long totalMemberListByWide(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addQueryConditionsWide(map, query);
		return this.mongoTemplate().count(query, Member.class);
	}

	@Override
	public int findMembersCountByCompanyId(String uuid) {
		Query query = new Query(Criteria.where("companyGuid").is(uuid));
		return (int) this.mongoTemplate().count(query, Member.class);
	}

	@Override
	public Recruitment findRecruitmentByUuid(String uuid) {
		return findById(Recruitment.class, uuid);
	}

	@Override
	public int findMemberTotalCount() {
		return this.mongoTemplate().findAll(Member.class).size();
	}

	@Override
	public void saveRecruitment(Recruitment recruitment) {
		this.mongoTemplate().save(recruitment);
	}

	@Override
	public void updateRecruitment(Recruitment recruitment) {
		Update update = new Update();
		update.set("telInvitationTime", recruitment.getTelInvitationTime());
		update.set("telIntentionPosition",
				recruitment.getTelIntentionPosition());
		update.set("telIntention", recruitment.getTelIntention());

		update.set("notJoinIntentionReason",
				recruitment.getNotJoinIntentionReason());
		update.set("notJoinIntentionReasonRemark",
				recruitment.getNotJoinIntentionReasonRemark());
		update.set("invitationTime", recruitment.getInvitationTime());

		update.set("intentionScore", recruitment.getIntentionScore());
		update.set("agreeEntry", recruitment.getAgreeEntry());
		update.set("personalOrCompany", recruitment.getPersonalOrCompany());

		update.set("notEntryPersonalReason",
				recruitment.getNotEntryPersonalReason());
		update.set("notEntryPersonReasonDetails",
				recruitment.getNotEntryPersonReasonDetails());
		update.set("notEntryCompanyReason",
				recruitment.getNotEntryCompanyReason());

		update.set("notEntryCompanyReasonRemark",
				recruitment.getNotEntryCompanyReasonRemark());
		update.set("entrySuccess", recruitment.getEntrySuccess());
		update.set("entryDate", recruitment.getEntryDate());

		update.set("chNameBasic", recruitment.getChNameBasic());
		update.set("idNumberBasic", recruitment.getIdNumberBasic());
		update.set("mobileBasic", recruitment.getMobileBasic());
		update.set("emailBasic", recruitment.getEmailBasic());
		update.set("joinInterview", recruitment.isJoinInterview());

		final Query query = createIDQuery(recruitment.getMemberUuid());
		this.mongoTemplate().updateFirst(query, update, Recruitment.class);
	}

	@Override
	public Member findMemberByUUIDAndCompanyGuid(String uuid, String guid) {
		Query query = new Query(new Criteria("uuid").is(uuid)
				.and("companyGuid").is(guid));
		return this.mongoTemplate().findOne(query, Member.class);
	}

	@Override
	public Turnover findTurnoverByUuid(String uuid) {
		return findById(Turnover.class, uuid);
	}

	@Override
	public void saveTurnover(Turnover turnover) {
		this.mongoTemplate().save(turnover);
	}

	@Override
	public void updateTurnover(Turnover turnover) {
		Update update = new Update();
		update.set("turnoverReason", turnover.getTurnoverReason());
		update.set("laborDisputeFileGuid", turnover.getLaborDisputeFileGuid());
		update.set("turnoverReasonRemark", turnover.getTurnoverReasonRemark());

		update.set("goOutReasonItems", turnover.getGoOutReasonItems());
		update.set("turnoverProcess", turnover.getTurnoverProcess());
		update.set("otherProcessRemark", turnover.getOtherProcessRemark());

		update.set("takeCompanyRsItems", turnover.getTakeCompanyRsItems());
		update.set("turnoverDate", turnover.getTurnoverDate());
		update.set("breachTrainingAgreement",
				turnover.isBreachTrainingAgreement());

		update.set("turnoverIncome", turnover.getTurnoverIncome());
		update.set("turnoverPosition", turnover.getTurnoverPosition());

		update.set("breachTrainingAgreementFileGuid",
				turnover.getBreachTrainingAgreementFileGuid());
		update.set("outStopPeriod", turnover.isOutStopPeriod());
		update.set("outStopPeriodFileGuid", turnover.getOutStopPeriodFileGuid());

		update.set("illegalDestroyCompanyFace",
				turnover.isIllegalDestroyCompanyFace());
		update.set("illegalDestroyCompanyFaceFileGuid",
				turnover.getIllegalDestroyCompanyFaceFileGuid());
		update.set("legalDisputes", turnover.isLegalDisputes());

		update.set("legalDisputesFileGuid", turnover.getLegalDisputesFileGuid());
		update.set("outOtherRemark", turnover.getOutOtherRemark());
		update.set("outOtherFileGuid", turnover.getOutOtherFileGuid());

		update.set("chNameBasic", turnover.getChNameBasic());
		update.set("idNumberBasic", turnover.getIdNumberBasic());
		update.set("mobileBasic", turnover.getMobileBasic());
		update.set("emailBasic", turnover.getEmailBasic());
		update.set("processAfterStatus", turnover.isProcessAfterStatus());

		final Query query = createIDQuery(turnover.getMemberUuid());
		this.mongoTemplate().updateFirst(query, update, Turnover.class);
	}

	@Override
	public Worked findWorkedByUuid(String uuid) {
		return findById(Worked.class, uuid);
	}

	@Override
	public void saveWorked(Worked worked) {
		this.mongoTemplate().save(worked);
	}

	@Override
	public void updateWorked(Worked worked) {
		Update update = new Update();
		update.set("awards", worked.getAwards());
		update.set("awardsRemark", worked.getAwardsRemark());
		update.set("punished", worked.getPunished());

		update.set("punishedRemark", worked.getPunishedRemark());

		update.set("workChange", worked.isWorkChange());
		update.set("changeDateOne", worked.getChangeDateOne());
		update.set("changeDateTwo", worked.getChangeDateTwo());
		update.set("changeDateThree", worked.getChangeJobThree());

		update.set("changeJobOne", worked.getChangeJobOne());
		update.set("changeJobTwo", worked.getChangeJobTwo());
		update.set("changeJobThree", worked.getChangeJobThree());

		update.set("chNameBasic", worked.getChNameBasic());
		update.set("idNumberBasic", worked.getIdNumberBasic());
		update.set("mobileBasic", worked.getMobileBasic());
		update.set("emailBasic", worked.getEmailBasic());

		final Query query = createIDQuery(worked.getMemberUuid());
		this.mongoTemplate().updateFirst(query, update, Worked.class);
	}

	@Override
	public void deleteMember(Member member) {
		this.mongoTemplate().remove(member);

	}

	@Override
	public List<Member> findAdminMemberList(Map<String, Object> map) {
		Query query = new Query();
		// addChNamePagination(query, map);
		addPropertyPagination(map, query);
		addAdminQueryConditions(map, query);
		return this.mongoTemplate().find(query, Member.class);
	}

	private Query addPropertyPagination(Map<String, Object> map, Query query) {
		String propertyName = (String) map.get("propertyName");
		query.skip((Integer) map.get("startIndex")).limit(
				(Integer) map.get("perPageSize"));
		if (StringUtils.isEmpty(propertyName)) {
			query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "pinyin")));
			return query;
		}

		String direction = (String) map.get("direction");

		if ("chName".equals(propertyName)) {
			if ("asc".equals(direction)) {
				query.with(new Sort(
						new Sort.Order(Sort.Direction.ASC, "pinyin")));
				return query;
			}
			query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "pinyin")));
			return query;
		}

		if ("asc".equals(direction)) {
			query.with(new Sort(
					new Sort.Order(Sort.Direction.ASC, propertyName)));
			return query;
		}

		query.with(new Sort(new Sort.Order(Sort.Direction.DESC, propertyName)));
		return query;

	}

	private void addAdminQueryConditions(Map<String, Object> map, Query query) {
		final String idNumber = (String) map.get("idNumber");
		if (StringUtils.isNotEmpty(idNumber)) {
			query.addCriteria(Criteria.where("idNumber").regex(
					"/*" + idNumber + "/*"));
		}

		final String chName = (String) map.get("chName");
		if (StringUtils.isNotEmpty(chName)) {
			query.addCriteria(Criteria.where("chName").regex(
					"/*" + chName + "/*"));
		}

		final String email = (String) map.get("email");
		if (StringUtils.isNotEmpty(email)) {
			query.addCriteria(Criteria.where("email")
					.regex("/*" + email + "/*"));
		}

		final String mobile = (String) map.get("mobile");
		if (StringUtils.isNotEmpty(mobile)) {
			query.addCriteria(Criteria.where("mobile").regex(
					"/*" + mobile + "/*"));
		}

		final MemberProcess memberProcess = (MemberProcess) map
				.get("memberProcess");
		if (null != memberProcess) {
			query.addCriteria(Criteria.where("memberProcess").is(memberProcess));
		}

		final String companyName = (String) map.get("companyName");

		if (StringUtils.isNotEmpty(companyName)) {
			query.addCriteria(Criteria.where("companyName").regex(
					"/*" + companyName + "/*"));
		}

	}

	@Override
	public long findAdminMemberTotalCount(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);

		addAdminQueryConditions(map, query);
		return this.mongoTemplate().count(query, Member.class);
	}

	@Override
	public List<Member> findHomeMemberList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addHomeQueryConditions(map, query);
		return this.mongoTemplate().find(query, Member.class);
	}

	private void addHomeQueryConditions(Map<String, Object> map, Query query) {
		final String chName = (String) map.get("chName");
		query.addCriteria(Criteria.where("chName").regex("/*" + chName + "/*"));

	}

	@Override
	public long totalHomeMemberList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addHomeQueryConditions(map, query);
		return this.mongoTemplate().count(query, Member.class);
	}

	@Override
	public long totalMembers() {
		return this.mongoTemplate().count(new Query(), Member.class);
	}

	@Override
	public List<Member> findMembersByCompanyGuid(String companyGuid) {
		Query query = new Query(Criteria.where("companyGuid").is(companyGuid));
		return this.mongoTemplate().find(query, Member.class);
	}

	@Override
	public long companyTotalMembers(String companyGuid) {
		Query query = new Query(Criteria.where("companyGuid").is(companyGuid));
		return this.mongoTemplate().count(query, Member.class);
	}

	@Override
	public Member findMemberByMobileAndCompanyUuid(String mobile,
			String currentUserGuid) {
		Query query = new Query(Criteria.where("mobile").is(mobile)
				.and("companyGuid").is(currentUserGuid));
		return this.mongoTemplate().findOne(query, Member.class);
	}

	@Override
	public Member findMemberByIDNumberAndCompanyGuid(String idNumber,
			String currentUserGuid) {
		Query query = new Query(Criteria.where("idNumber").is(idNumber)
				.and("companyGuid").is(currentUserGuid));
		return this.mongoTemplate().findOne(query, Member.class);
	}

	@Override
	public List<Member> findMemberList() {
		return this.mongoTemplate().findAll(Member.class);
	}

	@Override
	public void updateMemberDataState(Member member, MemberProcess memberProcess) {
		Update update = new Update();
		update.set("memberProcess", memberProcess);

		final Query query = createIDQuery(member.getUuid());
		this.mongoTemplate().updateFirst(query, update, Member.class);
	}

	@Override
	public void saveBasic(Basic basic) {
		this.mongoTemplate().save(basic);

	}

	@Override
	public Basic findBasicByUuid(String uuid) {
		Query query = new Query(Criteria.where("uuid").is(uuid));
		return this.mongoTemplate().findOne(query, Basic.class);
	}

	@Override
	public void saveEducation(Education education) {
		this.mongoTemplate().save(education);
	}

	@Override
	public Education findEducationByUuid(String uuid) {
		Query query = new Query(Criteria.where("uuid").is(uuid));
		return this.mongoTemplate().findOne(query, Education.class);
	}

	@Override
	public Member findMemberByEmailAndCompanyGuid(String email,
			String currentUserGuid) {
		Query query = new Query(Criteria.where("email").is(email)
				.and("companyGuid").is(currentUserGuid));
		return this.mongoTemplate().findOne(query, Member.class);
	}

	@Override
	public List<Member> findMyMemberList(Map<String, Object> queryMap) {
		Query query = new Query();
		addPropertyPagination(queryMap, query);
		addMyQueryConditions(queryMap, query);
		return this.mongoTemplate().find(query, Member.class);
	}

	private void addMyQueryConditions(Map<String, Object> queryMap, Query query) {
		final String chName = (String) queryMap.get("chName");
		final String email = (String) queryMap.get("email");
		final String mobile = (String) queryMap.get("mobile");
		final String idNumber = (String) queryMap.get("idNumber");
		final String companyGuid = (String) queryMap.get("companyGuid");

		if (StringUtils.isNotEmpty(chName)) {
			query.addCriteria(Criteria.where("chName").regex(
					"/*" + chName + "/*"));
		}
		if (StringUtils.isNotEmpty(email)) {
			query.addCriteria(Criteria.where("email")
					.regex("/*" + email + "/*"));
		}
		if (StringUtils.isNotEmpty(mobile)) {
			query.addCriteria(Criteria.where("mobile").regex(
					"/*" + mobile + "/*"));
		}
		if (StringUtils.isNotEmpty(idNumber)) {
			query.addCriteria(Criteria.where("idNumber").regex(
					"/*" + idNumber + "/*"));
		}
		if (StringUtils.isNotEmpty(companyGuid)) {
			query.addCriteria(Criteria.where("companyGuid").is(companyGuid));
		}
		if (StringUtils.isEmpty(companyGuid)) {
			query.addCriteria(Criteria.where("companyGuid").is(
					SecurityUtils.currentUserGuid()));
		}

	}

	@Override
	public long totalMyMemberList(Map<String, Object> queryMap) {
		Query query = new Query();
		addPropertyPagination(queryMap, query);
		addMyQueryConditions(queryMap, query);
		return this.mongoTemplate().count(query, Member.class);
	}

	@Override
	public List<Member> findQueryMemberList(Map<String, Object> map) {
		Query query = new Query();
		addPropertyPagination(map, query);
		addQueryConditionsWide(map, query);
		return this.mongoTemplate().find(query, Member.class);
	}

	@Override
	public long totalQueryMemberList(Map<String, Object> map) {
		Query query = new Query();
		addPropertyPagination(map, query);
		addQueryConditionsWide(map, query);
		return this.mongoTemplate().count(query, Member.class);
	}

	@Override
	public long totalQueryRecruitmentList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addWideQueryConditions(map, query);
		return this.mongoTemplate().count(query, Recruitment.class);
	}

	private Query addWideQueryConditions(Map<String, Object> map, Query query) {

		final String chName = (String) map.get("chName");
		final String mobile = (String) map.get("mobile");
		final String idNumber = (String) map.get("idNumber");
		final String email = (String) map.get("email");

		if (StringUtils.isNotEmpty(chName)
				&& (StringUtils.isNotEmpty(email)
						|| StringUtils.isNotEmpty(mobile) || StringUtils
							.isNotEmpty(idNumber))) {

			Criteria criteriaChName = new Criteria("chNameBasic").is(chName);

			Criteria criteriaMobile = Criteria.where("mobileBasic").is(mobile);

			Criteria criteriaIdnumber = Criteria.where("idNumberBasic").is(
					idNumber);

			Criteria criteriaEmail = Criteria.where("emailBasic").is(email);

			Criteria[] criterias = new Criteria[3];

			criterias[0] = criteriaIdnumber;
			criterias[1] = criteriaEmail;
			criterias[2] = criteriaMobile;

			Criteria orOperator = new Criteria().orOperator(criterias);

			Criteria criteria = criteriaChName.andOperator(orOperator);

			query.addCriteria(criteria);

		} else {
			query.addCriteria(Criteria.where("chNameBasic").is(null));
		}

		return query;

	}

	@Override
	public List<Recruitment> findQueryRecruitmentList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addWideQueryConditions(map, query);
		return this.mongoTemplate().find(query, Recruitment.class);
	}

	@Override
	public List<Turnover> findQueryTurnoverList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addWideQueryConditions(map, query);
		return this.mongoTemplate().find(query, Turnover.class);
	}

	@Override
	public long totalQueryTurnoverList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addWideQueryConditions(map, query);
		return this.mongoTemplate().count(query, Turnover.class);
	}

	@Override
	public long totalQueryWorkedList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addWideQueryConditions(map, query);
		return this.mongoTemplate().count(query, Worked.class);
	}

	@Override
	public List<Worked> findQueryWorkedList(Map<String, Object> map) {
		Query query = new Query();
		addPagination(query, map);
		addWideQueryConditions(map, query);
		return this.mongoTemplate().find(query, Worked.class);
	}

	@Override
	public Entry findEntryByUuid(String memberUuid) {
		Query query = new Query(Criteria.where("uuid").is(memberUuid));
		return this.mongoTemplate().findOne(query, Entry.class);
	}

	@Override
	public void saveEntry(Entry entry) {
		this.mongoTemplate().save(entry);

	}

	@Override
	public long totalEntryList(Map<String, Object> map3) {
		Query query = new Query();
		addPagination(query, map3);
		addWideQueryConditions(map3, query);
		return this.mongoTemplate().count(query, Entry.class);
	}

	@Override
	public List<Entry> findEntryList(Map<String, Object> map3) {
		Query query = new Query();
		addPagination(query, map3);
		addWideQueryConditions(map3, query);
		return this.mongoTemplate().find(query, Entry.class);
	}

	@Override
	public List<Recruitment> findRecruitmentList(Map<String, Object> map2) {
		Query query = new Query();
		addPagination(query, map2);
		addWideQueryConditions2(map2, query);
		return this.mongoTemplate().find(query, Recruitment.class);
	}

	private Query addWideQueryConditions2(Map<String, Object> map, Query query) {
		final String chName = (String) map.get("chName");
		final String mobile = (String) map.get("mobile");
		final String idNumber = (String) map.get("idNumber");
		final String email = (String) map.get("email");

		if (StringUtils.isNotEmpty(chName)
				&& (StringUtils.isNotEmpty(email)
						|| StringUtils.isNotEmpty(mobile) || StringUtils
							.isNotEmpty(idNumber))) {

			Criteria criteriaChName = new Criteria("chNameBasic").is(chName);

			Criteria criteriaMobile = Criteria.where("mobileBasic").is(mobile);

			Criteria criteriaIdnumber = Criteria.where("idNumberBasic").is(
					idNumber);

			Criteria criteriaEmail = Criteria.where("emailBasic").is(email);

			Criteria[] criterias = new Criteria[3];

			criterias[0] = criteriaIdnumber;
			criterias[1] = criteriaEmail;
			criterias[2] = criteriaMobile;

			Criteria orOperator = Criteria.where("telIntention")
					.is(IntentionStatus.ENABLED).orOperator(criterias);

			Criteria criteria = criteriaChName.andOperator(orOperator);

			query.addCriteria(criteria);

		} else {
			query.addCriteria(Criteria.where("chNameBasic").is(null));
		}

		return query;
	}

	@Override
	public long totalRecruitmentList(Map<String, Object> map2) {
		Query query = new Query();
		addPagination(query, map2);
		addWideQueryConditions2(map2, query);
		return this.mongoTemplate().count(query, Recruitment.class);
	}

	@Override
	public List<Recruitment> findRecruitmentTelList(Map<String, Object> map1) {
		Query query = new Query();
		addPagination(query, map1);
		addWideQueryConditions(map1, query);
		return this.mongoTemplate().find(query, Recruitment.class);
	}

	@Override
	public long totalRecruitmentTelList(Map<String, Object> map1) {
		Query query = new Query();
		addPagination(query, map1);
		addWideQueryConditions(map1, query);
		return this.mongoTemplate().count(query, Recruitment.class);
	}

	@Override
	public void deleteBasic(Basic basic) {

		this.mongoTemplate().remove(basic);

	}

	@Override
	public void deleteEducation(Education education) {
		this.mongoTemplate().remove(education);
	}

	@Override
	public void deleteRecruitment(Recruitment recruitment) {
		this.mongoTemplate().remove(recruitment);
	}

	@Override
	public void deleteTurnover(Turnover turnover) {
		this.mongoTemplate().remove(turnover);
	}

	@Override
	public void deleteWorked(Worked worked) {
		this.mongoTemplate().remove(worked);
	}

	@Override
	public void deleteEntry(Entry entry) {
		this.mongoTemplate().remove(entry);
	}

	@Override
	public Member findMemberByBasic(Basic basic) {
		Query query1 = new Query(Criteria.where("chName").is(basic.getChName())
				.and("idNumber").is(basic.getIdNumber()).and("companyGuid")
				.is(basic.getCompanyGuid()));
		Query query2 = new Query(Criteria.where("chName").is(basic.getChName())
				.and("mobile").is(basic.getMobile()).and("companyGuid")
				.is(basic.getCompanyGuid()));
		Query query3 = new Query(Criteria.where("chName").is(basic.getChName())
				.and("email").is(basic.getEmail()).and("companyGuid")
				.is(basic.getCompanyGuid()));
		Member member1 = this.mongoTemplate().findOne(query1, Member.class);
		Member member2 = this.mongoTemplate().findOne(query2, Member.class);
		Member member3 = this.mongoTemplate().findOne(query3, Member.class);

		if (member1 != null) {
			return member1;
		}
		if (member2 != null) {
			return member2;
		}
		if (member3 != null) {
			return member3;
		}
		return null;
	}

	@Override
	public long getMemberCountByCompanyUuid(String currentUserGuid) {
		Query query = new Query(Criteria.where("companyGuid").is(
				currentUserGuid));
		return this.mongoTemplate().count(query, Member.class);
	}

}
