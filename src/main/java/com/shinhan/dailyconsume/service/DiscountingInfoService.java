package com.shinhan.dailyconsume.service;

import java.util.List;

import com.shinhan.dailyconsume.dto.DiscountingDTO;
import com.shinhan.dailyconsume.dto.DiscountingInfoDTO;
import org.springframework.data.domain.Pageable;

public interface DiscountingInfoService {
	public DiscountingDTO getDiscountingInfos(Pageable pageable, String memberId);
}
