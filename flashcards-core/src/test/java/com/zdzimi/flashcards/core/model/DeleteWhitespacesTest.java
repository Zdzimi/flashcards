package com.zdzimi.flashcards.core.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class DeleteWhitespacesTest {

    @Parameterized.Parameter(0)
    public String wordWithWhitespace;
    @Parameterized.Parameter(1)
    public String wordResult;

    @Parameterized.Parameters(name = "{index}: Input: \"{0}\" -> \"{1}\"")
    public static List data(){
        return Arrays.asList(new String[][]{
                {"  sqw","sqw"},
                {"  s qw ","s qw"},
                {"s q w  ","s q w"},
                {"  word    ","word"},
                {" w ord; 2 ","w ord; 2"},
                {" .. ..  .",".. ..  ."}
        });
    }

    @Test
    public void test_parameterized(){
        FlashcardBox flashcardBox = new FlashcardBox();
        String word = wordWithWhitespace;
        Assert.assertEquals("incorrect", wordResult, flashcardBox.deleteWhitespaces(word));
    }

}
