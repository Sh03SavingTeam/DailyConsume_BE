package com.shinhan.dailyconsume.domain;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_member")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberEntity {
	
	@Id
	String memberId;
	
	String memberPw;
	String memberName;
	String memberGender;
	Date memberBirth;
	String memberAccount;
	Long pointAmount;
	
	@OneToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "rankId")
	RankEntity rank;
	
	
	
}
