package com.example.englishhelper.WordClass;

public class Word {

    public String word;
    public String translation;
    public String definition;

    public Word(String word, String translation, String definition) {
        this.word = word;
        this.translation = translation;
        this.definition = definition;
    }

    @Override
    public String toString() {
        return word + " - " + translation + " - " + definition;
    }
}
