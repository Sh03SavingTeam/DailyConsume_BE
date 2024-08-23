package com.shinhan.dailyconsume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.RankEntity;
import com.shinhan.dailyconsume.domain.RankHistoryEntity;
import com.shinhan.dailyconsume.dto.mypage.AddressRankingProjection;
import com.shinhan.dailyconsume.dto.mypage.RankDTO;
import com.shinhan.dailyconsume.dto.mypage.RankHistoryInfoDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingDTO;
import com.shinhan.dailyconsume.dto.mypage.RankingProjection;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.RankHistoryRepository;
import com.shinhan.dailyconsume.repository.RankRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService{
	
	private final MemberRepository memberRepo;
	private final RankRepository rankRepo;
	private final RankHistoryRepository rHistoryRepo;
	

	@Override
	public RankDTO getRankInfo(String memberId) {
		MemberEntity member = memberRepo.findByMemberId(memberId);
		String memberName = member.getMemberName();
		String rankName = member.getRank().getRankName();
		String rankImg = member.getRank().getRankImg();
		int totalScore = rHistoryRepo.getTotalScore(memberId);
		
		int nextAmount = 0;
		
		if((rankRepo.findById(member.getRank().getRankId()+1L).get())!=null){
			nextAmount = (rankRepo.findById(member.getRank().getRankId()+1L).get().getScore().intValue())-totalScore;
		}
		
		RankDTO rankDTO = new RankDTO(memberName, rankName, rankImg, totalScore, nextAmount);
		return rankDTO;
	}


	@Override
	public List<RankingProjection> getAllRanking() {
		List<RankingProjection> rlist = rHistoryRepo.getAllRanking();
		//System.out.println(rlist);
		return rlist;
	}


	@Override
	@Transactional
	public String register(RankHistoryInfoDTO rankHistoryInfoDTO) {
		
		Long amount = rankHistoryInfoDTO.getScore();
		String cmt = rankHistoryInfoDTO.getComent();
		
		
		MemberEntity member = memberRepo.findById(rankHistoryInfoDTO.getMemberId()).orElse(null);
		RankEntity memberRank = member.getRank();
		
		RankHistoryEntity rankHistory = RankHistoryEntity.builder()
                .amount(amount)
                .cmt(cmt)
                .member(member)
                .build();
		
		rHistoryRepo.save(rankHistory);
		
		Long totalScore = Long.valueOf(rHistoryRepo.getTotalScore(rankHistoryInfoDTO.getMemberId()));
		
		List<RankEntity> rankInfoList = rankRepo.findAll();
		
		System.out.println("++++++++++++++++++++member+++++++++++++++++: " + member);
		System.out.println("------------------------total----------------------: " + totalScore);
		for(int i = rankInfoList.size() - 1; i >= 0; i--) {
		    RankEntity rankInfo = rankInfoList.get(i);
		    
		    if(memberRank.getRankId() == rankInfoList.get(rankInfoList.size() - 1).getRankId()) {
		        return "지급 성공";
		    }
		    
		    if(rankInfo.getScore() <= totalScore 
		    		&& rankInfo.getRankId() > memberRank.getRankId()) {
		        memberRepo.updateRank(rankInfo, member.getMemberId());
		        return "지급 성공";
		    }
		}
		
		return "===";
	}


	@Override
	public List<AddressRankingProjection> getRankingByAddress(String memberId) {
		List<AddressRankingProjection> arlist = rHistoryRepo.getRankingByAddress(memberId);
		return arlist;
	}
	
	

}
