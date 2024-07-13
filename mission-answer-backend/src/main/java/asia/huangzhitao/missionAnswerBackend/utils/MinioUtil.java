package asia.huangzhitao.missionAnswerBackend.utils;

import asia.huangzhitao.missionAnswerBackend.config.MinioConfig;
import io.minio.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class MinioUtil {
    @Resource
    MinioConfig minioConfig;
    @Resource
    MinioClient minioClient;

    //获取列表
    public List<String> listObjects() {
        List<String> list=new ArrayList<>();
        try {

            ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder()
                    .bucket(minioConfig.getMinioBucketName())
                    .build();

            Iterable<Result<Item>> results =minioClient.listObjects(listObjectsArgs);
            for (Result<Item> result : results) {
                Item item = result.get();
                log.info(item.lastModified() + ", " + item.size() + ", " + item.objectName());
                list.add(item.objectName());
            }
        }catch (Exception e){
            log.error("错误："+e.getMessage());
        }
        return list;
    }

    //删除
    public void deleteObject(String objectName) {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(minioConfig.getMinioBucketName())
                    .object(objectName)
                    .build();
            minioClient.removeObject(removeObjectArgs);
        }catch (Exception e){
            log.error("错误："+e.getMessage());
        }
    }

    //上传
    public void uploadObject(InputStream is, String fileName, String contentType) {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioConfig.getMinioBucketName())
                    .object(fileName)
                    .contentType(contentType)
                    .stream(is, is.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);
            is.close();
        }catch (Exception e){
            log.error("错误："+e.getMessage());
        }
    }

    //获取minio中地址
    public String getObjectUrl(String objectName){
        try {
            GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(minioConfig.getMinioBucketName())
                    .object(objectName)
                    .expiry(7, TimeUnit.DAYS)
                    .build();
            return minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
        }catch (Exception e){
            e.printStackTrace();
            log.error("错误："+e.getMessage());
        }
        return "";
    }



    //下载minio服务的文件
    public InputStream getObject(String objectName){
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(minioConfig.getMinioBucketName())
                    .object(objectName)
                    .build();
            return minioClient.getObject(getObjectArgs);
        }catch (Exception e){
            log.error("错误："+e.getMessage());
        }
        return null;
    }

}
