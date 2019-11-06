package com.ec.sticket.util;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.ec.sticket.config.AWSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class AWSS3Util {
    private AmazonS3 s3Client;

    public AWSS3Util() {
        this.s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(AWSConfig.getAWSCredentials()))
                .withRegion(Regions.AP_NORTHEAST_2)
                .build();
    }


    public void uploadMultipartFile(String bucketName, String key, MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType("image/png");
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            s3Client.putObject(putObjectRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadAsset(MultipartFile multipartFile, int assetId) {
        uploadMultipartFile(AWSConfig.BUCKET_NAME,
                String.format("%s/asset_%d.png", AWSConfig.FOLDER_NAME_ASSETS, assetId), multipartFile);
    }

    public String getURL(String key) {
        return s3Client.getUrl(AWSConfig.BUCKET_NAME, key).toString();
    }

//    public String getKey(int id, String name) {
//        return String.format("%s/%d_%s.png", AWSConfig.FOLDER_NAME, id, name);
//    }

    private ObjectMetadata getMetadata(String dataURI) throws IOException {
        return getMetadata(ImageUtil.dataUriToInputStream(dataURI));
    }

    private ObjectMetadata getMetadata(InputStream inputStream) throws IOException {
        return getMetadata(IOUtils.toByteArray(inputStream));
    }

    private ObjectMetadata getMetadata(byte[] content) {
        return getMetadata(content.length);
    }

    private ObjectMetadata getMetadata(int contentLength) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        metadata.setContentLength(contentLength);
        return metadata;
    }

    public static String getAssetImgUrl(int assetId) {
        return String.format("%s/asset_%d.png", AWSConfig.ASSETS_URL, assetId);
    }
}
