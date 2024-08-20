package com.shinhan.dailyconsume.controller;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shinhan.dailyconsume.dto.ReceiptOCRDTO;
import com.shinhan.dailyconsume.service.OCRService;

@CrossOrigin
@RestController
@RequestMapping("/api/receipt")
public class ReceiptRestController {
	@Autowired
	OCRService ocrService;

	@PostMapping("/receiptOCR")
	@ResponseBody
	public ReceiptOCRDTO ocrService(@RequestBody Map<String, String> requestData) {
		// 클라이언트로부터 fileName 받기
		String fileName = requestData.get("fileName");

		JSONObject jsonobject = ocrService.reciptOCRService(fileName);
		System.out.println(jsonobject);

		// JSON에서 필요한 데이터 추출
		// images 배열에서 첫 번째 객체 추출
		JSONArray imagesArray = jsonobject.getJSONArray("images");
		JSONObject firstImage = imagesArray.getJSONObject(0);

		// receipt 객체에서 storeInfo 추출
		JSONObject receipt = firstImage.getJSONObject("receipt");
		JSONObject result = receipt.getJSONObject("result");
		JSONObject storeInfo = result.getJSONObject("storeInfo");

		// storeInfo에서 name 객체 추출 후, text 값 추출
		JSONObject nameObject = storeInfo.getJSONObject("name");
		String name = nameObject.getString("text");

		// storeInfo에서 addresses 배열 추출 후, 첫 번째 객체의 text 값 추출
		JSONArray addressesArray = storeInfo.getJSONArray("addresses");
		JSONObject addressObject = addressesArray.getJSONObject(0);
		String address = addressObject.getString("text");

		// storeInfo에서 bizNum 객체 추출 후, text 값 추출
		JSONObject bizNumObject = storeInfo.getJSONObject("bizNum");
		String bizNum = bizNumObject.getString("text");

		ReceiptOCRDTO receiptOCRDTO = ReceiptOCRDTO.builder()
				.name(name).addresses(address).bizNum(bizNum).build();
		
		
		return receiptOCRDTO;

	}

}
