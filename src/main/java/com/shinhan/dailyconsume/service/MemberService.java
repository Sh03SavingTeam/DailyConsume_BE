package com.shinhan.dailyconsume.service;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.RankEntity;
import com.shinhan.dailyconsume.dto.MemberDTO;

public interface MemberService {

	//CRUD 기본작업
	String register(MemberDTO dto);
	
	MemberDTO findMember(String memberId);
	
	default MemberEntity dtoToEntity(MemberDTO dto) {
		RankEntity rank = RankEntity.builder().rankId(dto.getRankId()).build();
		
		MemberEntity member = MemberEntity.builder()
				.memberId(dto.getMemberId())
				.memberPw(dto.getMemberPw())
				.memberName(dto.getMemberName())
				.memberGender(dto.getMemberGender())
				.memberBirth(dto.getMemberBirth())
				.memberAccount(dto.getMemberAccount())
				.pointAmount(dto.getPointAmount())
				.memberImg(dto.getMemberImg())
				.rank(rank)
				.build();
		return member;
	}
	
	default MemberDTO entityToDTO(MemberEntity entity) {
		MemberDTO dto = MemberDTO.builder()
				.memberId(entity.getMemberId())
				.memberPw(entity.getMemberPw())
				.memberName(entity.getMemberName())
				.memberGender(entity.getMemberGender())
				.memberBirth(entity.getMemberBirth())
				.memberAccount(entity.getMemberAccount())
				.pointAmount(entity.getPointAmount())
				.memberImg(entity.getMemberImg())
				.rankId(entity.getRank().getRankId())
				.build();
		
		return dto;
	}
}
