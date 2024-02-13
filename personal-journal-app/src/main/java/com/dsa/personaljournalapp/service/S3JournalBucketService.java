package com.dsa.personaljournalapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.*;

@Service
public class S3JournalBucketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(S3JournalBucketService.class);

    private static final String bucketName = "dsa-journal-bucket";

    public ListObjectsV2Response listFilesInS3Bucket(S3Client s3Client) {
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        return s3Client.listObjectsV2(listObjectsRequest);
    }

    public String getFilefromS3Bucket(S3Client s3Client, String filename) throws IOException {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .build();
        InputStream objectInputStream = s3Client.getObject(getObjectRequest);
        BufferedReader reader = new BufferedReader(new InputStreamReader(objectInputStream));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        return content.toString();
    }

    public static void uploadFileToS3Bucket(S3Client s3Client,File file) {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(file.getName())
                .build();

        s3Client.putObject(request, RequestBody.fromFile(file));
    }

    public static boolean validateS3Bucket(S3Client s3ClientInfo) {
        boolean result = false;
        if (!doesBucketExist(s3ClientInfo)) {
            s3ClientInfo.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
            LOGGER.info("Creating bucket: " + bucketName);
            result = true;
        } else {
            LOGGER.info("Bucket already exists: " + bucketName);
            result = true;
        }

        return result;
    }

    public static boolean doesBucketExist(S3Client s3Client) {
        try {
            HeadBucketResponse response = s3Client.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
            LOGGER.info("Creating bucket for application: " + bucketName);
            return true;
        } catch (Exception e) {
            LOGGER.error("Bucket is not found. Creating bucket now...");
            return false;
        }
    }
}
