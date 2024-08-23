package com.shinhan.dailyconsume.service;



public interface WeeklyConsumeService {
	
	//1.주간소비설정
	String save(String memberId, int weeklyMoney);
}
