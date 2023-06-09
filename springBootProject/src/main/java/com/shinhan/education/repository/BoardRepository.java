package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shinhan.education.vo.BoardVO;

//interface설계...CRUD작업을 위해 
//구현은 JPA가 한다. 
//기본은 : findAll(), findById(), save()
public interface BoardRepository extends CrudRepository<BoardVO, Long>, QuerydslPredicateExecutor<BoardVO> {

	// 조건조회 추가하기
	
	public List<BoardVO> findByTitle(String title);

	public List<BoardVO> findByContent(String aa);

	public List<BoardVO> findByWriter(String writer);

	public List<BoardVO> findByWriterAndTitle(String writer, String title);

	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title);
	// where Title like ? order by Title desc

	public List<BoardVO> findByTitleContaining(String title);// select * t_boards from where title like '%?%'

	public List<BoardVO> findByTitleStartingWith(String title);// select * t_boards where title like '?%'

	public List<BoardVO> findByTitleEndingWith(String title);// select * t_boards where title like '%?'

	public List<BoardVO> findByTitleNotLike(String title);

	public List<BoardVO> findByTitleContainingAndBoardNoGreaterThanEqual(String title, Long BoardNo);

	// title like '%?%' and BoardNo >= ?
	public List<BoardVO> findByBoardNoBetweenOrderByBoardNoDesc(Long BoardNo1, Long BoardNo2);
	// where BoardNo Between ? and ? order by BoardNo dec

	public List<BoardVO> findByContentIsNull(); // IsNull() 또는 Null
	// where content is null

	public List<BoardVO> findByContentIgnoreCase(String content);
	// where upper(Content) = upper(?)

	// title로 조회, sort desc, paging 1~10
	public List<BoardVO> findByTitleContainingOrderByTitleDesc(String title, Pageable paging);

	public List<BoardVO> findByTitleContaining(String title, Pageable paging);

	public Page<BoardVO> findByboardNoGreaterThan(Long boardNo, Pageable paging);

	// JPQL (JPL Query Language)
	// @Query("select * from t_boards where title like ? order by boardNo desc") --> 이렇게
	// 쓰면 안된다.
	@Query("select b from BoardVO b where b.title like %?1% " + "and b.content like %?2% order by b.boardNo desc")
	public List<BoardVO> findByTitle2(String title, String content); // 이름 규칙이 틀려서 사용할 수 없다.

	@Query("select b from BoardVO b where b.title like %:tt% and b.content like %:cc% order by b.boardNo desc")
	public List<BoardVO> findByTitle3(@Param("tt") String title, @Param("cc") String content); // 이름 규칙이 틀려서 사용할 수 없다.

	// @Query("select * from t_boards where title like ? order by boardNo desc") --> 이렇게
	// 쓰면 안된다.
	@Query("select b from #{#entityName} b where b.title like %?1% " + "and b.content like %?2% order by b.boardNo desc")
	public List<BoardVO> findByTitle4(String title, String content); // 이름 규칙이 틀려서 사용할 수 없다.

	// @Query("select * from t_boards where title like ? order by boardNo desc") --> 이렇게
	// 쓰면 안된다.
	@Query("select b.title, b.content, b.writer from #{#entityName} b where b.title like %?1% "
			+ "and b.content like %?2% order by b.boardNo desc")
	public List<Object[]> findByTitle5(String title, String content); // 이름 규칙이 틀려서 사용할 수 없다.

	// @Query("select * from t_boards where title like ? order by boardNo desc") --> 이렇게
	// 쓰면 안된다.
	@Query(value = "select * from t_boards  where title like '%'||?1||'%' "
			+ "and content like '%'||?2||'%' order by boardNo desc", nativeQuery = true)
	public List<BoardVO> findByTitle6(String title, String content); // 이름 규칙이 틀려서 사용할 수 없다.

}
