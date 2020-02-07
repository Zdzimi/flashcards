package com.zdzimi.flashcards.desktop.controller;

import com.zdzimi.flashcards.core.model.Category;

import java.util.Date;

public class DateUtils {

    public static boolean resume(Category category) {
        Date lastUpdate = category.getLastUpdate();

        if (lastUpdate == null){
            return true;
        }

        Date now = new Date();
        // 1000 * 60 * 60 * 24 * 5 == 5 days
        long minimalBreak = (long) (1000 * 60 * 60 * 24 * 5);
        Date date = new Date(lastUpdate.getTime() + minimalBreak);

        if (now.after(date)){
            return true;
        }
        return false;
    }
}
