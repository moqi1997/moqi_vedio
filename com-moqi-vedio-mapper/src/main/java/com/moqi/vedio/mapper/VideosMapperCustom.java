package com.moqi.vedio.mapper;


import com.moqi.vedio.pojo.Videos;
import com.moqi.vedio.pojo.vo.VideosVO;
import com.moqi.vedio.utils.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public abstract class VideosMapperCustom implements MyMapper<Videos> {
	
	/**
	 * @Description: 条件查询所有视频列表
	 *
	 */
	public abstract List<VideosVO> queryAllVideos(@Param("videoDesc") String videoDesc,
												  @Param("userId") String userId);
	
	/**
	 * @Description: 查询关注的视频
	 */
	public abstract List<VideosVO> queryMyFollowVideos(String userId);
	
	/**
	 * @Description: 查询点赞视频
	 */
	public abstract List<VideosVO> queryMyLikeVideos(@Param("userId") String userId);
	
	/**
	 * @Description: 对视频喜欢的数量进行累加
	 */
	public abstract void addVideoLikeCount(String videoId);
	
	/**
	 * @Description: 对视频喜欢的数量进行累减
	 */
	public abstract void reduceVideoLikeCount(String videoId);
}