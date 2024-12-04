package com.tdd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class StringCalulatorTest {

    StringCalculator sc = new StringCalculator();
    @Test
    void getResultTest(String numbers){

        //Test for empty-string
        Assertions.assertEquals(0, sc.getResult(""));
        //Test for single-digit value
        Assertions.assertEquals(1,sc.getResult("1"));

    }


}
