package com.idsmanager.xsifter.domain.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.idsmanager.commons.utils.DateUtils;
import com.idsmanager.commons.web.context.BeanProvider;
import com.idsmanager.xsifter.domain.member.Member;
import com.idsmanager.xsifter.domain.member.MemberProcess;
import com.idsmanager.xsifter.domain.member.MemberRepository;
import com.idsmanager.xsifter.domain.member.Recruitment;
import com.idsmanager.xsifter.domain.member.Turnover;
import com.idsmanager.xsifter.service.business.memberexcep.MemberExcepSaver;
/**
 * 
 *类名称 数据清理类
 *创建人 dushaofei
 *创建时间：2016-2-25 下午3:55:06 
 *类描述：
 *@version
 */
public class TimerTaskManager implements ServletContextListener {

	private Timer timer = new Timer();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("程序定时执行任务结束.....................................");
		timer.cancel();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		Date time = initDate();
		
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				xifterData();
			}
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}
	
	public Date initDate(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23); // 控制时
		calendar.set(Calendar.MINUTE, 59); // 控制分
		calendar.set(Calendar.SECOND, 59); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为凌晨的1：00：00
		return time;
	}

	public void xifterData() {
		
		MemberRepository repository = BeanProvider.getBean(MemberRepository.class);
				
		List<Member> listBeforMember = repository.findMemberList();
		//筛选重复数据
		for (int i = 0; i < listBeforMember.size() - 1; i++) {
			for (int j = listBeforMember.size() - 1; j > i; j--) {
				if (listBeforMember.get(j).getChName().equals(listBeforMember.get(i).getChName()) 
						&& listBeforMember.get(j).getIdNumber().equals(listBeforMember.get(i).getIdNumber())) {
					//check the Member entry Date
					checkDate(listBeforMember.get(j),listBeforMember.get(i));
				}
			}
		}
	}
	
	public void checkDate(Member member1,Member member2){
		MemberRepository repository = BeanProvider.getBean(MemberRepository.class);
		//查询两个员工的入职时间和离职时间
		
		//员工1
		Recruitment recruitment1 = repository.findRecruitmentByUuid(member1.getUuid());
		Turnover turnover1 = repository.findTurnoverByUuid(member1.getUuid());
		
		if(null == recruitment1 || null == recruitment1.getEntryDate()) return;
		
		if(null == turnover1 || null == turnover1.getTurnoverDate()) return;
		
		//入职时间
		Date entryDate1 = recruitment1.getEntryDate();
		//离职时间
		Date turnoDate1 = turnover1.getTurnoverDate();
		
		//员工2
		Recruitment recruitment2 = repository.findRecruitmentByUuid(member2.getUuid());
		Turnover turnover2 = repository.findTurnoverByUuid(member2.getUuid());
		if(null == recruitment2 || null == recruitment2.getEntryDate()) return;
		//入职时间
		Date entryDate2 = recruitment2.getEntryDate();
		if(null == turnover2 || null == turnover2.getTurnoverDate()) return;
		//离职时间
		Date turnoDate2 = turnover2.getTurnoverDate();
		 
		//check the entry date.
		if(checkEntryDate(entryDate1,entryDate2)){
			//update 2 data state for mongoDB
			saveMemberExcep(member1,entryDate1,turnoDate1);
			saveMemberExcep(member2,entryDate2,turnoDate2);
			return;
		}
		//check the turnoDate 
		if(checkEntryDate(turnoDate1,turnoDate2)){
			//update 2 data state for mongoDB
//			repository.updateMemberDataState(member2,MemberProcess.DATA_REPEAT);
			saveMemberExcep(member1,entryDate1,turnoDate1);
			saveMemberExcep(member2,entryDate2,turnoDate2);
			return;
		}
		
	}
	
	public void saveMemberExcep(Member member,Date entryDate,Date turnoDate){
		MemberExcepSaver save = new MemberExcepSaver(member);
		save.setEntryDate(entryDate);
		save.setTurnoverDate(turnoDate);
		save.save();
	}
	
	//check the entry date and turnoDate 
	public Boolean checkEntryDate(Date date1,Date date2){
		Boolean flg = false;
		//check date month
		int month = DateUtils.getDiffMonths(date1, date2);
		//tong yue
		if(month == 0){
			//check date day number
			int day1 = DateUtils.getDayInMonthByDate(date1);
			int day2 = DateUtils.getDayInMonthByDate(date2);
			if(day1==day2){
				flg = true;
			}
		}
		return flg;
	}
	
	public static void main(String[] args) {
		Date da1 = DateUtils.now();
		Date da2 = DateUtils.strToDate("2016-1-24");
		int month = DateUtils.getDiffMonths(da1,da2);
		System.out.println(month);
		
		
		Date date = DateUtils.pkdatelt(da1, da2);
		System.out.println(date);
		
		int day1 = DateUtils.getDayInMonthByDate(da1);
		int day2 = DateUtils.getDayInMonthByDate(da2);
		System.out.println(day1);
		System.out.println(day2);
	}
	

}
