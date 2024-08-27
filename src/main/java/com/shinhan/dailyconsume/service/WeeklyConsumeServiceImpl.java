package com.shinhan.dailyconsume.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.WeeklyConsumeEntity;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.WeeklyConsumeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WeeklyConsumeServiceImpl implements WeeklyConsumeService{

	private final WeeklyConsumeRepository wcRepo;
	private final MemberRepository mRepo;

	@Override
	public String save(String memberId, int weeklyMoney) {
		
		MemberEntity member = mRepo.findByMemberId(memberId);
		 
		 LocalDate today = LocalDate.now();
		 
	    // 이번 주의 시작일(월요일)
	    LocalDate weekStart = today.minusDays(today.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue());
	        
	    // 이번 주의 종료일(일요일)
	    LocalDate weekEnd = weekStart.plusDays(6);

	    Timestamp startDate = Timestamp.valueOf(weekStart.atStartOfDay());
	    Timestamp endDate = Timestamp.valueOf(weekEnd.atTime(LocalTime.MAX));
		 
		 String weeklyCheck = "0";
		 
		WeeklyConsumeEntity weeklyConsume = WeeklyConsumeEntity.builder()
				.startDate(startDate)
				.endDate(endDate)
				.weeklyCheck(weeklyCheck)
				.weeklyMoney(weeklyMoney)
				.member(member)
				.build();
				 
		 wcRepo.save(weeklyConsume);
		
		
		return memberId;
	}
	
	
}
