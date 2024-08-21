package com.shinhan.dailyconsume.service;

import java.util.List;

import com.shinhan.dailyconsume.dto.mypage.RankDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingProjection;

public interface RankService {
	RankDTO getRankInfo(String memberId);
	
	List<RankingProjection> getAllRanking();
	
}
