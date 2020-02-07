package com.zdzimi.flashcards.data.maper;

import com.zdzimi.flashcards.core.model.Flashcard;
import com.zdzimi.flashcards.data.dto.FlashcardDTO;

import java.util.ArrayList;
import java.util.List;

public class FlashcardMaper {

    public static Flashcard mapFlashcardDTOToFlashcard(FlashcardDTO flashcardDTO){
        return new Flashcard(
                flashcardDTO.getFlashcardId(),
                flashcardDTO.getQuestion(),
                flashcardDTO.getAnswer(),
                flashcardDTO.getFlashcardStatus(),
                flashcardDTO.getCategoryId()
        );
    }

    public static List<Flashcard> mapFlashcardDTOListToFlashcardList(List<FlashcardDTO> flashcardDTOList) {
        List<Flashcard> flashcardList = new ArrayList<>();
        for (FlashcardDTO f : flashcardDTOList){
            flashcardList.add(mapFlashcardDTOToFlashcard(f));
        }
        return flashcardList;
    }

    public static FlashcardDTO mapFlashcardToFlashcardDTO(Flashcard flashcard) {
        return new FlashcardDTO(
                flashcard.getFlashcardId(),
                flashcard.getQuestion(),
                flashcard.getAnswer(),
                flashcard.getFlashcardStatus(),
                flashcard.getCategoryId()
        );
    }

    public static List<FlashcardDTO> mapFlashcardListToFlashcardDTOList(List<Flashcard> done) {
        List<FlashcardDTO> flashcardDTOList = new ArrayList<>();
        for(Flashcard flashcard : done){
            flashcardDTOList.add(new FlashcardDTO(
                    flashcard.getFlashcardId(),
                    flashcard.getQuestion(),
                    flashcard.getAnswer(),
                    flashcard.getFlashcardStatus(),
                    flashcard.getCategoryId()
            ));
        }
        return flashcardDTOList;
    }
}
