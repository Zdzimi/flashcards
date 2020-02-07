package com.zdzimi.flashcards.core.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlashcardBox {

    private Category currentCategory;
    private List<Flashcard>[] tabList = new List[5];
    private Flashcard currentFlashcard;
    private List<Flashcard> done;
    private int counter;

    {
        for(int i = 0; i < tabList.length; i++)
            tabList[i] = new ArrayList();
        done = new ArrayList<>();
        counter = 0;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public List<Flashcard> getDone() {
        return done;
    }

    public Flashcard getCurrentFlashcard() {
        return currentFlashcard;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public static FlashcardBox createNewFlashcardBox(List<Flashcard> flashcardList, Category category) {
        FlashcardBox flashcardBox = new FlashcardBox();
        flashcardBox.currentCategory = category;
        flashcardBox.done = new ArrayList<>();

        for (Flashcard flashcard : flashcardList) {
            flashcardBox.tabList[flashcard.getFlashcardStatus()].add(flashcard);
        }
        return flashcardBox;
    }

    public Flashcard getRandomFlashcardWithMinimalStatus() {
        Random random = new Random();
        for (int i = 0; i < tabList.length; i++) {
            if (tabList[i].size() > 0) {
                int number = random.nextInt(tabList[i].size());
                currentFlashcard = tabList[i].remove(number);
                return currentFlashcard;
            }
        }
        return null;
    }

    public boolean checkAnswer(String answerText) {
        String[] goodAnswers = currentFlashcard.getAnswer().split(",");
        for (int i = 0; i < goodAnswers.length; i++) {
            if (deleteWhitespaces(answerText).equalsIgnoreCase(deleteWhitespaces(goodAnswers[i]))){
                return true;
            }
        }
        return false;
    }

    public String deleteWhitespaces(String goodAnswer) {
        String pureAns = goodAnswer;
        while (pureAns.startsWith(" ") || pureAns.endsWith(" ")){
            pureAns = pureAns.startsWith(" ") ? pureAns.replaceFirst(" ","") : pureAns;
            pureAns = pureAns.endsWith(" ") ? pureAns.substring(0,pureAns.length() - 1) : pureAns;
        }
        return pureAns;
    }

    public void addCurrentFlashcardToDoneList() {
        done.add(currentFlashcard);
    }

    public void incrementCurrentFlashcardStatus() {
        Integer status = currentFlashcard.getFlashcardStatus() == 4 ? 4 : currentFlashcard.getFlashcardStatus() + 1;
        currentFlashcard.setFlashcardStatus(status);
    }

    public void resetCurrentFlashcardStatus() {
        currentFlashcard.setFlashcardStatus(0);
    }

    public void setCategoryLastUpdate() {
        Date date = new Date(new java.util.Date().getTime());
        currentCategory.setLastUpdate(date);
    }

    public void incrementCunter() {
        counter++;
    }
}
