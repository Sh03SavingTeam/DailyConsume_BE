package com.shinhan.dailyconsume.service.store;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.repository.MemberCardRepository;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.StoreRepository;

@Component
public class PayHistoryDataInsert implements CommandLineRunner{

	@Autowired
	StoreRepository storeRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	MemberCardRepository memberCardRepo;
	
	@Override
	public void run(String... args) throws Exception {
		List<Long> teenExcludedCategories = Arrays.asList(5L, 6L, 12L, 22L, 23L, 26L, 27L, 29L, 32L, 34L, 35L, 44L, 52L, 53L, 56L, 58L, 62L, 63L, 66L, 79L, 82L, 89L, 90L, 91L, 93L);
		List<Long> twentyMaleExcludedCategories = Arrays.asList(20L, 29L, 40L, 42L, 57L, 85L, 98L);
		List<Long> twentyFemaleExcludedCategories = Arrays.asList(6L,12L, 52L, 76L, 93L);

		List<StoreEntity> teenStores = storeRepo.getExcludedCategories(teenExcludedCategories);
		List<StoreEntity> twentyMaleStores = storeRepo.getExcludedCategories(twentyMaleExcludedCategories);
		List<StoreEntity> twentyFemaleStores = storeRepo.getExcludedCategories(twentyFemaleExcludedCategories);
		
		teenStores.stream().forEach(store -> System.out.println("regNum: " + store.getStoreRegNum()));
		twentyMaleStores.stream().forEach(store -> System.out.println("regNum: " + store.getStoreRegNum()));
		twentyFemaleStores.stream().forEach(store -> System.out.println("regNum: " + store.getStoreRegNum()));
		
		// MemberCard테이블 MemberEntity 컬럼 업데이트
//		List<MemberEntity> findAllMember = memberRepo.findAll();
//		List<MemberCardEntity> findAllMemberCard = memberCardRepo.findAll();
//		
//		int memberIdx = 0;
//		for(MemberCardEntity memberCard:findAllMemberCard) {
//			memberCard.setMember(findAllMember.get(memberIdx ++));
//			memberCardRepo.save(memberCard);
//		}
		
	}

}
