package com.shinhan.dailyconsume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import com.shinhan.dailyconsume.dto.mypage.AddressRankingProjection;
import com.shinhan.dailyconsume.dto.mypage.RankDTO;
import com.shinhan.dailyconsume.dto.mypage.RankHistoryInfoDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingProjection;

public interface RankService {
	RankDTO getRankInfo(String memberId);
	
	List<RankingProjection> getAllRanking();
	
	List<AddressRankingProjection> getRankingByAddress(String memberId);
	
	String register(RankHistoryInfoDTO rankHistoryInfoDTO);
	
	int countAttendanceCheckByMemberId(String memberId);
	
	int checkIfRankExistsForToday(String memberId);
}
