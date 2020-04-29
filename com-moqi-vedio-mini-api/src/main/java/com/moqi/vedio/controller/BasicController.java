package com.moqi.vedio.controller;

import com.moqi.vedio.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author moqi
 * @date 2020/4/17 16:49
 */
@RestController
public class BasicController {

    @Autowired
    public RedisOperator redis;

    public static final String USER_REDIS_SESSION = "user-redis-session";

    // 文件保存的命名空间
    public static final String FILE_SPACE = "D:/moqi_vedio";

    // ffmpeg所在目录
  //  public static final String FFMPEG_EXE = "C:\\ffmpeg\\bin\\ffmpeg.exe";
    // 每页分页的记录数
    public static final Integer PAGE_SIZE = 5;

}
