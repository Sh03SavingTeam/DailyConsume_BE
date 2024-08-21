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

//나의 소비 dto
public class PayHistoryDTO {
	
//	private Long payId;
//	private Long payAmount;
//	private Timestamp payDate;
//	private Long consume_id;
	
	private int payAmount;
	private String consumeCategory;
	private float percentage;
	
	
	public void addPayAmount(int amount) {
        this.payAmount += amount;
    }
	
	public void avgAmount(int count) {
		this.payAmount /= count;
	}

	public PayHistoryDTO(int payAmount, String consumeCategory) {
		super();
		this.payAmount = payAmount;
		this.consumeCategory = consumeCategory;
	}
	
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
