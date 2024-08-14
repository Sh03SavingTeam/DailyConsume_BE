package com.shinhan.dailyconsume.domain;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
	Timestamp expirationDate;
	
}
