package com.shinhan.dailyconsume.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.shinhan.dailyconsume.domain.ConsumeCategoryEntity;
import com.shinhan.dailyconsume.dto.DiscountingDTO;
import com.shinhan.dailyconsume.dto.PayHistoryDTO;
import com.shinhan.dailyconsume.repository.ConsumeCategoryRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.dto.DiscountingInfoDTO;
import com.shinhan.dailyconsume.repository.DiscountingInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DiscountingInfoServiceImpl implements DiscountingInfoService {
	
	final DiscountingInfoRepository dRepo;
	private final ConsumeCategoryRepository cRepo;
	private final PayHistoryService pService;

	//할인 정보 조회
	@Override
	public DiscountingDTO getDiscountingInfos(Pageable pageable, String memberId) {

		//가장 많은 지출이 있는 카테고리 구하기
		List<PayHistoryDTO> payHistories = pService.getPayHistory(memberId);

		if (!payHistories.isEmpty()) {

			payHistories.sort(Comparator.comparing(PayHistoryDTO::getPayAmount).reversed());

			System.out.println(payHistories.toString());

			String category = payHistories.get(0).getConsumeCategory();

			ConsumeCategoryEntity consumeCategory = cRepo.findByConsumeName(category);

			//카테고리별 맞춤 할인 정보 구하기
			List<DiscountingInfoDTO> discountingDTOS = dRepo.findByConsumeCategory(consumeCategory).stream()
					.map(discountingInfoEntity -> new DiscountingInfoDTO(discountingInfoEntity))
					.collect(Collectors.toList());

			//할인 정보 총 개수
			int totalCount = discountingDTOS.size();

			List<DiscountingInfoDTO> pagingInfos = new ArrayList<>();

			int startIndex = (int) pageable.getOffset();
			int endIndex = Math.min(startIndex + pageable.getPageSize(), totalCount);

			List<DiscountingInfoDTO> paginatedInfos = new ArrayList<>(discountingDTOS).subList(startIndex, endIndex);

			for(DiscountingInfoDTO discountingInfoDTO : paginatedInfos){
				pagingInfos.add(discountingInfoDTO);
			}

			DiscountingDTO discountingDTO = new DiscountingDTO(category, totalCount, pagingInfos);

			return discountingDTO;

		}

		return null;

		}

}
