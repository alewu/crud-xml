package com.ale.crud.util.common;

import com.ale.crud.common.response.MyResponse;
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

    /**
     * 上传图片至OSS
     *
     * @param file   上传文
     * @param folder 模拟文件夹名 如"qj_nanjing/"
     * @return String 返回的唯一MD5数字签名
     */
    public static MyResponse uploadObject2OSS(MultipartFile file, String folder) {
        MyResponse response = new MyResponse();
        try (InputStream inputStream = file.getInputStream()) {
            // 文件名
            String fileName = file.getOriginalFilename();
            if (fileName.endsWith(".apk")) {
                fileName = "yzcx.apk";
            }
            fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" +
                    System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."), fileName.length());
            // 文件大小
            Long fileSize = file.getSize();
            // 创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            // 上传的文件的长度
            metadata.setContentLength(inputStream.available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MIME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            ossClient.putObject(bucketName, folder + fileName, inputStream, metadata);
            // 解析结果
            Date expiration = new DateTime().plusYears(10).toDate();
            URL url = ossClient.generatePresignedUrl(bucketName, folder + fileName, expiration);
            String urlString = url.toString();
            String subUrl = urlString.substring(0, urlString.lastIndexOf("?"));
            response.put("file", subUrl);
            response.put("fileSize", Tools.getPrintSize(fileSize));
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        } finally {
            ossClient.shutdown();
        }
        return null;
    }


    /**
     * 上传图片至OSS
     * @param file 上传文件
     * @param folder 模拟文件夹名 eg. "parent_dir/"
     * @return 图片地址
     */
    public static String uploadObjectOSS(MultipartFile file, String folder) {
        try (InputStream inputStream = file.getInputStream()) {
            String fileName = file.getOriginalFilename();
            ObjectMetadata metadata = getObjectMetadata(fileName);
            // 上传的文件的长度
            metadata.setContentLength(inputStream.available());
            // 上传文件 (上传文件流的形式)
            ossClient.putObject(bucketName, folder + fileName, inputStream, metadata);
            // 解析结果,得到图片地址
            Date expiresAt = new DateTime().plusYears(10).toDate();
            return getImgURL(folder + fileName, expiresAt);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        } finally {
            ossClient.shutdown();
        }
        return null;
    }

    /**
     * 设置对象/文件元信息
     * @param fileName 文件名
     * @return
     */
    public static ObjectMetadata getObjectMetadata(String fileName) {
        // 创建上传Object的Metadata
        ObjectMetadata metadata = new ObjectMetadata();
        // 指定该Object被下载时的网页的缓存行为
        metadata.setCacheControl("no-cache");
        // 指定该Object下设置Header
        metadata.setHeader("Pragma", "no-cache");
        // 指定该Object被下载时的内容编码格式
        metadata.setContentEncoding("utf-8");
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
     * 根据key获取oss服务器上的图片地址
     * @param key 文件名
     * @return 图片地址
     */
    public static String getImgURL(String key, Date expiresAt) {
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiresAt);
        String urlString = url.toString();
        String subUrl = urlString.substring(0, urlString.lastIndexOf("?"));
        ossClient.shutdown();
        return subUrl;
    }

    public static void main(String[] args) {
        // 上传字符串
        String content = "Hello OSS";
        // key是文件名
        ossClient.putObject(bucketName, "file", new ByteArrayInputStream(content.getBytes()));
    }

    /**
     * 创建存储空间
     * @param ossClient  阿里云OSS客户端对象
     * @param bucketName 存储空间名
     * @return
     */
    public static boolean createBucket(OSSClient ossClient, String bucketName) {
        Bucket bucket = ossClient.createBucket(bucketName);
        return bucketName.equals(bucket.getName());
    }


}
