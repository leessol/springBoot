package com.shinhan.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@ComponentScan(basePackages = {"com.shinhan"}) //Controller 스캔 (원래는 model 부분도 다 스캔해야하는데 안됨 그래서 enable함)
//@EntityScan("com.shinhan") //VO 스캔
//@EnableJpaRepositories("com.shinhan") //model 스캔
// --> project 생성 시 패키지의 하위에 있는 패키지는 자동 스캔된다. 
@SpringBootApplication
public class SpringBootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}

}
