package com.shinhan.education;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.UserCellPhoneVO3Repository;
import com.shinhan.education.repository.UserCellPhoneVORepository;
import com.shinhan.education.repository.UserCellPhoneVORepository2;
import com.shinhan.education.repository.UserVO3Repository;
import com.shinhan.education.repository.UserVORepository;
import com.shinhan.education.vo2.UserCellPhoneVO;
import com.shinhan.education.vo2.UserCellPhoneVO2;
import com.shinhan.education.vo2.UserCellPhoneVO3;
import com.shinhan.education.vo2.UserVO;
import com.shinhan.education.vo2.UserVO2;
import com.shinhan.education.vo2.UserVO3;

@SpringBootTest
public class OneToOneTest {

	@Autowired
	UserVORepository uRepo;
	
	@Autowired
	UserCellPhoneVORepository pRepo;
	
	@Autowired
	UserCellPhoneVORepository2 uRepo2;
	
	@Autowired
	UserVO3Repository uRepo3;
	
	
	@Autowired
	UserCellPhoneVO3Repository phone3Repo;
	
	@Test
	void test5() {
		phone3Repo.findById("zz").ifPresent(p->{
			System.out.println(p.getPhoneNumber());
			System.out.println(p.getUser2().getUsername());
		});
	}
	
	//@Test
	void test4() {
		UserVO3 user = UserVO3.builder()
				.userid("jj4")
				.username("Oh4!!")
				.build();
		
		UserCellPhoneVO3 phone = UserCellPhoneVO3.builder()
				.phoneNumber("010-4777-3333")
				.model("겔럭스22")
				.user2(user)
				.build();
 
		uRepo3.save(user);
	}
	
	//@Test
	void test3() {
		UserCellPhoneVO3 phone = UserCellPhoneVO3.builder()
				.phoneNumber("010-7777-3333")
				.model("겔럭스21")
				.build();
		
		UserVO3 user = UserVO3.builder()
				.userid("jj2")
				.username("Oh!!")
				.phone(phone)
				.build();
		phone.setUser2(user);
		uRepo3.save(user);
	}
	
	
	
	//@Test
	void test2() {
		UserVO2 user = UserVO2.builder()
				.userid("good")
				.username("홍대")
				.build();
		UserCellPhoneVO2 phone = UserCellPhoneVO2.builder()
				.phoneNumber("010-7894-5632")
				.model("아이폰10")
				.user(user)
				.build();
		uRepo2.save(phone);
	}
	
	//@Test
	void test1() {
		UserCellPhoneVO phone = UserCellPhoneVO.builder()
				.phoneNumber("010-7894-5632")
				.model("아이폰10")
				.build();
		UserCellPhoneVO savedPhone = pRepo.save(phone);
		
		UserVO user = UserVO.builder()
				.userid("good")
				.username("홍대")
				.phone(savedPhone)
				.build();
		uRepo.save(user);
	}
}






