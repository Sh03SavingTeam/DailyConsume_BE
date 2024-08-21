package com.shinhan.dailyconsume.service;

import java.util.List;
import java.util.stream.Collectors;

import com.shinhan.dailyconsume.dto.DiscountingDTO;
import com.shinhan.dailyconsume.repository.PayHistoryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.dto.DiscountingInfoDTO;
import com.shinhan.dailyconsume.repository.DiscountingInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiscountingInfoServiceImpl implements DiscountingInfoService {
	
	final DiscountingInfoRepository dRepo;
	private final PayHistoryRepository pRepo;

	//할인 정보 조회
	@Override
	public DiscountingDTO getDiscountingInfos(Pageable pageable, String memberId) {

		//가장 많은 지출이 있는 카테고리 구하기
		List<Object[]> result = pRepo.findCategoryPayByMember(memberId);

		if (!result.isEmpty()) {

			String category = (String) result.get(0)[0];
			int categoryId = (Integer) result.get(0)[1];

			//카테고리별 맞춤 할인 정보 구하기
			switch (categoryId) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					break;
			}
			//return
		}

		return null;

		}

}
