package com.shinhan.dailyconsume.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.AttendanceDTO;
import com.shinhan.dailyconsume.dto.mypage.AddressRankingProjection;
import com.shinhan.dailyconsume.dto.mypage.RankDTO;
import com.shinhan.dailyconsume.dto.mypage.RankHistoryInfoDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingProjection;
import com.shinhan.dailyconsume.service.RankService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rank")
public class RankController {
	final RankService rankService;
	
	
	@GetMapping("/{memberId}")
	public RankDTO rankInfo(@PathVariable("memberId") String memberId) {
		return rankService.getRankInfo(memberId);
	}
	@GetMapping("/benefits/{memberId}")
	public RankDTO rankInfoBenefit(@PathVariable("memberId") String memberId) {
		return rankService.getRankInfo(memberId);
	}
	@GetMapping("/ranking")
	public List<RankingProjection> rlist(){
		return rankService.getAllRanking();
	}
	@GetMapping("/aranking/{memberId}")
	public List<AddressRankingProjection> getRankingByAddress(@PathVariable("memberId") String memberId) {
		return rankService.getRankingByAddress(memberId);
	}
	
	@PostMapping("/scoreInsert")
	public String register(@RequestBody RankHistoryInfoDTO rankHistoryInfoDTO) {
		return rankService.register(rankHistoryInfoDTO);
	}
	@GetMapping("/attendance/{memberId}")
	public AttendanceDTO attendanceInfo(@PathVariable("memberId") String memberId) {
		int attendanceInfo = rankService.checkIfRankExistsForToday(memberId);
		int totalAttendance = rankService.countAttendanceCheckByMemberId(memberId);
		AttendanceDTO attenDTO = new AttendanceDTO(memberId,attendanceInfo,totalAttendance );
		return attenDTO;
	}
}
