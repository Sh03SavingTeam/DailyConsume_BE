package com.shinhan.dailyconsume.service;

import java.util.List;

import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.dto.PayHistoryDTO;

public interface PayHistoryService {
	/**
     * 특정 회원의 특정 월의 결제 내역을 조회합니다.
     * 
     * @param memberId 회원 ID
     * @param month 월 (1월은 1, 12월은 12)
     * @param year 연도 (예: 2024)
     * @return 해당 월의 결제 내역 리스트
     */
    
	// 특정 회원의 특정 월의 결제 내역을 조회
    List<PayHistoryDTO> selectByMemberIdAndMonth(String memberId, int month, int year);

    // PayHistoryDTO를 PayHistoryEntity로 변환
    default PayHistoryDTO entityToDTO(PayHistoryEntity entity) {
        return PayHistoryDTO.builder()
                .payId(entity.getPayId())
                .payAmount(entity.getPayAmount())
                .payDate(entity.getPayDate())               
                .consumeCategoryId(entity.getConsumeCategory() != null ? entity.getConsumeCategory().getConsumeId() : null)
                .cardNum(entity.getCard() != null ? entity.getCard().getCardNum() : null)
                .strRegNum(entity.getStore() != null ? entity.getStore().getStrRegNum() : null)
                .storeName(entity.getStore() != null ? entity.getStore().getStoreName() : null)
                .build();
    }
}
