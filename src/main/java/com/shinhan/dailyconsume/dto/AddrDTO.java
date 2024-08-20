package com.shinhan.dailyconsume.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddrDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addrId;
	private String addrName;
	private String addrDetail;
	private String memberId;
}
