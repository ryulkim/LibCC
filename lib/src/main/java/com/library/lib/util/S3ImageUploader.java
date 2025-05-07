//package com.library.common.common.util;
//
//import static java.util.UUID.randomUUID;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.CannedAccessControlList;
//import com.amazonaws.services.s3.model.DeleteObjectRequest;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.amazonaws.util.IOUtils;
//import com.library.common.member.exception.S3Exception;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLDecoder;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Objects;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class S3ImageUploader {
//
//    private final AmazonS3 amazonS3;
//    private static final String ARTICLE_IMAGE_PATH = "article/image/";
//
//    @Value("${cloud.aws.s3.bucket-name}")
//    private String bucket;
//
//    public String upload(MultipartFile image) {
//        validateImage(image);
//        validateImageFileExtention(image.getOriginalFilename());
//        try {
//            return uploadImageToS3(image);
//        } catch (IOException e) {
//            throw new S3Exception("S3 업로드 중 오류가 발생했습니다.");
//        }
//    }
//
//    private static void validateImage(MultipartFile image) {
//        if(image.isEmpty() || Objects.isNull(image.getOriginalFilename())) {
//            throw new S3Exception("데이터가 비어있습니다.");
//        }
//    }
//
//    private void validateImageFileExtention(String filename) {
//        int lastDotIndex = filename.lastIndexOf(".");
//        if (lastDotIndex == -1) {
//            throw new S3Exception("파일 확장자가 없습니다.");
//        }
//
//        String extention = filename.substring(lastDotIndex + 1).toLowerCase();
//        List<String> allowedExtentionList = Arrays.asList("jpg", "jpeg", "png", "gif");
//
//        if (!allowedExtentionList.contains(extention)) {
//            throw new S3Exception("허용되지 않는 파일 확장자입니다.");
//        }
//    }
//
//    private String uploadImageToS3(MultipartFile image) throws IOException {
//        String originalFilename = image.getOriginalFilename();
//        String extention = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
//        String s3FileName = ARTICLE_IMAGE_PATH + randomUUID().toString().substring(0, 10) + originalFilename;
//
//        InputStream is = image.getInputStream();
//        byte[] bytes = IOUtils.toByteArray(is);
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType("image/" + extention);
//        metadata.setContentLength(bytes.length);
//
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//
//        try{
//            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, s3FileName, byteArrayInputStream, metadata)
//                    .withCannedAcl(CannedAccessControlList.PublicRead);
//
//            amazonS3.putObject(putObjectRequest);
//        } catch (Exception e){
//            log.error("AWS Error 발생 :" + e.getMessage());
//            throw new S3Exception("S3 업로드 중 오류가 발생했습니다.");
//        } finally {
//            byteArrayInputStream.close();
//            is.close();
//        }
//        return amazonS3.getUrl(bucket, s3FileName).toString();
//    }
//
//    public void deleteImageFromS3(String imageAddress){
//        String key = getKeyFromImageAddress(imageAddress);
//        try {
//            amazonS3.deleteObject(new DeleteObjectRequest(bucket, key));
//        } catch (Exception e){
//            throw new S3Exception("S3 이미지 삭제 중 오류가 발생했습니다.");
//        }
//    }
//
//    private String getKeyFromImageAddress(String imageAddress){
//        try{
//            URL url = new URL(imageAddress);
//            String decodingKey = URLDecoder.decode(url.getPath(), "UTF-8");
//
//            return decodingKey.substring(1);
//        }catch (MalformedURLException | UnsupportedEncodingException e){
//            throw new S3Exception("S3 이미지 주소를 가져오는 중 오류가 발생했습니다.");
//        }
//    }
//}
