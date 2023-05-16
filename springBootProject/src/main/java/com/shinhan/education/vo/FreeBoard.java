package com.shinhan.education.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter 
@ToString  @EqualsAndHashCode(of = {"bno","title"})
@Entity
@Table(name ="tbl_freeboards")
public class FreeBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	@CreationTimestamp
	private Timestamp regDate;
	@UpdateTimestamp
	private Timestamp updateDate;
	
	@JsonIgnore // jackson이 JSON을 만들때, 무시 
	//연관관계설정 : 1:n
	@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
			mappedBy = "board")
	List<FreeBoardReply> replies;
}





