package com.shinhan.dailyconsume.domain;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "t_address")
public class AddressEntity {
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addrId;
	private String addrName;
	private String addrDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="memberId")
	private MemberEntity member;
}
