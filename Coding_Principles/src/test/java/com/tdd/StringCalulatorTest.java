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
        //Given any numbers separeted by ','; give output the result of all the numbers
        Assertions.assertEquals(5, sc.getResult("1,2,2"));

        //Ignore White Spaces that are added in the string, just consider number
        Assertions.assertEquals(4, sc.getResult("2   ,  2"));

        //Handle cases where no number is specified between commas -- Passed
        Assertions.assertEquals(4, sc.getResult("2   ,  2,,,"));

        //Handle cases where no number is specified between commas & a number at the end
        Assertions.assertEquals(9, sc.getResult("2  ,2,,,5"));
    }


}
