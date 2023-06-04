package com.example.smart_words_app.model;

public class Word {

    private String id;
    private WordAttributes attributes;

    public Word(String id, WordAttributes attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WordAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(WordAttributes attributes) {
        this.attributes = attributes;
    }
}
