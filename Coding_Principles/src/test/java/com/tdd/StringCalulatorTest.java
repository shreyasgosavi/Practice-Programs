package com.tdd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class StringCalulatorTest {

    StringCalculator sc = new StringCalculator();
    @Test
    void getResultTest(){

        //Test for empty-string
        Assertions.assertEquals(0, sc.getResult(""));
        //Test for single-digit value
        Assertions.assertEquals(1,sc.getResult("1"));

        //Given two numbers separated by comma,result should be addition of those
        Assertions.assertEquals(3, sc.getResult("1,2"));

    }


}
