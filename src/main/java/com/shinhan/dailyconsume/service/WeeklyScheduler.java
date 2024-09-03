package com.shinhan.dailyconsume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class WeeklyScheduler {

    private final WeeklyConsumeService weeklyConsumeService;
    private final MemberService memService;

    @Scheduled(cron = "0 0 0 * * 1")
    public void weeklyMission(){
        weeklyConsumeService.weeklyMission(LocalDate.now().plusDays(-1));
    }
    @Scheduled(cron = "0 0 0 1 * *")
    public void monthRankReset() {
    	memService.updateAllMembersRankToOne();
    }
}
