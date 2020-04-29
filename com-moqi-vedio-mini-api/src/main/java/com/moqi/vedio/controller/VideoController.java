package com.moqi.vedio.controller;

import com.moqi.vedio.service.BgmService;
import com.moqi.vedio.service.VideoService;
import com.moqi.vedio.util.IMoocJSONResult;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@Api(value="视频业务的接口", tags= {"视频业务业务的controller"})
@RequestMapping("/video")
public class VideoController extends BasicController{


	@Autowired
	private BgmService bgmService;

	@Autowired
	private VideoService videoService;
	@ApiOperation(value="上传视频", notes="上传视频的接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name="userId", value="用户id", required=true,
					dataType="String", paramType="form"),
			@ApiImplicitParam(name="bgmId", value="背景音乐id", required=false,
					dataType="String", paramType="form"),
			@ApiImplicitParam(name="videoSeconds", value="背景音乐播放长度", required=true,
					dataType="String", paramType="form"),
			@ApiImplicitParam(name="videoWidth", value="视频宽度", required=true,
					dataType="String", paramType="form"),
			@ApiImplicitParam(name="videoHeight", value="视频高度", required=true,
					dataType="String", paramType="form"),
			@ApiImplicitParam(name="desc", value="视频描述", required=false,
					dataType="String", paramType="form")
	})
	@PostMapping(value="/uploadCover", headers="content-type=multipart/form-data")
	public IMoocJSONResult uploadCover(String userId,
									   String videoId,
									   @ApiParam(value="视频封面", required=true)
											   MultipartFile file) throws Exception {

		if (StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
			return IMoocJSONResult.errorMsg("视频主键id和用户id不能为空...");
		}

		// 文件保存的命名空间
//		String fileSpace = "C:/imooc_videos_dev";
		// 保存到数据库中的相对路径
		String uploadPathDB = "/" + userId + "/video";

		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		// 文件上传的最终保存路径
		String finalCoverPath = "";
		try {
			if (file != null) {

				String fileName = file.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {

					finalCoverPath = FILE_SPACE + uploadPathDB + "/" + fileName;
					// 设置数据库保存的路径
					uploadPathDB += ("/" + fileName);

					File outFile = new File(finalCoverPath);
					if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
						// 创建父文件夹
						outFile.getParentFile().mkdirs();
					}

					fileOutputStream = new FileOutputStream(outFile);
					inputStream = file.getInputStream();
					IOUtils.copy(inputStream, fileOutputStream);
				}

			} else {
				return IMoocJSONResult.errorMsg("上传出错...");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return IMoocJSONResult.errorMsg("上传出错...");
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.flush();
				fileOutputStream.close();
			}
		}

		videoService.updateVideo(videoId, uploadPathDB);

		return IMoocJSONResult.ok();
	}
	
}
