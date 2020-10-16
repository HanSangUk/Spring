package com.icia.project.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.DTO.BoardDTO;
import com.icia.project.Service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bs;

	private ModelAndView mav;
	
	@RequestMapping(value="/")
	public String home() {
		return "home";
	}
	
	@RequestMapping(value="/main")
	public String Main() {
		return "Main";
	}
	
	@RequestMapping(value="/boardwrite")
	public String BoardWrite() {
		return "boardv/BoardWrite";
	}
	
	@RequestMapping(value="/boardinsert")
	public ModelAndView Boardinsert(@ModelAttribute BoardDTO bDTO) throws IllegalStateException, IOException {
		mav = bs.boardinsert(bDTO);
		return mav;
	}
	
	@RequestMapping(value="/boardlist")
	public ModelAndView BoardList(@RequestParam(value="page", required=false, defaultValue="1")int page) {
		mav = bs.boardlist(page);
		return mav;
	}
	
	@RequestMapping(value="/boardview")
	public ModelAndView BoardView(@RequestParam("bnumber") int bnumber, @RequestParam("page") int page) {
		mav = bs.boardview(bnumber, page);
		return mav;
	}
	
	@RequestMapping(value="/boardupdate")
	public ModelAndView BoardUpdate(@RequestParam("bnumber") int bnumber, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		mav = bs.boardupdate(bnumber, page);
		return mav;
	}
	
	@RequestMapping(value="/boardupdateform")
	public ModelAndView BoardUpdateForm(@ModelAttribute BoardDTO bDTO, @RequestParam("page") int page,
			@RequestParam("bnumber") int bnumber) {
		mav = bs.boardupdateform(bDTO, page, bnumber);
		return mav;
	}
	
	@RequestMapping(value="/boarddelete")
	public ModelAndView BoardDelete(@RequestParam("bnumber") int bnumber, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		mav = bs.boarddelete(bnumber, page);
		return mav;
	}
	
	@RequestMapping(value="/boardsearch")
	public ModelAndView BoardSearch(@RequestParam("searchtype") String searchtype,
			@RequestParam("keyword") String keyword, @RequestParam(value="page", required=false, defaultValue="1")int page) {
		mav = bs.boardsearch(searchtype, keyword, page);
		return mav;
	}
	
	
	
	
	
}
