package com.icia.project.DTO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {
	private int cnumber;
	private int bnumber;
	private String cwriter;
	private String ccontents;
	private Date cdate;
}
