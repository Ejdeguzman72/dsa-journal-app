package com.dsa.personaljournalapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3JournalBucketConfig {

    @Bean
    public S3Client s3Client() {
        Region region = Region.US_EAST_1;

        return S3Client.builder()
                .region(region)
                .build();
    }
}
