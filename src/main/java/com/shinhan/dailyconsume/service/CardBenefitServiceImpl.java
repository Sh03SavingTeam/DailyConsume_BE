package com.shinhan.dailyconsume.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.dto.CardBenefitDTO;
import com.shinhan.dailyconsume.repository.CardBenefitRepository;

@Service
public class CardBenefitServiceImpl implements CardBenefitService {

	@Autowired
	CardBenefitRepository cardBenefitRepo;

	@Override
	public List<CardBenefitDTO> getCardBenefits(String cardName) {
		List<CardBenefitDTO> cardbenefitlist = cardBenefitRepo.findByCardName(cardName).stream().map(en->entityToDTO(en))
				.collect(Collectors.toList());
		return cardbenefitlist;
	}
	
	
	
}
