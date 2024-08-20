package com.shinhan.dailyconsume.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RankingDTO implements RankingProjection{
	private String memberId;
	private String memberName;
	private int rankId; 
	private int totalAmount;
	private int rankNum;
}
