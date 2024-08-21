package com.shinhan.dailyconsume.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayHistoryDTO {
	private Long payId;          // 결제내역 ID
    private Long payAmount;      // 결제 금액
    private Timestamp payDate;   // 결제 일자
    
    private Long consumeCategoryId;  // 소비 카테고리 ID
    private String consumeCategoryName; // 소비 카테고리 이름 (옵션)
    
    private String cardNum;      // 카드 번호
    private String cardName;     // 카드 이름 (옵션)
    
    private String strRegNum;  // 사업자 등록번호
    private String storeName;    // 상호명 (옵션)
    
    
}
