package com.shinhan.dailyconsume.domain;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
	String expirationDate;
	Long cvc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cardName")
	private CardEntity card;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memberId")
	private MemberEntity member;
	

	@OneToMany(mappedBy = "memberCard", fetch = FetchType.LAZY)
	private List<PayHistoryEntity> payHistories;
}
