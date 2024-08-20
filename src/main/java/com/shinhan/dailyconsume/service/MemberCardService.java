package com.shinhan.dailyconsume.service;

import java.util.List;

import com.shinhan.dailyconsume.domain.CardEntity;
import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.dto.MemberCardDTO;

public interface MemberCardService {
	
	//CRUD 작업 제공
	//1.입력
	String register(MemberCardDTO dto);
	
	//2.조회
	List<MemberCardDTO> selectByMemberId(String memberId);
	
	//3.상세조회
	MemberCardDTO selectByCardNum(String cardNum);
	
	//4.삭제
	void delete(String cardNum);
	

	default MemberCardEntity dtoToEntity(MemberCardDTO dto) {
		MemberEntity member = MemberEntity.builder().memberId(dto.getMemberId()).build();
		CardEntity card = CardEntity.builder().cardName(dto.getCardName()).build();
		
		MemberCardEntity memberCard = MemberCardEntity.builder()
				.cardNum(dto.getCardNum())
				.expirationDate(dto.getExpirationDate())
				.cvc(dto.getCvc())
				.member(member)
				.card(card)
				.build();
		return memberCard;
		
	}
	
	default MemberCardDTO entityToDTO(MemberCardEntity entity) {
		MemberCardDTO dto = MemberCardDTO.builder()
				.cardNum(entity.getCardNum())
				.expirationDate(entity.getExpirationDate())
				.cvc(entity.getCvc())
				.cardName(entity.getCard().getCardName())
				.memberId(entity.getMember().getMemberId())
				.build();
				
		return dto;
	}
}
