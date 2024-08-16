package com.shinhan.dailyconsume.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.MemberCardDTO;
import com.shinhan.dailyconsume.service.OCRService;

@CrossOrigin
@RestController
@RequestMapping("/card")
public class CardRestController {
	@Autowired
	OCRService ocrSevice;
	
	//로그인한 사용자의 카드 목록 조회
	//@GetMapping("/list")
	
	
	//카드 촬영 사진에 대한 ocr 데이터
	
	//카드 정보 등록
//	@PostMapping("/cardRegister")
//	MemberCardDTO memberCardRegister(@RequestBody MemberCardDTO memberCardDTO) {
//		
//	}
	
}
