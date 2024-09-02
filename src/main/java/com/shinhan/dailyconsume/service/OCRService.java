package com.shinhan.dailyconsume.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Service
public class OCRService {

	@Value("${CardClovaOCR_URL}")
	private String cardOCRURL;
	@Value("${CardClovaOCR_SECRET_KEY}")
	private String cardOCRKey;

	@Value("${ReceiptClovaOCR_URL}")
	private String reciptOCRURL;
	@Value("${ReceiptClovaOCR_SECRET_KEY}")
	private String reciptOCRKey;

	private final S3Client s3Client;

	@Autowired
	public OCRService(S3Client s3Client) {
		this.s3Client = s3Client;
	}

	public JSONObject cardOCRService(String fileName) {

		String bucketName = "shinhands3rd-project2";
		String keyName = "CardIMG/"+fileName;
		//String keyName = "CardIMG/cardimg_m001_1725264697619.jpg";
		StringBuilder response = new StringBuilder();
		System.out.println("사진 경로 : "+ bucketName+" "+keyName);

		try {
			// S3에서 이미지 가져오기
			String encodedString = getBase64EncodedFile(bucketName, keyName);

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
			image.put("data", encodedString);
			images.put(image);

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
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			// System.out.println(response);
			br.close();
			return new JSONObject(response.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public JSONObject reciptOCRService(String fileName) {

		String bucketName = "shinhands3rd-project2";
		String keyName = "ReceiptIMG/" + fileName;
		//String keyName = "ReceiptIMG/receiptex.jpg";
		StringBuilder response = new StringBuilder();

		try {
			// S3에서 이미지 가져오기
			String encodedString = getBase64EncodedFile(bucketName, keyName);

			// 영수증 OCR api 연결 설정
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
			image.put("data", encodedString);
			images.put(image);

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
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			// System.out.println(response);
			br.close();
			return new JSONObject(response.toString());

		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private String getBase64EncodedFile(String bucketName, String keyName) throws IOException {
		try (ResponseInputStream<?> s3Object = s3Client
				.getObject(GetObjectRequest.builder().bucket(bucketName).key(keyName).build())) {

			// 객체 데이터를 읽어 바이트 배열로 변환
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = s3Object.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}

			// 바이트 배열을 Base64로 인코딩
			byte[] fileContent = baos.toByteArray();
			return Base64.getEncoder().encodeToString(fileContent);
		}
	}
}
