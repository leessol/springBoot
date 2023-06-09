package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.MemberRepository;
import com.shinhan.education.repository.ProfileRepository;
import com.shinhan.education.vo.MemberDTO;
import com.shinhan.education.vo.MemberRole;
import com.shinhan.education.vo.ProfileDTO;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class MemberProfileTest {
	@Autowired
	MemberRepository mRepo;
	@Autowired
	ProfileRepository pRepo;

	// 멤버의 profile 갯수를 얻기
	@Test
	public void addMember() {
		IntStream.range(1,4).forEach(i->{
			MemberDTO member = MemberDTO.builder()
					.mid("manager-" + i)
					.mname("매니저" + i)
					.mpassword("1234")
					.mrole(MemberRole.MANAGER).build();
			 
			mRepo.save(member);
		});
	}	

	// 멤버의 profile 갯수를 얻기
	//@Test
	public void getProfileCount() {
		List<Object[]> result = pRepo.getMemberWithProfileCount("user");
		result.forEach(arr -> log.info(Arrays.toString(arr)));
	}

	// 해당 profile의 member 정보 알아내기
	// @Test
	void getMemberByProfile() {
		Long pno = 206L;
		pRepo.findById(pno).ifPresent(p -> {
			MemberDTO member = p.getMember();
			log.info(p.isCurrentYn() + " : " + member.getMname() + " ---- " + member.getMrole());
		});
	}

	// 특정 멤버의 profile 조회하기
	// @Test
	void selectById() {
		MemberDTO member = mRepo.findById("user1").orElse(null);
		pRepo.findByMember(member).forEach(p -> log.info(p.toString()));
	}

	// @Test
	void profileInsertTest() {
//		MemberDTO member = mRepo.findById("user1").orElse(null);
//		if(member != null) {
//			log.info(member.toString());
//			IntStream.range(1,6).forEach(i->{
//				ProfileDTO profile = ProfileDTO.builder()
//						.fname("face-" + i + ".jpg")
//						.currentYn(i==5?true:false)
//						.member(member)
//						.build();
//				pRepo.save(profile);
//			});
//			pRepo.findAll().forEach(profile->log.info(profile.toString()));
//			
//		}

		MemberDTO member2 = mRepo.findById("user2").orElse(null);
		if (member2 != null) {
			log.info(member2.toString());
			IntStream.range(1, 6).forEach(i -> {
				ProfileDTO profile = ProfileDTO.builder().fname("face-" + i + ".jpg").currentYn(i == 5 ? true : false)
						.member(member2).build();
				pRepo.save(profile);
			});
			pRepo.findAll().forEach(profile -> log.info(profile.toString()));

		}

	}

}
