package com.dsa.personaljournalapp.domain;

import com.dsa.personaljournalapp.model.JournalEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddUpdateJournalRequest extends JournalEntry {

}
