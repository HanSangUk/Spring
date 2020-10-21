package com.icia.project.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icia.project.DAO.CommentDAO;
import com.icia.project.DTO.CommentDTO;

@Service
public class CommentService {
	
	@Autowired
	private CommentDAO cDAO;

	public List<CommentDTO> boardcommentajax(CommentDTO cDTO) {
		int writerResult = cDAO.commentinsert(cDTO);
		List<CommentDTO> clist = new ArrayList<CommentDTO>();
		if(writerResult > 0) {
			clist = cDAO.commentlist(cDTO.getBnumber());
		} else {
			clist = null;
		}
		return clist;
	}

	public List<CommentDTO> commentdelete(int cnumber, int bnumber) {
		int result = cDAO.commentdelete(cnumber);
		List<CommentDTO> commentlist = new ArrayList<CommentDTO>();
		if(result > 0) {
			commentlist = cDAO.commentlist(bnumber);
		} else {
			commentlist = null;
		}
		return commentlist;
	}

}
