package com.ale.common.controller;

import com.ale.common.response.MyResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/oss")
public class SysOssController {

    /**
     * @author alewu
     * @date 2017/11/27 17:16
     * @description 上传阿里云
     */
    @PostMapping("/files")
    public MyResponse upload(@RequestParam("files") MultipartFile[] files) {
        if (files != null && files.length > 0) {
            List<String> fileURLs = AliyunOSSClientUtil.upload(files);
            return MyResponse.ok().put("fileURLs", fileURLs);
        }
        return MyResponse.failed();
    }


}