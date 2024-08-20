package com.shinhan.dailyconsume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.CardEntity;
import com.shinhan.dailyconsume.dto.CardDTO;
import com.shinhan.dailyconsume.repository.CardRepository;


public interface CardService {
	
	//체크카드 목록
	List<CardDTO> getCheckCards();
	
	//신용카드 목록
	List<CardDTO> getCreditCards();
	
	//카드정보
	CardDTO getCardInfo(String cardName);
	
	default CardEntity dtoToEntity(CardDTO dto) {
		CardEntity carden = CardEntity.builder()
				.cardName(dto.getCardName())
				.cardImgUrl(dto.getCardImgUrl())
				.cardPageUrl(dto.getCardPageUrl())
				.cardType(dto.getCardType())
				.build();
		
		return carden;
	}
	
	default CardDTO entityToDTO(CardEntity entity) {
		CardDTO dto = CardDTO.builder()
				.cardName(entity.getCardName())
				.cardImgUrl(entity.getCardImgUrl())
				.cardPageUrl(entity.getCardPageUrl())
				.cardType(entity.getCardType())
				.build();
		
		return dto;
	}
}
