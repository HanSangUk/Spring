package com.icia.project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.DTO.CommentDTO;
import com.icia.project.Service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService cs;
	
	
	@RequestMapping(value="/boardcomment")
	public @ResponseBody List<CommentDTO> commentAjax(@ModelAttribute CommentDTO cDTO){
		List<CommentDTO> clist = cs.boardcommentajax(cDTO);
		return clist;
	}

	@RequestMapping(value="/commentdelete")
	public @ResponseBody List<CommentDTO> commentdelete(@RequestParam("cnumber") int cnumber, @RequestParam("bnumber") int bnumber){
		List<CommentDTO> commentlist = cs.commentdelete(cnumber, bnumber);
		return commentlist;
	}
}
