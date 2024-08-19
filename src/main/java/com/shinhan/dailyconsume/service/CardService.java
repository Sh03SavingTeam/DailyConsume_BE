package com.shinhan.dailyconsume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.CardEntity;
import com.shinhan.dailyconsume.repository.CardRepository;

@Service
public class CardService {
	
	@Autowired
	private CardRepository cardRepository;
	
	public List<CardEntity> getCheckCards(){
		return cardRepository.findByCardType("check");
	}

	public List<CardEntity> getCreditCards(){
		return cardRepository.findByCardType("credit");
	}
}
