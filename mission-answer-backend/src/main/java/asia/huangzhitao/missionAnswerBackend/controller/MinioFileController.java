package asia.huangzhitao.missionAnswerBackend.controller;

import asia.huangzhitao.missionAnswerBackend.common.BaseResponse;
import asia.huangzhitao.missionAnswerBackend.common.ErrorCode;
import asia.huangzhitao.missionAnswerBackend.common.ResultUtils;
import asia.huangzhitao.missionAnswerBackend.config.MinioConfig;
import asia.huangzhitao.missionAnswerBackend.exception.BusinessException;
import asia.huangzhitao.missionAnswerBackend.model.dto.file.UploadFileRequest;
import asia.huangzhitao.missionAnswerBackend.model.enums.FileUploadBizEnum;
import asia.huangzhitao.missionAnswerBackend.utils.MinioUtil;
import cn.hutool.core.io.FileUtil;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件接口
 */
@RestController
@RequestMapping("/minio")
@Slf4j
public class MinioFileController {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioConfig minioConfig;

    @Resource
    private MinioUtil minioService;


    /**
     * 校验文件
     *
     * @param multipartFile
     * @param fileUploadBizEnum 业务类型
     */
    private void validFile(MultipartFile multipartFile, FileUploadBizEnum fileUploadBizEnum) {
        // 文件大小
        long fileSize = multipartFile.getSize();
        // 文件后缀
        String fileSuffix = FileUtil.getSuffix(multipartFile.getOriginalFilename());
        final long ONE_M = 1024 * 1024L;
        if (FileUploadBizEnum.USER_AVATAR.equals(fileUploadBizEnum)) {
            if (fileSize > ONE_M) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件大小不能超过 1M");
            }
            if (!Arrays.asList("jpeg", "jpg", "svg", "png", "webp").contains(fileSuffix)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件类型错误");
            }
        }
    }

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public BaseResponse<String> minioUploadFile(@RequestParam("file") MultipartFile file, UploadFileRequest uploadFileRequest) {
        String biz = uploadFileRequest.getBiz();
        FileUploadBizEnum fileUploadBizEnum = FileUploadBizEnum.getEnumByValue(biz);
        validFile(file, fileUploadBizEnum);
        try {
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minioConfig.getMinioBucketName())
                            .object(file.getOriginalFilename())
                            .stream(inputStream, inputStream.available(), -1)
                            .contentType(file.getContentType())
                            .build()
            );
            return ResultUtils.success("File uploaded successfully!");
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "Error uploading file" + e.getMessage());
        }
    }



    //列表
    @GetMapping("/list")
    public BaseResponse<List<String>> list()
    {
        List<String> strings = minioService.listObjects();
        return ResultUtils.success(strings);
    } 

    //删除
    @PutMapping("/delete")
    public BaseResponse<Boolean> delete(@RequestParam String filename)
    {
        minioService.deleteObject(filename);
        return ResultUtils.success(true);
    }

    //上传文件
    @PostMapping("/upload2")
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file)
    {
        try
        {
            // todo 完善文件命名逻辑
            InputStream is = file.getInputStream(); //得到文件流
            String fileName = file.getOriginalFilename(); //文件名
            String newFileName = System.currentTimeMillis() + "." + StringUtils.substringAfterLast(fileName, ".");
            // todo 完善类型校验逻辑
            String contentType = file.getContentType();  //类型
            minioService.uploadObject(is, newFileName, contentType);
            return ResultUtils.success(newFileName);
        } catch (Exception e)
        {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "上传失败");
        }
    }

    //下载minio服务的文件

    /**
     * 下载文件
     * @param filename
     * @param response
     */
    @GetMapping("/download")
    public void download(@RequestParam String filename, HttpServletResponse response) {
        try {
            InputStream fileInputStream = minioService.getObject(filename);
            if (fileInputStream == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件未找到");
            }
            // todo 完善文件命名逻辑
            String newFileName = System.currentTimeMillis() + "." + StringUtils.substringAfterLast(filename, ".");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(newFileName, "UTF-8").replaceAll("\\+", "%20"));
            response.setContentType("application/octet-stream"); // 更改为更通用的MIME类型
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e)
        {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "下载失败");
        }
    }

    //获取minio文件的下载地址
    @GetMapping("/getHttpUrl")
    public BaseResponse<String> getHttpUrl(@RequestParam String filename)
    {
        try
        {
            String url = minioService.getObjectUrl(filename);
            return ResultUtils.success(url);
        } catch (Exception e)
        {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, e.getMessage());
        }
    }

    @GetMapping("/download2")
    public BaseResponse<InputStreamResource> downloadFile(@PathVariable String objectName) {
        try {
            InputStream is = minioClient.getObject(
                    GetObjectArgs.builder()
                    .bucket(minioConfig.getMinioBucketName())
                    .object(objectName)
                    .build()
            );

            // TODO: 完善文件命名逻辑，例如从数据库中获取文件名或根据objectName生成文件名
            String fileName = "downloaded_file"; // 示例文件名

            // 设置HTTP响应头，包含文件名和文件类型（如果知道的话）
            HttpHeaders headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            return ResultUtils.success(new InputStreamResource(is));
        } catch (MinioException | IOException e) {
            // 处理异常，例如返回错误响应
            e.printStackTrace();
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR,"500");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
