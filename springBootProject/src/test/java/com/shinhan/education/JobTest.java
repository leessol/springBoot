package com.shinhan.education;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.JobsRepository;
import com.shinhan.education.vo.JobVO;

@SpringBootTest
public class JobTest {

	@Autowired
	JobsRepository repo;
	
	//@Test
	public void test6() {
		repo.deleteAll();
	}
	
	
	//@Test
	public void test5() {
		//CRUD....delete
		repo.findById("job20").ifPresent(job->{
			System.out.println(job + "존재한다.");
			repo.delete(job);
		});
	}
	
	
	//@Test
	public void test4() {
		//CRUD....Update
		repo.findById("job20").ifPresent(job->{
			job.setJobTitle("마켓팅-수정");
			job.setMaxSalary(10000);
			job.setMinSalary(9000);
			repo.save(job);
		});
	}
	
	
	//@Test
	@Order(2)
	public void test3() {
		//CRUD....Read
		
		Optional<JobVO> jobOptional = repo.findById("job2");
		if(jobOptional.isPresent()) {
			JobVO job = jobOptional.get();
			System.out.println(job);
		}else {
			System.out.println("존재하는 직책이 없다");
		}
		JobVO job = repo.findById("job2").orElse(null);
		if(job==null) {
			System.out.println("존재하는 직책이 없다");
		}else {
			System.out.println(job);
		}
		
		
		
	}
	
	
	//@Test
	//@Order(1)
	public void test2() {
		//CRUD....Read
		Iterable<JobVO> datas =  repo.findAll();
		List<JobVO> joblist = (List<JobVO>)datas;
		if(joblist.size()==0) {
			System.out.println("조회데이터없음");
		}else {
			for(JobVO job:joblist) {
				System.out.println(job);
			}
		}
	}
	
	
	//@Test
	public void test1() {
		//CRUD....Create
		String[] arr = {"마켓팅","SI개발자","SM개발자","메니져","AA",
				    "ㅠㅠ","CC","DD","EE","FF"
					};
		IntStream.range(0, arr.length).forEach(i->{
			JobVO job = JobVO.builder()
					.jobId("job" + (i))
					.jobTitle(arr[i]) 
					.minSalary(1000)
					.maxSalary(5000)
					.build();
			repo.save(job);
		});
	}
}










