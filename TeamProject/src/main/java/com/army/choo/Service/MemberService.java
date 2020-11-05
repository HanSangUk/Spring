package com.army.choo.Service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.army.choo.DAO.MemberDAO;
import com.army.choo.DTO.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mDAO;
	
	private ModelAndView mav;
	
	@Autowired
	private HttpSession session;
	
	//기업회원가입
	public ModelAndView comMemberInsertForm(MemberDTO mDTO) {
		mav = new ModelAndView();
		int result = mDAO.comMemberInsertForm(mDTO);
		if(result > 0) {
			mav.setViewName("Main");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}
	
	//기업로그인
	public ModelAndView comLoignForm(MemberDTO mDTO) {
		mav = new ModelAndView();
		String loginId = mDAO.comLoginForm(mDTO);
		if(loginId != null) {
			session.setAttribute("loginId", loginId);
			mav.setViewName("Main");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}
	
	//기업회원정보수정 기본정보불러오기(페이지이동)
	public ModelAndView comMemberUpdate(String comnumber) {
		mav = new ModelAndView();
		MemberDTO mDTO = mDAO.comMemberUpdate(comnumber);
		mav.addObject("comupdate", mDTO);
		mav.setViewName("ComMember/ComMemberUpdate");
		
		return mav;
	}
	
	//기업회원정보수정
	public ModelAndView comMemberUpdateForm(MemberDTO mDTO) {
		mav = new ModelAndView();
		int result = mDAO.comMemberUpdateForm(mDTO);
		if(result > 0) {
			mav.setViewName("Main");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}
	
	//회원탈퇴
	public ModelAndView comMemberOutForm(MemberDTO mDTO) {
		mav = new ModelAndView();
		int result = mDAO.comMemberOutForm(mDTO);
		if(result>0) {
			mav.setViewName("Main");
		} else {
			mav.setViewName("Fail");
		}
		return mav;
	}
	
	//마이페이지
	public ModelAndView comMyPage(String comnumber) {
		mav = new ModelAndView();
		MemberDTO mDTO = mDAO.comMyPage(comnumber);
		mav.addObject("commypage", mDTO);
		mav.setViewName("ComMember/MyPage");
		return mav;
	}

}
