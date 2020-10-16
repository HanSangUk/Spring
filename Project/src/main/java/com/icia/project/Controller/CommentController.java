package com.icia.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.Service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService cs;
	
	private ModelAndView mav;
}
