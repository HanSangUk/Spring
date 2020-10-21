package com.icia.project.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonNode;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.project.DAO.BoardDAO;
import com.icia.project.DAO.MemberDAO;
import com.icia.project.DTO.BoardDTO;
import com.icia.project.DTO.MemberDTO;
import com.icia.project.DTO.PageDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mDAO;
	
	@Autowired
	private BoardDAO bDAO;
	
	@Autowired
	private HttpSession session;
	
	private ModelAndView mav;
	
	private static final int PAGE_LIMIT = 3;   // 한페이지에 보이는 글 갯수
	private static final int BLOCK_LIMIT = 5;   // 목록에 보이는 수

	
	
	public String idoverlap(String mid) {
		String idresult = mDAO.idoverlap(mid);
		String result = null;
		if(idresult == null) {
			result = "OK";
		} else {
			result = "NO";
		}
		return result;
	}

	public ModelAndView memberjoinform(MemberDTO mDTO) throws IllegalStateException, IOException {
		mav = new ModelAndView();
		MultipartFile mfile = mDTO.getMfile();
		String mfilename = mfile.getOriginalFilename();
		
		String savePath = "C:\\Users\\1\\git\\Spring\\Project\\src\\main\\webapp\\resources\\uploadFile\\"+mfilename;
		if(!mfile.isEmpty()) {
			mfile.transferTo(new File(savePath));
		}
		mDTO.setMfilename(mfilename);
		int result = mDAO.memberjoinform(mDTO);
		if(result > 0) {
			mav.setViewName("home");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}

	public ModelAndView loginform(MemberDTO memberlogin) {
		mav = new ModelAndView();
		String loginId = mDAO.loginform(memberlogin);
		if(loginId != null) {
			session.setAttribute("loginId", loginId);
			mav.setViewName("Main");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}
	//회원목록페이징
	public ModelAndView memberlist(int page) {
		mav = new ModelAndView();
		int mlistCount = mDAO.memberCount();
		int startRow = (page-1)*PAGE_LIMIT+1;
		int endRow = page*PAGE_LIMIT;

		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		List<MemberDTO> memberlist = mDAO.memberlist(paging);
		int maxPage = (int)(Math.ceil((double)mlistCount/PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page/BLOCK_LIMIT))) -1) * BLOCK_LIMIT +1;
		                     // Math.ceil = 그냥 반올림
		int endPage = startPage + BLOCK_LIMIT - 1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		
		mav.addObject("paging", paging);
		mav.addObject("memberlist", memberlist);
		mav.setViewName("memberv/MemberList");
		return mav;
	}

	public ModelAndView memberview(String mid, int page) {
		mav = new ModelAndView();
		MemberDTO mDTO = mDAO.memberview(mid);
		mav.addObject("page", page);
		mav.addObject("mDTO", mDTO);
		mav.setViewName("memberv/MemberView");
		return mav;
	}

	public ModelAndView naverLogin(String profile) throws ParseException {
		mav = new ModelAndView();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(profile);
		JSONObject naverUser = (JSONObject)obj;
		JSONObject userInfo = (JSONObject)naverUser.get("response");
		String naverId = (String)userInfo.get("id");
		String loginId = mDAO.naverLogin(naverId);
		session.setAttribute("loginId", loginId);
		mav.setViewName("Main");
		return mav;
	}

	public ModelAndView kakaoLogin(JsonNode profile) {
		mav = new ModelAndView();
		String kakaoId = profile.get("id").asText();
		String loginId = mDAO.kakaoLogin(kakaoId);
		
		session.setAttribute("loginId", loginId);
		mav.setViewName("Main");
		
		return mav;
	}
	//회원수정페이지 이동
	public ModelAndView memberupdate(String mid) {
		mav = new ModelAndView();
		MemberDTO mDTO = mDAO.memberview(mid);
		
		mav.addObject("memberupdate", mDTO);
		mav.setViewName("memberv/MemberUpdate");
		return mav;
	}
	//회원정보 수정
	public ModelAndView memberupdateform(MemberDTO update) {
		mav = new ModelAndView();
		int result = mDAO.memberupdateform(update);
		if(result>0) {
			mav.setViewName("Main");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}

	public ModelAndView memberdelete(String mid, int page) {
		mav = new ModelAndView();
		int result = mDAO.memberdelete(mid);
		if(result >0) {
			mav.setViewName("redirect:/memberlistpaging?page="+page);
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}

	public ModelAndView mypage(String bwriter, int page) {
		mav = new ModelAndView();
		int boardlistcount = bDAO.mypagecount(bwriter);
		int startRow = (page-1)*PAGE_LIMIT+1;
		int endRow = page*PAGE_LIMIT;
		
		PageDTO paging = new PageDTO();
		paging.setStartRow(startRow);
		paging.setEndRow(endRow);
		paging.setSearchtype("searchwriter");
		paging.setKeyword(bwriter);
		List<BoardDTO> boardlist = bDAO.searchlist(paging);
		int maxPage = (int)(Math.ceil((double)boardlistcount/PAGE_LIMIT));
		int startPage = (((int)(Math.ceil((double)page/BLOCK_LIMIT))) -1) * BLOCK_LIMIT +1;
		                     // Math.ceil = 그냥 반올림
		int endPage = startPage + BLOCK_LIMIT - 1;
		if(endPage>maxPage) {
			endPage = maxPage;
		}
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		mav.addObject("paging", paging);
		mav.addObject("myboard", boardlist);
		mav.setViewName("memberv/MyPage");
		return mav;
	}

}
