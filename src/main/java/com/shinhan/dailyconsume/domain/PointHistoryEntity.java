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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "t_point_history")
public class PointHistoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pHistoryId;
	
	private Long divNum;
	private Long amount;
	
	@CreationTimestamp
	private Timestamp pointRegDate;
	
	private String cmt;
	
	@ManyToOne( fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId")
	private MemberEntity member;
	
	
}
