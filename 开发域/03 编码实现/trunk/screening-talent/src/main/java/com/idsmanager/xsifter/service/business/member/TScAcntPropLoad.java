package com.idsmanager.xsifter.service.business.member;

import com.idsmanager.commons.utils.PropertyUtils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.IntentionStatus;
import com.idsmanager.xsifter.domain.member.Basic;
import com.idsmanager.xsifter.domain.member.Education;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.NotEntryPersonalReasonItem;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.domain.member.Worked;
import com.idsmanager.xsifter.web.controller.enterprise.EnterpriseAuditController;

import java.lang.reflect.Field; 
import java.util.List;

import org.apache.commons.lang.StringUtils;
public class TScAcntPropLoad {
	
	private transient MemberRepository repository = BeanProvider
			.getBean(MemberRepository.class);
	
	private String uuid;
	
	private Recruitment recruitment= null;
	
	private Turnover turnover = null;
	
	private Worked worked = null;

	private Basic basic = null;
	
	private Education education = null;
	
	public TScAcntPropLoad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TScAcntPropLoad(String uuid) {
		super();
		this.uuid = uuid;
	}

	public Integer load() {
		
		recruitment = this.repository.findRecruitmentByUuid(uuid);

		turnover = this.repository.findTurnoverByUuid(uuid);
		
		worked = this.repository.findWorkedByUuid(uuid);
		
		basic = this.repository.findBasicByUuid(uuid);
		
	    education = this.repository.findEducationByUuid(uuid);
	    int basic = xsifterBasicField();
	    int turn = xsifterTurnoverField();
	    int recu = xsifterRecruField();
	    int work = xsifterWorkedField();
	    int educ = xsifterEducationField();
	    Integer result = basic += turn +=recu += work += educ;
		return result;
	}
	
	public Integer xsifterRecruField(){
		int num = 0;
		if(null != recruitment){
			Class cls = recruitment.getClass();  
			Field[] fields = cls.getDeclaredFields();  
			for(int i=0; i<fields.length; i++){  
				Field f = fields[i];  
				f.setAccessible(true);  
				try {
//					System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(recruitment));
					if(null != f.get(recruitment)){
						if(StringUtils.isNotEmpty(f.get(recruitment).toString())){
							if(f.getName().equals("notEntryPersonReasonDetails")){
								List<NotEntryPersonalReasonItem> notEntryPersonReasonDetails = recruitment.getNotEntryPersonReasonDetails();
								for (NotEntryPersonalReasonItem notEntryPersonalReasonItem : notEntryPersonReasonDetails) {
									int itemNum = ifNotEntryPersonalReasonItem(notEntryPersonalReasonItem);
									num += itemNum;
								}
							}else if(f.getName().equals("entrySuccess")){
								IntentionStatus success = recruitment.getEntrySuccess();
								int entrySuccessNum = entrySuccess(success);
								num += entrySuccessNum;
							}
							else{
								String propNum = PropertyUtils.getProp(f.getName());
								if(StringUtils.isNotEmpty(propNum)){
									num += Integer.parseInt(propNum);
								}
							}
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}  
			}  
		}
        return num;
	}
	//不同意入职
	public int ifNotEntryPersonalReasonItem(NotEntryPersonalReasonItem entryPersonalReasonItem){
		int num =0;
		if(entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.PRE_TELL_REASON) || entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.PRE_NOT_TELL)){
			num += 2;
		}else if(entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.SALARY_DIFF) || entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.OTHER_REASON) || entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.UNKNOWN_REASON)){
			num += 1;
		}else if(entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.IMG_NOT_MATCH) || entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.EDU_NOT_MATCH) || entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.HISTORY_NOT_MATCH)|| entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.EDU_NOT_MATCH) || entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.OTHER_GOAL)){
			num += 3;
			
		}else if(entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.OTHER_NOT_MATCH)){
			num += 1;
		}else if(entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.LANGUAGE_BAD) || entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.OPERATION_BAD) || entryPersonalReasonItem.equals(NotEntryPersonalReasonItem.CLOTHES_BAD)){
			num += 1;
		}
		return num;
	}
	//入职成功
	public int entrySuccess(IntentionStatus status){
		int num = 0;
		if(status.equals(IntentionStatus.DISABLED)){
			num+=1;
		}else{
			num+=5;
		}
		return num;
	}
	public Integer xsifterTurnoverField(){
		int num = 0;
		if(null != turnover){
			Class cls = turnover.getClass();  
			Field[] fields = cls.getDeclaredFields();  
			for(int i=0; i<fields.length; i++){  
				Field f = fields[i];  
				f.setAccessible(true);  
				try {
//					System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(turnover));
					if(null != f.get(turnover)){
						if(StringUtils.isNotEmpty(f.get(turnover).toString())){
							String propNum = PropertyUtils.getProp(f.getName());
							if(StringUtils.isNotEmpty(propNum)){
								num += Integer.parseInt(propNum);
							}
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}  
			}  
		}
        return num;
	}
	
	public Integer xsifterWorkedField(){
		int num = 0;
		if(null != worked){
			Class cls = worked.getClass();  
			Field[] fields = cls.getDeclaredFields();  
			for(int i=0; i<fields.length; i++){  
				Field f = fields[i];  
				f.setAccessible(true);  
				try {
//					System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(worked));
					if(null != f.get(worked)){
						if(StringUtils.isNotEmpty(f.get(worked).toString())){
							String propNum = PropertyUtils.getProp(f.getName());
							if(StringUtils.isNotEmpty(propNum)){
								num += Integer.parseInt(propNum);
							}
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}  
			}  
		}
        return num;
	}
	
	public Integer xsifterBasicField(){
		int num = 0;
		if(null != basic){
			Class cls = basic.getClass();  
			Field[] fields = cls.getDeclaredFields();  
			for(int i=0; i<fields.length; i++){  
				Field f = fields[i];  
				f.setAccessible(true);  
				try {
//					System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(basic));
					if(null != f.get(basic)){
						if(StringUtils.isNotEmpty(f.get(basic).toString())){
							String propNum = PropertyUtils.getProp(f.getName());
							if(StringUtils.isNotEmpty(propNum)){
								num += Integer.parseInt(propNum);
							}
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}  
			}  
		}
        return num;
	}
	
	public Integer xsifterEducationField(){
		int num = 0;
		if(null != education){
			Class cls = education.getClass();  
			Field[] fields = cls.getDeclaredFields();  
			for(int i=0; i<fields.length; i++){  
				Field f = fields[i];  
				f.setAccessible(true);  
				try {
//					System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(education));
					if(null != f.get(education)){
						if(StringUtils.isNotEmpty(f.get(education).toString())){
							String propNum = PropertyUtils.getProp(f.getName());
							if(StringUtils.isNotEmpty(propNum)){
								num += Integer.parseInt(propNum);
							}
						}
					}
				} catch (IllegalArgumentException | IllegalAccessException e1) {
					e1.printStackTrace();
				}  
			}  
		}
        return num;
	}
	
}
