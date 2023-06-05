package com.example.smart_words_app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Collection {

    private String id;
    private CollectionAttributes attributes;

    public Collection(String id, CollectionAttributes attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CollectionAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(CollectionAttributes attributes) {
        this.attributes = attributes;
    }
}
