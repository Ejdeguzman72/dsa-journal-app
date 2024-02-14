package com.dsa.personaljournalapp.component;

import com.dsa.personaljournalapp.service.S3JournalBucketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class ApplicationStartupRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartupRunner.class);

    @Autowired
    S3Client s3Client;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("Checking for S3 Bucket");
        boolean result = S3JournalBucketService.validateS3Bucket(s3Client);
        if (result) {
            LOGGER.info("S3 Bucket is validated");
        } else {
            LOGGER.error("S3 Bucket configuration is experiencing issues");
        }
    }
}
