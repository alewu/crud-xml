package com.ale.crud.common.controller;

import com.ale.crud.common.response.MyResponse;
import com.ale.crud.exception.BusinessException;
import com.ale.crud.exception.BusinessExceptionEnum;
import com.ale.crud.util.common.AliyunOSSClientUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/oss")
public class SysOssController {

    /**
     * @author alewu
     * @date 2017/11/27 17:16
     * @description 上传阿里云
     */
    @PostMapping("/files")
    public MyResponse upload(MultipartFile[] files) throws Exception {
        MyResponse response = new MyResponse();
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                String fileContentType = file.getContentType();
                response = AliyunOSSClientUtil.uploadObject2OSS(file, fileContentType);
            }
            return response;
        }
        return MyResponse.failed();
    }

    /**
     * @author:yf
     * @param:MultipartFile
     * @description:文件上传至阿里云 2017-6-6上午9:18:53
     */
    @PostMapping("/android/files")
    public MyResponse androidUpload(HttpServletRequest request) throws Exception {
        MyResponse response = new MyResponse();
        String fileType = request.getParameter("fileType");
        // 根据文件类型获得文件保存的路径
        String urlType = getUrlType(fileType);
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> mapfile = mRequest.getFileMap();
        if (mapfile.isEmpty()) {
            throw new BusinessException(BusinessExceptionEnum.FileIsEmpty);
        }
        List<String> urls = new LinkedList<>();
        for (Map.Entry<String, MultipartFile> entry : mapfile.entrySet()) {
            MultipartFile mFile = entry.getValue();
            String url = AliyunOSSClientUtil.uploadObjectOSS(mFile, urlType);
            urls.add(url);
            response.put("msg", "文件上传成功");
        }
        response.put("urls", urls);
        return response;
    }


    public String getUrlType(String fileType) throws BusinessException {
        String urlType = "";
        if ("image".equals(fileType)) {
            // 图片
            urlType = "image/";
        } else if ("video".equals(fileType)) {
            // 音频
            urlType = "video/";
        } else if ("apk".equals(fileType)) {
            // android package
            urlType = "apk/";
        } else {
            throw new BusinessException(BusinessExceptionEnum.FileTypeError);
        }
        return urlType;
    }


}
