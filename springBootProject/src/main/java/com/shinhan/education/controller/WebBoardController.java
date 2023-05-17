package com.shinhan.education.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.querydsl.core.types.Predicate;
import com.shinhan.education.repository.WebBoardRepository;
import com.shinhan.education.repository.WebReplyRepository;
import com.shinhan.education.vo3.PageMarker;
import com.shinhan.education.vo3.PageVO;
import com.shinhan.education.vo3.WebBoard;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/webboard")
public class WebBoardController {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@PostMapping("/register.do")
	public String registerPost(WebBoard board, RedirectAttributes attr) {
		WebBoard newBoard = boardRepo.save(board);
		if(newBoard!=null) {
			attr.addFlashAttribute("msg", "Insert success");
			
		} else {
			attr.addFlashAttribute("msg", "Insert fail");
		}
		return "redirect:list.do";
		
	}
	
	@GetMapping("/register.do")
	public void registerGet() {
		
	}
	
	
	@PostMapping("/delete.do") 
	public String deletePost(Long bno, PageVO pageVO, RedirectAttributes attr) {
		//boardRepo.delete(board);
		boardRepo.deleteById(bno);
		//addFlashAttribute: 새로고침하면 없어짐(1회성)
		//addAttribute: 새로고침해도 유지 
		attr.addFlashAttribute("msg","삭제작업 완료");
		attr.addAttribute("page", pageVO.getPage());
		attr.addAttribute("size", pageVO.getSize());
		attr.addAttribute("keyword", pageVO.getKeyword());
		attr.addAttribute("type", pageVO.getType());
		return "redirect:list.do";
	}
	
	
	@PostMapping("/modify.do") 
	public String updatePost(WebBoard board, PageVO pageVO, RedirectAttributes attr) {
		WebBoard savedBoard = boardRepo.save(board);
		if(savedBoard == null) {
			attr.addFlashAttribute("msg", "Update fail");
		} else {
			attr.addFlashAttribute("msg", "Update success");
		}
		attr.addAttribute("bno", board.getBno());
		attr.addAttribute("page", pageVO.getPage());
		attr.addAttribute("size", pageVO.getSize());
		attr.addAttribute("keyword", pageVO.getKeyword());
		attr.addAttribute("type", pageVO.getType());
		return "redirect:view.do";
	}
	
	
	@GetMapping("/modify.do")
	public void updateOrDelete(Long bno, Model model, PageVO pageVO) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board",board);
			model.addAttribute("pageVO", pageVO);
		});
	}
	
	@GetMapping("/view.do")
	public void selectById(Long bno, Model model, PageVO pageVO) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("board",board);
			model.addAttribute("pageVO", pageVO);
		});
	}
	
	@GetMapping("/list.do")
	public void selectAll(PageVO pageVO, Model model) {
		
		if(pageVO == null) {
			pageVO = new PageVO();
			pageVO.setPage(1);
		}
		
//		
//		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
//		if (flashMap != null) {
//			Object message = flashMap.get("msg");
//			logger.info("입력/삭제/수정에 대한 결과 message=>" + message);
//			model.addAttribute("resultMessage", message);
//		}
		
		Predicate pre = boardRepo.makePredicate(pageVO.getType(), pageVO.getKeyword());
		Pageable paging = pageVO.makePageable(pageVO.getPage(), "bno");
		//Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "bno");
		Page<WebBoard> result = boardRepo.findAll(pre,paging);
		//List<WebBoard> blist = result.getContent();
		
		
//		System.out.println("전체 페이지 수: " + result.getTotalPages());
//		System.out.println("전체 건수: " + result.getTotalElements());
		PageMarker<WebBoard> pageMarker = new PageMarker<>(result, pageVO.getSize());
		
		model.addAttribute("blist", pageMarker);
		//Page<WebBoard> p_result = pageMarker.getResult();
		//System.out.println(p_result.getContent());
	}
	
}
