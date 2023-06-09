package com.shinhan.education;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import com.shinhan.education.repository.MemberRepository;
import com.shinhan.education.vo.MemberDTO;
import com.shinhan.education.vo.MemberRole;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class MemberTest {
	@Autowired
	MemberRepository mRepo;
	
	@Test
	void memberInsert() {
		//member table에 10명 입력하기
		IntStream.rangeClosed(1, 10).forEach(i->{
			MemberDTO member = MemberDTO.builder()
					.mid("user" + i)
					.mname("멤버" + i)
					.mpassword("1234")
					.build();
			if(i<=5) {
				member.setMrole(MemberRole.ADMIN);
			} else {
				member.setMrole(MemberRole.USER);
			}
			
			mRepo.save(member);
		});
	}
}
