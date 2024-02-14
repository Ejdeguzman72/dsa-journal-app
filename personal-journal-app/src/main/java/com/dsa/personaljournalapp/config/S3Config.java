package com.dsa.personaljournalapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.accessKey}")
    private String AWS_ACCESS_KEY_ID;

    @Value("${aws.secretKey}")
    private String AWS_SECRET_ACCESS_KEY;

    @Value("${aws.region}")
    private String region;

    @Bean
    public S3Client s3Client() {
        Region region = Region.US_EAST_1;

        return S3Client.builder()
                .credentialsProvider(credentialsProvider())
                .region(region)
                .build();
    }

    private StaticCredentialsProvider credentialsProvider() {
        AwsBasicCredentials awsCredentials = AwsBasicCredentials .create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY);
        return StaticCredentialsProvider.create(awsCredentials);
    }
}