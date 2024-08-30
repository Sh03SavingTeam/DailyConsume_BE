package com.shinhan.dailyconsume.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.dto.mypage.RankHistoryInfoDTO;
import com.shinhan.dailyconsume.dto.point.PointRegisterDTO;
import com.shinhan.dailyconsume.repository.PayHistoryRepository;
import com.shinhan.dailyconsume.service.myapge.PointHistoryService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.WeeklyConsumeEntity;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.WeeklyConsumeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WeeklyConsumeServiceImpl implements WeeklyConsumeService{

	private final PointHistoryService phService;
	private final RankService rService;
	private final WeeklyConsumeRepository wcRepo;
	private final MemberRepository mRepo;
	private final PayHistoryRepository phRepo;


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

	// 주간소비미션 확인 -> 포인트 및 등급 점수 지급, 상태값 업데이트
	@Override
	public void weeklyMission(LocalDate yesterday) {

		// 미션 시작일 (지난주 월요일)
		Timestamp startDate = Timestamp.valueOf(yesterday.plusDays(-6).atStartOfDay());
		// 미션 마감일 (지난주 일요일)
		Timestamp endDate = Timestamp.valueOf(yesterday.atTime(LocalTime.MAX));

		System.out.println(startDate);
		System.out.println(endDate);

		// 지난주 일요일이 마감일인 WeeklyConsumeEntity 목록 가져오기
		List<WeeklyConsumeEntity> weeklyConsumeEntityList = wcRepo.findByEndDate(endDate);

		for(WeeklyConsumeEntity weeklyConsume : weeklyConsumeEntityList){
			String memberId = weeklyConsume.getMember().getMemberId();
			// 지난주 결제총액
			int weeklyPayAmount = phRepo.sumPayAmountBetweenDates(memberId, weeklyConsume.getStartDate(), weeklyConsume.getEndDate());
			// 지난주 미션금액
			int missionAmount = weeklyConsume.getWeeklyMoney();
			// 지난주 미션금액의 절반
			int halfMissionAmount = missionAmount/2;

			// 50% 이상 ~ 설정금액 이하 결제 시
			if(weeklyPayAmount >= halfMissionAmount && weeklyPayAmount <= missionAmount){
				// 리워드(500원) 지급
				PointRegisterDTO pointRegisterDTO = new PointRegisterDTO(memberId, "주간 소비 미션 달성 포인트 지급", 500);
				phService.register(pointRegisterDTO);
				// 등급 점수 5점 지급
				RankHistoryInfoDTO rankHistoryInfoDTO = new RankHistoryInfoDTO(5L, "주간 소비 미션 달성 점수 지급", memberId);
				rService.register(rankHistoryInfoDTO);
				// 주간소비미션 달성값 update
				weeklyConsume.updateWeeklyCheck();
			}
			else if(weeklyPayAmount <= halfMissionAmount){
				// 등급 점수 3점 지급
				RankHistoryInfoDTO rankHistoryInfoDTO = new RankHistoryInfoDTO(3L, "주간 소비 미션 달성 점수 지급", memberId);
				rService.register(rankHistoryInfoDTO);
				// 주간소비미션 달성값 update
				weeklyConsume.updateWeeklyCheck();
			}
		}

	}

}
