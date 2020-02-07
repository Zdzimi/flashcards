package com.zdzimi.flashcards.core.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class CheckAnswerTest {

    private static FlashcardBox flashcardBox;
    @Parameterized.Parameter(0)
    public String requiredAnswer;
    @Parameterized.Parameter(1)
    public String answer;
    @Parameterized.Parameter(2)
    public boolean isCorrect;

    @Parameterized.Parameters(name = "{index}: \"{0}\" equal \"{1}\" -> {2}")
    public static List data(){
        return Arrays.asList(new Object[][]{
                {"Answer", "answer", true},
                {"Answer", "Answer", true},
                {"Answer", "ANSWER", true},
                {"Answer", " answer", true},
                {"Answer", " Answer", true},
                {"Answer", " ANSWER", true},
                {" Answer", "answer", true},
                {" Answer", "Answer", true},
                {" Answer", "ANSWER", true},
                {" Answer ", "answer", true},
                {" Answer ", "Answer", true},
                {" Answer ", "ANSWER", true},
                {"Answer", "a nswer", false},
                {"Ans wer", " answer ", false},
                {"Answer", "ansswer", false},
                {"Answerr", "answer", false},
                {"A n s w e r", "answer", false},
                {"Answer", " a n s w e r ", false},
                {"Andwer", "answer", false},
                {"Answer.", "answer", false},
                {"Answer", "answer;", false},
        });
    }

    @Before
    public void prepareFlashcardBox(){
        List<Flashcard> flashcards = Arrays.asList(new Flashcard("question",requiredAnswer));
        Category category = new Category();
        flashcardBox = FlashcardBox.createNewFlashcardBox(flashcards,category);
        flashcardBox.getRandomFlashcardWithMinimalStatus();

    }

    @Test
    public void test_parameterized(){
        String word = answer;
        Assert.assertEquals("incorrect ", isCorrect, flashcardBox.checkAnswer(word));
    }
}
