package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.shinhan.education.vo.PDSBoard;

public interface PDSBoardRepository extends CrudRepository<PDSBoard, Long> {
	//fetch = FetchType.Lazy인 경우, JPQL를 이용할 수 있다. 
	@Query("select b.pname, b.pwriter, f.pdsfilename "
			+ " from PDSBoard b left outer join b.files2 f "
			+ " where b.pid = ?1 order by b.pid ")
	public List<Object[]> getFilesInfo(long pid);
			
	//fetch가 lazy로 되어있는데 join한 내용을 가져오고 싶을때 이렇게 한다. 
	//fetch = FetchType.Lazy인 경우, nativeQuery를 사용한다. 
	@Query(value="select board.pname, Count(*) "
			+ "from tbl_pdsboard board left outer join tbl_pdsfiles file2 "
			+ "on(board.pid=file2.pdsno "
			+ "group by board.pname", nativeQuery = true)
	public List<Object[]> getFilesInfo2();

}
