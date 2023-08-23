package com.yedam.app.board.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.board.service.BoardService;
import com.yedam.app.board.service.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	// GET  -  조회, 빈 페이지를 요청
	
	// POST(Request Body 요구) -  등록, 수정, 삭제
	
	
	// 전체조회 
	@GetMapping("boardList")
	public String boardList(Model model) {
		model.addAttribute("boardList", boardService.getBoardList());
		
		return "board/list";
	}
	
	
	// 단건조회 
	@GetMapping("boardInfo")
	public String boardInfo(Model model, BoardVO boardVO) {
		BoardVO findBoard = boardService.getBoard(boardVO);
		model.addAttribute("boardInfo", findBoard);
		return "board/info";
	}
	
	
	// 등록 : 폼
	@GetMapping("boardInsert")
	public String boardInsert() {
		// 등록 시 사용될 Primary Key를 Model에 담아 반환.
		return "board/form";
	}
	
	// 등록 : 처리
	@PostMapping("boardInsert")
	public String boardInsertProcess(BoardVO boardVO) {
		int result = boardService.insertBoard(boardVO);
		return "redirect:boardInfo?bno="+result;
	}
	
	// 수정 : 폼
	@GetMapping("boardUpdate")
	public String boardUpdate(BoardVO boardVO, Model model) {
		BoardVO findBoard = boardService.getBoard(boardVO);
		model.addAttribute("boardInfo", findBoard);
		return "board/form";
	}
	
	// 수정 : 처리
	@PostMapping("boardUpdate")
	public String boardUpdateProcess(BoardVO boardVO, RedirectAttributes rtt) {
		Map<String, Object> result = boardService.updateBoard(boardVO);
		
		rtt.addAttribute("bno", result.get("Target Bno"));			// redirect 시 컨트롤러에 전달
		rtt.addFlashAttribute("msg", result);	// 최종 페이지에 전달
		return "redirect:boardInfo";
	}
	
	
	// 삭제 : 처리
	@PostMapping("deleteBoardList")
	@ResponseBody
	public Map<String, Object> deleteBoardList(@RequestBody List<Integer> bnoList) {
		return boardService.deleteBoard(bnoList);
	}
	
	
}
