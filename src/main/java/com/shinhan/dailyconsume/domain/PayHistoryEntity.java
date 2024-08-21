package com.shinhan.dailyconsume.domain;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "t_pay_history")
public class PayHistoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payId;
	
	private Long payAmount;
	
	@CreationTimestamp
	private Timestamp payDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumeId")
	private ConsumeCategoryEntity consumeCategory;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cardNum")
	private MemberCardEntity card;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="strRegNum")
	private StoreEntity store;
}
