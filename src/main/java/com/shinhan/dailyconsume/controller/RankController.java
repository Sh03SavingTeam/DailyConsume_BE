package com.shinhan.dailyconsume.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.mypage.RankDTO;
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
	
	@GetMapping("/ranking")
	public List<RankingProjection> rlist(){
		return rankService.getAllRanking();
	}
}
