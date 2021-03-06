package com.zdzimi.flashcards.core.model;

public class Flashcard {

    private Integer flashcardId;
    private String question;
    private String answer;
    private Integer flashcardStatus;
    private Integer categoryId;

    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.flashcardStatus = 0;
    }

    public Flashcard(String question, String answer, Integer categoryId) {
        this.question = question;
        this.answer = answer;
        this.flashcardStatus = 0;
        this.categoryId = categoryId;
    }

    public Flashcard(Integer flashcardId,
                     String question,
                     String answer,
                     Integer flashcardStatus,
                     Integer categoryId) {
        this.flashcardId = flashcardId;
        this.question = question;
        this.answer = answer;
        this.flashcardStatus = flashcardStatus;
        this.categoryId = categoryId;
    }

    public Integer getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(Integer flashcardId) {
        this.flashcardId = flashcardId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getFlashcardStatus() {
        return flashcardStatus;
    }

    public void setFlashcardStatus(Integer flashcardStatus) {
        this.flashcardStatus = flashcardStatus;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
