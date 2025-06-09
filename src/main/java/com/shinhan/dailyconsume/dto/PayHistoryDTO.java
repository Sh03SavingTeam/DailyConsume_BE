package com.shinhan.dailyconsume.dto;


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
    
}
