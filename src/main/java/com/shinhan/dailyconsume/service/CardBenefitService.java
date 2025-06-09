package com.shinhan.dailyconsume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.CardBenefitEntity;
import com.shinhan.dailyconsume.domain.CardEntity;
import com.shinhan.dailyconsume.dto.CardBenefitDTO;
import com.shinhan.dailyconsume.repository.CardBenefitRepository;

public interface CardBenefitService {
	
	//카드 혜택 목록
	List<CardBenefitDTO> getCardBenefits(String cardName);
	
	default CardBenefitEntity dtoToEntity(CardBenefitDTO dto) {
		CardBenefitEntity cardBenefitEntity = CardBenefitEntity.builder()
				.benefitId(dto.getBenefitId())
				.benefit(dto.getBenefit())
				.cardName(dto.getCardName())
				.build();
		
		return cardBenefitEntity;
	}
	
	default CardBenefitDTO entityToDTO(CardBenefitEntity entity) {
		CardBenefitDTO dto = CardBenefitDTO.builder()
				.benefitId(entity.getBenefitId())
				.benefit(entity.getBenefit())
				.cardName(entity.getCardName())
				.build();
		
		return dto;
	}

}
