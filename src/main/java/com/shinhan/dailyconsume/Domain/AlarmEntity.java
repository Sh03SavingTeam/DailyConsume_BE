package com.shinhan.dailyconsume.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "t_alarm")
public class AlarmEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long alarmId;
	
	private String content;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payId")
	private PayHistoryEntity payHistory;
	
}
