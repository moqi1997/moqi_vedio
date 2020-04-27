package com.moqi.vedio.mapper;


import com.moqi.vedio.pojo.SearchRecords;
import com.moqi.vedio.utils.MyMapper;

import java.util.List;

public interface SearchRecordsMapper extends MyMapper<SearchRecords> {
	
	public List<String> getHotwords();
}