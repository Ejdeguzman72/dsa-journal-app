package com.dsa.personaljournalapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JournalEntry {

    @NotNull(message = "Field is missing/null, field content cannot be null")
    @Pattern(regexp = "[a-zA-Z ]{0,3000}", message = "Bad request, content field can only contain letters and spaces, special characters are not allowed")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
