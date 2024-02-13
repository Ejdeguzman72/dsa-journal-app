package com.dsa.personaljournalapp.controller;

import com.dsa.personaljournalapp.domain.AddUpdateJournalRequest;
import com.dsa.personaljournalapp.domain.AddUpdateJournalResponse;
import com.dsa.personaljournalapp.domain.UriConstants;
import com.dsa.personaljournalapp.model.JournalEntry;
import com.dsa.personaljournalapp.service.JournalService;
import com.dsa.personaljournalapp.service.S3JournalBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping(value = UriConstants.UPLOAD_JOURNAL_ENTRY_URI)
    public AddUpdateJournalResponse uploadJournalEntry(@RequestBody @Valid AddUpdateJournalRequest request) throws IOException {
        return journalService.uploadJournalEntry(request);
    }

    @GetMapping(value = UriConstants.RETRIEVE_ALL_FILES_FROM_S3)
    public Map<String,String> retrieveAllS3Files() throws IOException {
        return journalService.retrieveAllS3Files();
    }
}
