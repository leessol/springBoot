package com.shinhan.education.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shinhan.education.vo.FreeBoard;

public interface FreeBoardRepository 
          extends PagingAndSortingRepository<FreeBoard, Long>{
    //where bno>?
	List<FreeBoard>  findByBnoGreaterThan(Long bno, Pageable paging);
}
