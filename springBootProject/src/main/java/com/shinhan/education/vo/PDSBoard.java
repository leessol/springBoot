package com.shinhan.education.vo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString //(exclude = "files2") //toString 에서 걸린다. 
@Entity
@Table(name = "tbl_pdsboard")
@EqualsAndHashCode(of = "pid")
@Builder

public class PDSBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pid;
	private String pname;
	private String pwriter;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) // 같이 바뀜. 
	@JoinColumn(name = "pdsno") // PDSFile칼럼에 생성
	private List<PDSFile> files2; //실제로 컬럼이 생기는게 아님. 여러건을 찾으러 가는 key만 있으면 됨. (pdsno가 PDSfile에 생긴다. )
	//원래 한개가 여러개 쪽으로 들어가야 한다. 근데 이경우, 소스만 보면 여러개가 한개인 쪽으로 들어오는거 같은데 아니다. 반대임. 한개(Board)가 여러개(File)에 들어간것임. 
	//실제로 보면 files 컬럼이 board 테이블에 생성된게 아니다. file테이블에 생성됨. 
	//fetchtype.Eager: 내가(board)가 조회될때 files2로 엮어 있으니깐 자동으로 file테이블도 조회가 됨
	// 근데 fetchtype.lazy하면 나만(board)만 조회가 된다. 
}
