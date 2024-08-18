package com.shinhan.dailyconsume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.dto.MemberCardDTO;
import com.shinhan.dailyconsume.repository.MemberCardRepository;

@Service
public class MemberCardServiceImpl implements MemberCardService {

	@Autowired
	MemberCardRepository memberCardRepo;
	
	@Override
	public String register(MemberCardDTO dto) {
		MemberCardEntity newMemberCardEntity = memberCardRepo.save(dtoToEntity(dto));
		return newMemberCardEntity.getCardNum();
	}

	@Override
	public void delete(String card) {
		// TODO Auto-generated method stub
		
	}

}
