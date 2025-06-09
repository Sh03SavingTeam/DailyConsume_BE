package com.shinhan.dailyconsume.controller;

import java.util.List;
import java.util.Map;

import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.domain.CardEntity;
import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.dto.CardBenefitDTO;
import com.shinhan.dailyconsume.dto.CardDTO;
import com.shinhan.dailyconsume.dto.CardOCRDTO;
import com.shinhan.dailyconsume.dto.MemberCardDTO;
import com.shinhan.dailyconsume.service.CardBenefitService;
import com.shinhan.dailyconsume.service.CardService;
import com.shinhan.dailyconsume.service.MemberCardService;
import com.shinhan.dailyconsume.service.OCRService;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
@RequestMapping("/api/card")
public class CardRestController {
	
	@Autowired
	OCRService ocrService;

	@Autowired
	CardService cardService;
	
	@Autowired
	MemberCardService memberCardService;
	
	@Autowired
	CardBenefitService cardBenefitService;

	// 카드 촬영 사진에 대한 OCR
	@PostMapping("/cardOCR")
	@ResponseBody
	public CardOCRDTO ocrService(@RequestBody Map<String, String> requestData) {
		//클라이언트로부터 fileName 받기
		String fileName = requestData.get("fileName");
		
		JSONObject jsonobject = ocrService.cardOCRService(fileName);
		System.out.println(jsonobject);
		
		// JSON에서 필요한 데이터 추출
        JSONObject firstImage = jsonobject.getJSONArray("images").getJSONObject(0);
        JSONObject result = firstImage.getJSONObject("creditCard").getJSONObject("result");

        String number = result.getJSONObject("number").getString("text");
        String validThru = result.getJSONObject("validThru").getString("text");

        // CardOCRDTO 객체 생성
        CardOCRDTO cardOCRDTO = CardOCRDTO.builder()
                                           .number(number)
                                           .validThru(validThru)
                                           .build();
        
        // DTO 반환
        return cardOCRDTO;
		
		
	}
	
	//체크카드 목록
	@GetMapping("/checkCardList")
	public List<CardDTO> getCheckCards(){
		return cardService.getCheckCards();
	}
	
	
	//신용카드 목록
	@GetMapping("/creditCardList")
	public List<CardDTO> getCreditCards() {
		return cardService.getCreditCards();
	}
	
	//전체 카드 정보 목록
	@GetMapping("/getAllCardInfo")
	public List<CardDTO> getAllCardInfo(){
		return cardService.getCardInfos();
	}
	
	
	//선택한 카드 정보(카드번호 -> 이미지, 카드명)
	@GetMapping("/getCardInfo")
	public CardDTO getCardInfo(@RequestParam String cardNum) {
		System.out.println("전달받은 카드번호 : "+cardNum);
		MemberCardDTO memberCardDTO = memberCardService.selectByCardNum(cardNum);
		
		String cardName = memberCardDTO.getCardName();
		
		return cardService.getCardInfo(cardName);
	}
	
	//선택한 카드의 혜택 목록 조회(카드명 -> 혜택목록)
	@GetMapping("/getCardBenefit")
	public List<CardBenefitDTO> getCardBenefits(@RequestParam String cardName){
		System.out.println("카드 이름 : "+cardName);
		List<CardBenefitDTO> cardbenefitList = cardBenefitService.getCardBenefits(cardName);
		
		return cardbenefitList;
	}
	
	

	// 카드 정보 등록
	@PostMapping("/cardRegister")
	public MemberCardDTO memberCardRegister(@RequestBody MemberCardDTO memberCardDTO) {
		System.out.println(memberCardDTO);
		
		
		String cardNum = memberCardService.register(memberCardDTO);
		return MemberCardDTO.builder().build();
	}
	
	// 로그인한 회원이 등록한 카드 목록 조회
	@GetMapping("/memberCardList")
	public List<MemberCardDTO> getMemberCardList(@RequestParam String memberId){
		List<MemberCardDTO> memberCardList = memberCardService.selectByMemberId(memberId);
		
		return memberCardList;
	}
	

	//해당 카드 정보 삭제
	@DeleteMapping("/delete")
	public void cardDelete(@RequestParam String cardNum) {
		System.out.println("삭제요청한 cardNum : "+cardNum);
		memberCardService.delete(cardNum);
	}
}
