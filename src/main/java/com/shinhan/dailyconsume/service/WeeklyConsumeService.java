package com.shinhan.dailyconsume.service;


import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

public interface WeeklyConsumeService {
	
	//1.주간소비설정
	String save(String memberId, int weeklyMoney);

	// 주간소비미션 확인 -> 포인트 및 등급 점수 지급, 상태값 업데이트
	void weeklyMission(LocalDate yesterday);
}
