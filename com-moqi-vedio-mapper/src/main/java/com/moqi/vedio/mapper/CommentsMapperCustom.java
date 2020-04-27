package com.moqi.vedio.mapper;


import com.moqi.vedio.pojo.Comments;
import com.moqi.vedio.pojo.vo.CommentsVO;
import com.moqi.vedio.utils.MyMapper;

import java.util.List;

public interface CommentsMapperCustom extends MyMapper<Comments> {
	
	public List<CommentsVO> queryComments(String videoId);
}