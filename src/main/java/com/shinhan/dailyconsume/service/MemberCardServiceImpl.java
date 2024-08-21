package com.shinhan.dailyconsume.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.dto.MemberCardDTO;
import com.shinhan.dailyconsume.repository.MemberCardRepository;
import com.shinhan.dailyconsume.repository.MemberRepository;

@Service
public class MemberCardServiceImpl implements MemberCardService {

	@Autowired
	MemberCardRepository memberCardRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	@Override
	public String register(MemberCardDTO dto) {
		MemberCardEntity newMemberCardEntity = memberCardRepo.save(dtoToEntity(dto));
		return newMemberCardEntity.getCardNum();
	}
	
	
	public List<MemberCardDTO> selectByMemberId(String memberId){
		MemberEntity member = memberRepo.findById(memberId).orElse(null);
		List<MemberCardDTO> MemberCardlist= memberCardRepo.findByMember(member).stream().map(en->entityToDTO(en))
				.collect(Collectors.toList());
		
		return MemberCardlist;
	}
	
	@Override
	public MemberCardDTO selectByCardNum(String cardNum) {
		MemberCardEntity memberCard = memberCardRepo.findByCardNum(cardNum);
		return entityToDTO(memberCard);
		
	}

	@Override
	public void delete(String cardNum) {
		memberCardRepo.deleteById(cardNum);
		// TODO Auto-generated method stub
		
	}

}
