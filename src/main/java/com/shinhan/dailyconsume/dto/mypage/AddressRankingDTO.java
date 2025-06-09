package com.shinhan.dailyconsume.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRankingDTO implements AddressRankingProjection {
	private String memberId;
	private String memberName;
	private String rankId;
	private int totalAmount;
	private int addrId;
	private String addrDetail;
	private int rankNum;
	
}
