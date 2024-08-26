package com.shinhan.dailyconsume.dto;

import java.sql.Date;

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
public class MemberDTO {	
	@Id
	String memberId;
	String memberPw;
	String memberName;
	String memberGender;
	Date memberBirth;
	String memberAccount;
	Long pointAmount;
	String memberImg;
	Long rankId;

}
