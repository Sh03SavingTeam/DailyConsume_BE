package com.shinhan.dailyconsume.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.dto.MemberDTO;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.RankRepository;

import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	RankRepository rankRepo;
	
	@Override
	public String register(MemberDTO dto) {
		
		Optional<MemberEntity> exsistingMember = memberRepo.findById(dto.getMemberId());
		
		MemberEntity member = memberRepo.save(dtoToEntity(dto));
		return member.getMemberId();
	};
	
	@Override
	public MemberDTO findMember(String memberId) {
		MemberEntity member = memberRepo.findByMemberId(memberId);
		return entityToDTO(member);
	}
	
	@Transactional
	@Override
	public void updateAllMembersRankToOne() {
		memberRepo.updateAllMembersRankToOne();
		
	};
}
