package com.moqi.vedio.service;

import com.moqi.vedio.pojo.Videos;

public interface VideoService {
	
	/**
	 * @Description: 保存视频
	 */
	public String saveVideo(Videos video);
	
	/**
	 * @Description: 修改视频的封面
	 */
	public void updateVideo(String videoId, String coverPath);
	

}


