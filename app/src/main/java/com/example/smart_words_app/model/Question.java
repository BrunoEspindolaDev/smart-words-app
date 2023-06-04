package com.example.smart_words_app.model;

import android.graphics.Bitmap;

public class Question {

    private Bitmap image;
    private Word option1;
    private Word option2;
    private Word option3;
    private Word correctOption;

    public Question() {
    }

    public Question(Bitmap image, Word option1, Word option2, Word option3, Word correctOption) {
        this.image = image;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.correctOption = correctOption;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Word getOption1() {
        return option1;
    }

    public void setOption1(Word option1) {
        this.option1 = option1;
    }

    public Word getOption2() {
        return option2;
    }

    public void setOption2(Word option2) {
        this.option2 = option2;
    }

    public Word getOption3() {
        return option3;
    }

    public void setOption3(Word option3) {
        this.option3 = option3;
    }

    public Word getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Word correctOption) {
        this.correctOption = correctOption;
    }
}
