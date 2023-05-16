package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.MultiKeyARepository;
import com.shinhan.education.repository.MultiKeyBRepository;
import com.shinhan.education.vo2.MultiKeyAUsing;
import com.shinhan.education.vo2.MultiKeyB;
import com.shinhan.education.vo2.MultiKeyBDTO;

@SpringBootTest   
public class MultiKeyTest {

	@Autowired
	MultiKeyARepository aRepo;
	
	@Autowired
	MultiKeyBRepository bRepo;
	
	@Test
	void test2() {
		MultiKeyB id = MultiKeyB.builder()
				.id1(10)
				.id2(21)
				.build();
		MultiKeyBDTO b = MultiKeyBDTO.builder()
				.id(id)
				.userName("zzilre2")
				.address("함평2")
				.build();
		bRepo.save(b);
	}
	
	//@Test
	void test1() {
		MultiKeyAUsing a = new MultiKeyAUsing();
		a.setId1(1);
		a.setId2(2);
		a.setUserName("jin3");
		a.setAddress("pusan3");
		aRepo.save(a);
	}
}
