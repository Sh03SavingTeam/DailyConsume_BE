package com.shinhan.dailyconsume.service.discount;

import com.shinhan.dailyconsume.dto.discount.DiscountingDTO;
import org.springframework.data.domain.Pageable;

public interface DiscountingInfoService {
	// 회원별 맞춤 할인 정보 가져오기
	public DiscountingDTO getDiscountingInfos(Pageable pageable, String memberId);
}
