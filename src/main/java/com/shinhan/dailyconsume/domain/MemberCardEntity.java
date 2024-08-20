package com.shinhan.dailyconsume.domain;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_member_card")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MemberCardEntity {
	@Id
	String cardNum;
	Timestamp expirationDate;
	Long cvc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cardId")
	private CardEntity card;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memberId")
	private MemberEntity member;

	@OneToMany(mappedBy = "memberCard", fetch = FetchType.LAZY)
	private List<PayHistoryEntity> payHistories;

}
