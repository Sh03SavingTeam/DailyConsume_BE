package com.shinhan.dailyconsume.service;

import java.util.List;
import java.util.stream.Collectors;

import com.shinhan.dailyconsume.dto.DiscountingDTO;
import com.shinhan.dailyconsume.repository.MemberRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.dto.DiscountingInfoDTO;
import com.shinhan.dailyconsume.repository.DiscountingInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiscountingInfoServiceImpl implements DiscountingInfoService {
	
	final DiscountingInfoRepository dRepo;
	private final MemberRepository mRepo;

	//할인 정보 조회
	@Override
	public DiscountingDTO getDiscountingInfos(Pageable pageable, String memberId) {

		//가장 많은 지출이 있는 카테고리 구하기

		return null;

	}



}
