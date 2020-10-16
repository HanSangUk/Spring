package com.icia.project.DTO;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
	private String mid;
	private String mpassword;
	private String mname;
	private Date mbirth;
	private String memail;
	private String maddress1;
	private String maddress2;
	private String maddress3;
	private String maddress4;
	private String mphone;
	private String mfilename;
	private MultipartFile mfile;
	private String kakaoId;
	private String naverId;
}
