package com.shinhan.education;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.WebBoard;
import com.shinhan.education.vo3.WebReply;

@SpringBootTest
public class WebBoardReplyTest {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo;
	
	//board별 댓글 수
	
	//특정 board 댓글 조회 (Reply에서 시작- Reply 입장) --> test4()랑 결과가 같음. 
	@Test //양방향
	void test5() {
		WebBoard board = boardRepo.findById(170L).get();
		List<WebReply> replyList = replyRepo.findByBoard(board);
		replyList.forEach(reply-> {
			System.out.println(reply);
		});
	}
	
	
	//특정 board 댓글 조회 (Board에서 시작- Board 입장)
	//@Test //양방향
	void test4() {
		boardRepo.findById(170L).ifPresent(board->{
			List<WebReply> replyList = board.getReplies();
			for(WebReply reply:replyList) {
				System.out.println(reply);
			}
		});
	}
	
	//모든 board 조회하기 
	//@Test
	void test3() {
		boardRepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	
	
	
	//board에 insert 100건
	//@Test
	void test1() {
		IntStream.rangeClosed(300, 400).forEach(i->{
			WebBoard entity = WebBoard.builder()
					.title("webBoard" + i)
					.writer("user" + (i%10))
					.content("springBoot Project...")
					.build();
			boardRepo.save(entity);
		});
		
	}
	
	//5개의 board에 댓글 10개 넣기
	//@Test
	void test2() {
		Long[] arr = {141L, 145L, 150L, 160L, 170L};
		Arrays.stream(arr).forEach(bno->{
			boardRepo.findById(bno).ifPresent(board->{
				IntStream.rangeClosed(20, 30).forEach(i->{
					WebReply reply = WebReply.builder()
							.replyText("날씨가 더워요..." + i)
							.replyer("댓글작성자" + i)
							.board(board)
							.build();
					replyRepo.save(reply);
				});
			});
		});
	}
}
