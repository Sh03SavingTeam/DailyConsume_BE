package com.shinhan.dailyconsume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.dto.DiscountingInfoDTO;
import com.shinhan.dailyconsume.repository.DiscountingInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiscountingInfoServiceImpl implements DiscountingInfoService {
	
	final DiscountingInfoRepository dRepo;
	
	@Override
	public List<DiscountingInfoDTO> getDiscountingInfos() {
		return null;
	}

}
