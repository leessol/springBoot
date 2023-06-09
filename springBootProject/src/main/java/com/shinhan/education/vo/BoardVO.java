package com.shinhan.education.vo;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity //JPA가 VO로 인식하고, 관리하는 annotation (즉, vo == entity) 
// @entity라고 쓰면 JPA가 관리하겠다. 
@Table(name = "t_boards") //VO를 보고 테이블로 아예 바로 생성하겠다. --> 그래서 대신 ID가 반드시 필요
// 컬럼이름은 예약어 불가, 카멜 표기법으로 작성된 이름은 DB 컬럼은 언더바로 변경된다. 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long boardNo;
	@NonNull // lombok에 있음 --> 생성 시, 반드시 값이 있어야 한다.  // Java 
	@Column(nullable = false) // DB에 해달 컬럼이 not null이라는 뜻이다, //여기서는 title은 꼭 넣어야 함
	private String title;
	@Column(length = 100)
	private String content;
	private String writer;
	@CreationTimestamp //insert 시 시간이 입력
	private Timestamp reg;
	@UpdateTimestamp //update 시 수정시간 입력
	private Timestamp updateDate;
	
}
