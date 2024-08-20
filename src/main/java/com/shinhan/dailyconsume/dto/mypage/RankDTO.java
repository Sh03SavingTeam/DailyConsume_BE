package com.shinhan.dailyconsume.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankDTO {
	private String memberName;
	private String rankName;
	private String rankImg;
	private int amount;
	private int nextAmount;
	
	
}
