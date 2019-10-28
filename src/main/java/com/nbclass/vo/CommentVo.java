package com.nbclass.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentVo extends PageVo {
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private String nickname;
	private Integer status;
}

