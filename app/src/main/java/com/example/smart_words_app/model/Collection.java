package com.example.smart_words_app.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Collection {
    @JsonProperty("id")
    private int id;

    @JsonProperty("attributes")
    private CollectionAttributes attributes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CollectionAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CollectionAttributes attributes) {
        this.attributes = attributes;
    }
}

