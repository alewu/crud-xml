package com.ale.util.common;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ObjectMetadata;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author alewu
 * @date 2017/11/27 21:16
 * @description 阿里云OSS存储对象 使用java-sdk上传图片
 */
public class AliyunOSSClientUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AliyunOSSClientUtil.class);

    private static OSSClient ossClient;
    // 存储空间
    private static String bucketName = ConfigUtil.getParameter("bucketName");
    //Endpoint（访问域名）
    private static String endpoint = ConfigUtil.getParameter("endpoint");
    //AccessKey（访问密钥）
    private static String accessKeyId = ConfigUtil.getParameter("accessKeyId");
    private static String accessKeySecret = ConfigUtil.getParameter("accessKeySecret");

    static {
        // 配置OSSClient（可选）
        // ClientConfiguration conf = new ClientConfiguration();
        ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public static void main(String[] args) {
        // 上传字符串
        String content = "Hello OSS";
        // key是文件名
        ossClient.putObject(bucketName, "file", new ByteArrayInputStream(content.getBytes()));
    }

    /**
     * 上传多个文件
     * @param files 上传的文件
     * @return 文件地址
     */
    public static List<String> upload(MultipartFile[] files) {
        List<String> urls = new LinkedList<>();
        for (MultipartFile file : files) {
            String url = uploadFileToOSS(file);
            urls.add(url);
        }
        ossClient.shutdown();
        return urls;
    }

    /**
     * 上传单个文件
     * @param file 上传的文件
     * @return 文件地址
     */
    public static String uploadFileToOSS(MultipartFile file) {
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        // 自定义文件名
        fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" +
                System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
        // 根据文件类型获得文件保存的路径（bucket下文件路径）
        String key = contentType + "/" + fileName;
        try (InputStream inputStream = file.getInputStream()) {
            int contentLength = inputStream.available();
            ObjectMetadata metadata = getObjectMetadata(fileName, contentLength);
            ossClient.putObject(bucketName, key, inputStream, metadata);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return getImgURL(key);
    }


    /**
     * 设置对象/文件元信息
     *
     * @param fileName      文件名
     * @param contentLength 文件长度
     * @return 对象/文件元信息
     */
    public static ObjectMetadata getObjectMetadata(String fileName, int contentLength) {
        // 创建上传Object的Metadata
        ObjectMetadata metadata = new ObjectMetadata();
        // 指定该Object被下载时的网页的缓存行为
        metadata.setCacheControl("no-cache");
        // 指定该Object下设置Header
        metadata.setHeader("Pragma", "no-cache");
        // 指定该Object被下载时的内容编码格式
        metadata.setContentEncoding("utf-8");
        // 必须设置ContentLength， 否则会报 411 missContentLength
        metadata.setContentLength(contentLength);
        // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
        // 如果没有扩展名则填默认值application/octet-stream
        metadata.setContentType(getContentType(fileName));
        // 指定该Object被下载时的名称（指示MIME用户代理如何显示附加的文件，打开或下载，及文件名称）
        //  content-disposition = "Content-Disposition" ":"disposition-type *( ";" disposition-parm )
        //  An example is Content-Disposition: attachment; filename="fname.ext"
        metadata.setContentDisposition("attachment;filename=" + fileName);
        return metadata;
    }


    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     *
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        if (".bmp".equals(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equals(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equals(fileExtension) || ".jpg".equals(fileExtension)
                || ".png".equals(fileExtension)) {
            return "image/jpeg";
        }
        if (".html".equals(fileExtension)) {
            return "text/html";
        }
        if (".txt".equals(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equals(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equals(fileExtension)
                || "pptx".equals(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equals(fileExtension)
                || "docx".equals(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equals(fileExtension)) {
            return "text/xml";
        }
        // 默认返回类型
        return "application/octet-stream";
    }


    /**
     * 生成预签名URL
     * 如果你想把自己的资源发放给第三方用户访问，但是又不想开放Bucket的读权限，可以通过生成预签名URL的形式提供给用户一个临时的访问URL。
     * 在生成URL时，你可以指定URL过期的时间，从而限制用户长时间访问。
     * 生成的URL默认以GET方式访问
     *
     * @param key 文件名
     * @return 图片地址
     */
    public static String getImgURL(String key) {
        Date expiresAt = new DateTime().plusYears(10).toDate();
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiresAt);
        String urlString = url.toString();
        return urlString.substring(0, urlString.lastIndexOf("?"));
    }

    /**
     * 创建存储空间
     *
     * @param bucketName 存储空间名
     * @return
     */
    public static boolean createBucket(String bucketName) {
        Bucket bucket = ossClient.createBucket(bucketName);
        return bucketName.equals(bucket.getName());
    }

}
