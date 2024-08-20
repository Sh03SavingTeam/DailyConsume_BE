package com.shinhan.dailyconsume.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.CardEntity;
import com.shinhan.dailyconsume.dto.CardDTO;
import com.shinhan.dailyconsume.repository.CardRepository;

@Service
public class CardServiceImpl implements CardService{
	
	@Autowired
	CardRepository cardRepo;
	
	@Override 
	public List<CardDTO> getCheckCards(){
		List<CardDTO> cardlist = cardRepo.findByCardType("check").stream().map(en->entityToDTO(en))
				.collect(Collectors.toList());
		
		return cardlist;
	}
	
	@Override
	public List<CardDTO> getCreditCards(){
		List<CardDTO> cardlist = cardRepo.findByCardType("credit").stream().map(en->entityToDTO(en))
				.collect(Collectors.toList());
		
		return cardlist;
	}
	
	@Override
	public CardDTO getCardInfo(String cardName) {
		CardEntity cardentity = cardRepo.findByCardName(cardName);
		return entityToDTO(cardentity);
	}

}
