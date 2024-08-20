package com.shinhan.dailyconsume.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.AddressEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.dto.AddrDTO;

public interface AddrService {

	// CRUD 작업
	// 1.입력
	String addrRegister(AddrDTO dto);

	// 2.조회
	List<AddrDTO> getAddrList(String memberId);

	// 3.삭제
	void deleteAddr(Long addrId);

	default AddressEntity dtoToEntity(AddrDTO dto) {
		MemberEntity member = MemberEntity.builder().memberId(dto.getMemberId()).build();

		AddressEntity address = AddressEntity.builder().addrId(dto.getAddrId()).addrName(dto.getAddrName())
				.addrDetail(dto.getAddrDetail()).member(member).build();

		return address;
	}

	default AddrDTO entityToDTO(AddressEntity entity) {
		AddrDTO dto = AddrDTO.builder().addrId(entity.getAddrId()).addrName(entity.getAddrName())
				.addrDetail(entity.getAddrDetail()).memberId(entity.getMember().getMemberId()).build();

		return dto;
	}
}
