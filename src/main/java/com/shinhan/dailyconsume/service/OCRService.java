package com.shinhan.dailyconsume.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OCRService {

	@Value("$(CardClovaOCR_URL")
	private String cardOCRURL;
	@Value("$(CardClovaOCR_SECRET_KEY")
	private String cardOCRKey;

	@Value("$(ReciptClovaOCR_URL")
	private String reciptOCRURL;
	@Value("$(ReciptClovaOCR_SECRET_KEY")
	private String reciptOCRKey;

	public void cardOCRService() {
		try {

			// 카드 OCR api 연결 설정
			URL cardOCRurl = new URL(cardOCRURL);
			HttpURLConnection con = (HttpURLConnection) cardOCRurl.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");

			// 헤더 설정
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("X-OCR-SECRET", cardOCRKey);

			// JSON 페이로드 생성
			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			json.put("resultType", "string");

			// 이미지 정보 설정
			JSONArray images = new JSONArray();
			JSONObject image = new JSONObject();
			image.put("format", "jpg");
			image.put("name", "medium");
			image.put("url", "https://shinhands3rd-project2.s3.ap-southeast-2.amazonaws.com/CardIMG/cardexample.jpg");
			images.add(image);

			json.put("images", images);
			String postParams = json.toString();

			// POST 요청 전송
			con.connect();
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postParams.getBytes("UTF-8"));
				wr.flush();
			}

			// 응답 코드 확인
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 성공
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 오류
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			// 응답 읽기
			StringBuilder response = new StringBuilder();
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reciptOCRService() {
		try {

			// 카드 OCR api 연결 설정
			URL cardOCRurl = new URL(reciptOCRURL);
			HttpURLConnection con = (HttpURLConnection) cardOCRurl.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");

			// 헤더 설정
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("X-OCR-SECRET", reciptOCRKey);

			// JSON 페이로드 생성
			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			json.put("resultType", "string");

			// 이미지 정보 설정
			JSONArray images = new JSONArray();
			JSONObject image = new JSONObject();
			image.put("format", "jpg");
			image.put("name", "medium");
			image.put("url", "https://shinhands3rd-project2.s3.ap-southeast-2.amazonaws.com/CardIMG/cardexample.jpg");
			images.add(image);

			json.put("images", images);
			String postParams = json.toString();

			// POST 요청 전송
			con.connect();
			try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
				wr.write(postParams.getBytes("UTF-8"));
				wr.flush();
			}

			// 응답 코드 확인
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 성공
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 오류
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			// 응답 읽기
			StringBuilder response = new StringBuilder();
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
