package com.shinhan.dailyconsume.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.shinhan.dailyconsume.dto.AddrDTO;
import com.shinhan.dailyconsume.service.AddrService;

@CrossOrigin
@RestController
@RequestMapping("/api/address")
public class AddrRestController {

	@Autowired
	AddrService addrService;
	
	//주소 등록
	@PostMapping("/addrRegister")
	public AddrDTO addrRegister(@RequestBody AddrDTO addrDTO) {
		System.out.println(addrDTO);
		String addrName = addrService.addrRegister(addrDTO);
		return AddrDTO.builder().build();
	}
	
	//등록 주소 목록 조회
	@GetMapping("/addrList")
	public List<AddrDTO> getAddrList(@RequestParam String memberId){
		List<AddrDTO> addrList = addrService.getAddrList(memberId);
		return addrList;
	}
	
	//선택한 주소 삭제
	@DeleteMapping("/addrDelete")
	public void addrDelete(@RequestParam Long addrId) {
		System.out.println("삭제 요청한 addrId : "+addrId);
		addrService.deleteAddr(addrId);
	}
}
