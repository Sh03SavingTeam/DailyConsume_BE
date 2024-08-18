package com.shinhan.dailyconsume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.MemberCardDTO;
import com.shinhan.dailyconsume.service.MemberCardService;
import com.shinhan.dailyconsume.service.OCRService;

@CrossOrigin
@RestController
@RequestMapping("/api/card")
public class CardRestController {
	
	@Autowired
	OCRService ocrService;

	@Autowired
	MemberCardService memberCardService;

	// 로그인한 사용자의 카드 목록 조회
	// @GetMapping("/list")

	// 카드 촬영 사진에 대한 ocr 데이터
	@PostMapping("/cardOCR")
	public void ocrService() {
		ocrService.cardOCRService();
	}

	// 카드 정보 등록
	@PostMapping("/cardRegister")
	public MemberCardDTO memberCardRegister(@RequestBody MemberCardDTO memberCardDTO) {
		System.out.println(memberCardDTO);
		
		String cardNum = memberCardService.register(memberCardDTO);
		return MemberCardDTO.builder().build();
	}

	@DeleteMapping("/cardDelete/{cardNum}")
	public void cardDelete(@PathVariable("cardNum") String cardNum) {
		memberCardService.delete(cardNum);
	}
}
