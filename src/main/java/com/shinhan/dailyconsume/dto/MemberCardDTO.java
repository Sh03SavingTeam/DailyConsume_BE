package com.shinhan.dailyconsume.dto;

import java.sql.Timestamp;

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
public class MemberCardDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String cardNum;
	private Timestamp expirationDate;
}
