package com.example.smart_words_app.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Word {
    @JsonProperty("id")
    private int id;

    @JsonProperty("attributes")
    private WordAttributes attributes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WordAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(WordAttributes attributes) {
        this.attributes = attributes;
    }
}

