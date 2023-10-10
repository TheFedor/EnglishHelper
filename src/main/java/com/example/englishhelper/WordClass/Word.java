package com.example.englishhelper.WordClass;

import lombok.Getter;

@Getter
public class Word {

    private String word;
    private String translation;
    private String definition;

    public Word(String word, String translation, String definition) {
        this.word = word;
        this.translation = translation;
        this.definition = definition;
    }
    //использовалось при простеньком тестировании для вывода в консоль
    //в рамках простенького локального приложения, не лишним будет оставить
    @Override
    public String toString() {
        return word + " - " + translation + " - " + definition;
    }
}
