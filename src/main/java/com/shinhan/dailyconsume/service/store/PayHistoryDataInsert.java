package com.shinhan.dailyconsume.service.store;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shinhan.dailyconsume.domain.ConsumeCategoryEntity;
import com.shinhan.dailyconsume.domain.MemberCardEntity;
import com.shinhan.dailyconsume.domain.MemberEntity;
import com.shinhan.dailyconsume.domain.PayHistoryEntity;
import com.shinhan.dailyconsume.domain.StoreEntity;
import com.shinhan.dailyconsume.repository.MemberCardRepository;
import com.shinhan.dailyconsume.repository.MemberRepository;
import com.shinhan.dailyconsume.repository.PayHistoryRepository;
import com.shinhan.dailyconsume.repository.StoreRepository;

import jakarta.transaction.Transactional;

//@Component
public class PayHistoryDataInsert implements CommandLineRunner{

	@Autowired
	StoreRepository storeRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	MemberCardRepository memberCardRepo;
	
	@Autowired
	PayHistoryRepository payHistoryRepo;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		// MemberCard테이블 MemberEntity 컬럼 업데이트 첫 실행 시 주석 풀어주세요!
		//memberCardUpdate();
		
		List<Long> teenExcludedCategories = Arrays.asList(5L, 6L, 12L, 22L, 23L, 26L, 27L, 29L, 32L, 34L, 35L, 44L, 52L, 53L, 56L, 58L, 62L, 63L, 66L, 79L, 82L, 89L, 90L, 91L, 93L);
		List<Long> twentyMaleExcludedCategories = Arrays.asList(20L, 29L, 40L, 42L, 57L, 85L, 98L);
		List<Long> twentyFemaleExcludedCategories = Arrays.asList(6L,12L, 52L, 76L, 93L);

		List<StoreEntity> teenStores = storeRepo.getExcludedCategories(teenExcludedCategories);
		List<StoreEntity> twentyMaleStores = storeRepo.getExcludedCategories(twentyMaleExcludedCategories);
		List<StoreEntity> twentyFemaleStores = storeRepo.getExcludedCategories(twentyFemaleExcludedCategories);
		
		teenStores.stream().forEach(store -> System.out.println("regNum: " + store.getStoreRegNum()));
		twentyMaleStores.stream().forEach(store -> System.out.println("regNum: " + store.getStoreRegNum()));
		twentyFemaleStores.stream().forEach(store -> System.out.println("regNum: " + store.getStoreRegNum()));
		
		
		
		List<MemberEntity>teenMember = memberRepo.teenMember();
		List<MemberEntity>twentyMaleMember = memberRepo.twentyMaleMember();
		List<MemberEntity>twentyFemaleMember = memberRepo.twentyFemaleMember();
		
		teenMember = memberEntitySetCard(teenMember);
		twentyMaleMember = memberEntitySetCard(twentyMaleMember);
		twentyFemaleMember = memberEntitySetCard(twentyFemaleMember);
		
		
		
		for(int i = 0; i < 300; i++) {
			
			int teenExcludedRandomIdx = (int) (Math.random() * (teenStores.size() -1));;
			int twentyMaleExcludedRandomIdx = (int) (Math.random() * (twentyMaleStores.size() - 1));;
			int twentyFemaleExcludedRandomIdx  = (int) (Math.random() * (twentyFemaleStores.size() - 1));;
			
			int teenMemberRandomIdx = (int) (Math.random() * (teenMember.size() - 1));
			int twentyMaleRandomIdx = (int) (Math.random() * (twentyMaleMember.size() - 1));
			int twentyFemaleRandomIdx = (int) (Math.random() * (twentyFemaleMember.size() - 1));
			
			int teenRandomPayAmout = (int) ((Math.random() * 590) + 10)* 100;
			int twentyMaleRandomPayAmout = (int) ((Math.random() * 1490) + 10)* 100;
			int twentyFemaleRandomPayAmout = (int) ((Math.random() * 1490) + 10)* 100;
			
			ConsumeCategoryEntity consumeCateEntity = ConsumeCategoryEntity.builder()
					.consumeId(1L)
					.build();
			
			PayHistoryEntity teenEntity = PayHistoryEntity.builder()
					.payAmount(Long.valueOf(teenRandomPayAmout))
					.consumeCategory(consumeCateEntity)
					.memberCard(teenMember.get(teenMemberRandomIdx).getMemberCards().get(0))
					.stores(teenStores.get(teenExcludedRandomIdx))
					.payDate(randomDate())
					.build();
			
			PayHistoryEntity twentyMaleEntity = PayHistoryEntity.builder()
					.payAmount(Long.valueOf(twentyMaleRandomPayAmout))
					.consumeCategory(consumeCateEntity)
					.memberCard(twentyMaleMember.get(twentyMaleRandomIdx).getMemberCards().get(0))
					.stores(twentyMaleStores.get(twentyMaleExcludedRandomIdx))
					.payDate(randomDate())
					.build();
			
			PayHistoryEntity twentyFemaleEntity = PayHistoryEntity.builder()
					.payAmount(Long.valueOf(twentyFemaleRandomPayAmout))
					.consumeCategory(consumeCateEntity)
					.memberCard(twentyFemaleMember.get(twentyFemaleRandomIdx).getMemberCards().get(0))
					.stores(twentyFemaleStores.get(twentyFemaleExcludedRandomIdx))
					.payDate(randomDate())
					.build();
			
			payHistoryRepo.save(teenEntity);
			payHistoryRepo.save(twentyMaleEntity);
			payHistoryRepo.save(twentyFemaleEntity);
			
		}
		
	}
	
	public void memberCardUpdate() {
		List<MemberEntity> findAllMember = memberRepo.findAll();
		List<MemberCardEntity> findAllMemberCard = memberCardRepo.findAll();
		int memberIdx = 0;
		
		for(MemberCardEntity memberCard:findAllMemberCard) {
			memberCard.setMember(findAllMember.get(memberIdx ++));
			memberCardRepo.save(memberCard);
		}
	}
	
	public List<MemberEntity> memberEntitySetCard(List<MemberEntity> memberList) {
		System.out.println("size: " + memberList.size());
		for(MemberEntity member:memberList) {
			member.setMemberCards(memberCardRepo.findByMember(member));
		}
		return memberList;
	}
	
	public Timestamp randomDate() {
		Timestamp startDate = Timestamp.valueOf("2024-09-01 00:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-09-14 23:59:59");
        
        long startMillis = startDate.getTime();
        long endMillis = endDate.getTime();
        
        long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis);
        
        Timestamp randomTimestamp = new Timestamp(randomMillis);
        
       return randomTimestamp;
	}

}
