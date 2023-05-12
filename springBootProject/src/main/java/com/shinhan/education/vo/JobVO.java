package com.shinhan.education.vo;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name = "t_jobs")
@Builder

public class JobVO {
	
	@Id //@generatedValue --> 필요 없음. 디비에 들어가서 시퀀스처럼 증가 되는 것이 아니라 내가 넣어 줘야 하니깐
	private String jobId;
	@Column(unique = true,  nullable = false, length = 35)
	private String jobTitle;
	private int minSalary;
	private int maxSalary;
	@CreationTimestamp
	Timestamp regDate; // 입력시에만 들어감 
	@UpdateTimestamp
	Timestamp updateDate; //입력시 + 수정시 들어감
	
	
}
