package com.dsa.personaljournalapp.domain;

import com.dsa.personaljournalapp.model.JournalEntryUploadResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.File;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUpdateJournalResponse {

    JournalEntryUploadResponse journalEntryUploadResponse;

    File file;

    public JournalEntryUploadResponse getJournalEntryUploadResponse() {
        return journalEntryUploadResponse;
    }

    public void setJournalEntryUploadResponse(JournalEntryUploadResponse journalEntryUploadResponse) {
        this.journalEntryUploadResponse = journalEntryUploadResponse;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
