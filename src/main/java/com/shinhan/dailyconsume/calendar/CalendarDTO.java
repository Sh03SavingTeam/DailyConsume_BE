package com.shinhan.dailyconsume.calendar;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarDTO {
    private Long payId;
    private Long payAmount;
    private Timestamp payDate;
    private String consumeCategory;  // 소비 카테고리 이름
    private String cardNum;          // 카드 번호
    private String cardName;  		// 카드명    
    private String storeRegNum;      // 사업자 등록번호
    private String storeName;        // 상호명 (옵션)
    private int myPayCheck;			 // 이상결제 여부(1:정상, 0:이상)
    private String menuName;	//메뉴명
}