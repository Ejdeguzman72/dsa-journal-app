package com.dsa.personaljournalapp.service;
;
import com.dsa.personaljournalapp.domain.AddUpdateJournalRequest;
import com.dsa.personaljournalapp.domain.AddUpdateJournalResponse;
import com.dsa.personaljournalapp.model.JournalEntry;
import com.dsa.personaljournalapp.model.JournalEntryUploadResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Service
public class JournalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JournalService.class);

    @Autowired
    S3Client s3Client;

    @Autowired
    S3JournalBucketService s3JournalBucketService;

    public AddUpdateJournalResponse uploadJournalEntry(AddUpdateJournalRequest request) throws IOException {
        AddUpdateJournalResponse response = new AddUpdateJournalResponse();
        JournalEntry journalEntry = new JournalEntry();
        JournalEntryUploadResponse journalEntryUploadResponse = new JournalEntryUploadResponse();

        journalEntry.setContent(request.getContent());
        File newUpload = createJournalEntryFile(journalEntry);
        journalEntryUploadResponse = readDataFromFileUpload(newUpload);

        response.setJournalEntryUploadResponse(journalEntryUploadResponse);
        response.setFile(newUpload.getAbsoluteFile());

        S3JournalBucketService.uploadFileToS3Bucket(s3Client,newUpload);

        return response;
    }

    public Map<String,String> retrieveAllS3Files() throws IOException {
        Map<String,String> s3FilenameContentMap = new HashMap<>();
        ListObjectsV2Response listObjectsV2Response = s3JournalBucketService.listFilesInS3Bucket(s3Client);

        List<S3Object> sortedObjects = listObjectsV2Response.contents().stream()
                .sorted(Comparator.comparing(S3Object::lastModified).reversed())
                .toList();

        for (S3Object s3Object : sortedObjects) {
            s3FilenameContentMap.put(s3Object.key(),s3JournalBucketService.getFilefromS3Bucket(s3Client,s3Object.key()));
        }

        return s3FilenameContentMap;
    }

    public File createJournalEntryFile(JournalEntry entry) throws IOException {
        File journalEntryFile = new File("Entry-" + LocalDate.now() + ".txt");
        try (FileWriter journalEntryFileWriter = new FileWriter(journalEntryFile)) {
            journalEntryFileWriter.write("Created: " + LocalDate.now());
            journalEntryFileWriter.write(System.lineSeparator());

            String content = entry.getContent();
            int length = content.length();
            int startIndex = 0;

            while (startIndex < length) {

                int endIndex = Math.min(startIndex + 80, length);
                if (endIndex < length && !Character.isWhitespace(content.charAt(endIndex))) {
                    while (endIndex > startIndex && !Character.isWhitespace(content.charAt(endIndex))) {
                        endIndex--;
                    }
                }
                journalEntryFileWriter.write(content.substring(startIndex, endIndex));
                journalEntryFileWriter.write(System.lineSeparator());
                startIndex = endIndex + 1;
            }
        } catch (Exception e) {
            LOGGER.error("Error retrieving file: " + e);
        }

        return journalEntryFile;
    }

    public JournalEntryUploadResponse readDataFromFileUpload(File file) throws FileNotFoundException {
        JournalEntryUploadResponse response = new JournalEntryUploadResponse();
        Scanner journalEntryScanner = new Scanner(file);
        StringBuilder entryStringBuilder = new StringBuilder();

        try {
            while (journalEntryScanner.hasNextLine()) {
                String data = journalEntryScanner.nextLine();
                char[] contentDataCharArray = data.toCharArray();
                for (char c : contentDataCharArray) {
                    entryStringBuilder.append(c);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error reading file: " + file.getName());
        } finally {
            journalEntryScanner.close();
        }

        response.setContent(entryStringBuilder.toString());
        response.setCreatedDate(LocalDate.now());

        return response;
    }
}