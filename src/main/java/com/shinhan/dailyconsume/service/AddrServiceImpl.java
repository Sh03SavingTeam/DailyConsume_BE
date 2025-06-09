package com.shinhan.dailyconsume.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.dailyconsume.domain.AddressEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.dto.AddrDTO;
import com.shinhan.dailyconsume.repository.AddrRepository;
import com.shinhan.dailyconsume.repository.MemberRepository;

@Service
public class AddrServiceImpl implements AddrService {
	@Autowired
	AddrRepository addrRepo;

	@Autowired
	MemberRepository memberRepo;

	@Override
	public String addrRegister(AddrDTO dto) {
		AddressEntity newaddressEntity = addrRepo.save(dtoToEntity(dto));
		return newaddressEntity.getAddrName();
	}
	
	@Override
	public void addrUpdate(AddrDTO dto) {
		addrRepo.findById(dto.getAddrId()).ifPresent(en->{
			en.setAddrDefault(dto.getAddrDefault());
			addrRepo.save(en);
		});
		
	}

	public List<AddrDTO> getAddrList(String memberId) {
		MemberEntity member = memberRepo.findById(memberId).orElse(null);
		List<AddrDTO> addrlist = addrRepo.findByMember(member).stream().map(en -> entityToDTO(en))
				.collect(Collectors.toList());

		return addrlist;
	}

	public void deleteAddr(Long addrId) {
		addrRepo.deleteById(addrId);
	}
}
