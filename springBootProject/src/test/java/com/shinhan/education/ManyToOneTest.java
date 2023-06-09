package com.shinhan.education;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.PDSBoardRepository;
import com.shinhan.education.repository.PDSFileRepository;
import com.shinhan.education.vo.PDSBoard;
import com.shinhan.education.vo.PDSFile;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class ManyToOneTest {
	@Autowired
	PDSFileRepository fileRepo;

	@Autowired
	PDSBoardRepository boardRepo;

	// board별 file의 건수 출력
	@Test
	public void getFileCount() {
		// Eager인 경우
		boardRepo.findAll().forEach(board -> {
			System.out.println(board.getPname() + "----" + board.getFiles2().size());
		});

		// Lazy인 경우
		boardRepo.getFilesInfo(227L).forEach(arr -> {
			log.info(Arrays.toString(arr));
		});

		// Lazy인 경우
		boardRepo.getFilesInfo2().forEach(arr -> {
			log.info(Arrays.toString(arr));
		});
	}

	// @Test
	public void test3() {
		boardRepo.getFilesInfo(232L).forEach(arr -> {
			System.out.println(Arrays.toString(arr));
		});

		// fetch =
		System.out.println("========================");
		PDSBoard board = boardRepo.findById(232L).orElse(null);
		if (board != null) {
			System.out.println(board.getPname());
			System.out.println(board.getPwriter());
			System.out.println(board.getFiles2());
		}
	}

	// @Test
	void deleteByBoard() {
		boardRepo.deleteById(227L);
	}

	// @Test
	void deleteByFile() {
		Long[] arr = { 212L, 213L, 214L, 215L };
		Arrays.stream(arr).forEach(bno -> {
			fileRepo.deleteById(bno);
		});
	}

	// @Test
	void selectAllBoard() {
		boardRepo.findAll().forEach(board -> {
			log.info("보드이름: " + board.getPname() + "작성자: " + board.getPwriter() + "첨부file건수: "
					+ board.getFiles2().size() + "건");
		});
	}

	// @Test
	void insertAll() {

//		PDSBoard board = PDSBoard.builder()
//						.pname("SpringBoot수업")
//						.pwriter("은정")
//						.files2(flist)
//						.build();
//		boardRepo.save(board);

		IntStream.range(41, 50).forEach(i -> {

			List<PDSFile> flist = new ArrayList<>();

			IntStream.range(1, 6).forEach(j -> {
				PDSFile f = PDSFile.builder().pdsfilename("shinFile-" + j + ".xls").build();
				flist.add(f);
			});

			PDSBoard board = PDSBoard.builder().pname("Spring 수업-----" + i).pwriter("은빈").files2(flist).build();
			boardRepo.save(board);
		});

	}

}
