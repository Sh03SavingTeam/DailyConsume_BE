package com.shinhan.dailyconsume.service;

import com.shinhan.dailyconsume.dto.WeeklyConsumeDTO;

public interface WeeklyConsumeService {
	
	//1.주간소비설정
	WeeklyConsumeDTO save(WeeklyConsumeDTO dto);
}
