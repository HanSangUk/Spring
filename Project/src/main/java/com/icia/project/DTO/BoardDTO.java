package com.icia.project.DTO;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
	private int bnumber;
	private String bwriter;
	private String btitle;
	private String bcontents;
	private Date bdate;
	private int bhits;
	
	private String bfilename;
	private String bfilenameupdate;
	private MultipartFile bfile;
}
