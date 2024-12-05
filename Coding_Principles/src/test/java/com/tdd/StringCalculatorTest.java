package com.tdd;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class StringCalculatorTest {

    StringCalculator sc = new StringCalculator();

    @Test
    void getResultAsZeroWhenInputStringIsEmpty(){
        //Test for empty-string
        Assertions.assertEquals(0, sc.getResult(""));
    }

    @Test
    void getResultWhenInputHasSingleDigitValue(){
        //Test for single-digit value
        Assertions.assertEquals(1,sc.getResult("1"));
    }

    @Test
    void getResultWhenInputHasTwoDigits(){
        //Given two numbers separated by comma,result should be addition of those
        Assertions.assertEquals(3, sc.getResult("1,2"));
    }

    @Test
    void getResultWhenInputHasAnyNumberOfDigits(){
        //Given any numbers separeted by ','; give output the result of all the numbers
        Assertions.assertEquals(11, sc.getResult("1,2,2,6"));
    }

    @Test
    void getResultAndIgnoreWhiteSpacesInTheString(){
        //Ignore White Spaces that are added in the string, just consider number
        Assertions.assertEquals(4, sc.getResult("2   ,  2"));
    }

    @Test
    void getResultAndHandleCaseWithMultipleConsecutiveDelimiters(){
        //Handle cases where no number is specified between commas -- Passed
        Assertions.assertEquals(4, sc.getResult("2   ,  2,,,"));
    }
    @Test
    void getResultAndHandleCaseWhenThereAreNumbersBetweenMultipleDelimiters(){

        //Handle cases where no number is specified between commas & a number at the end
        Assertions.assertEquals(9, sc.getResult("2  ,2,,,5"));
    }
    @Test
    void getResultAndIgnoreValuesWhichAreNotNumbers(){

        //If there are characters other than numbers ignore/skip it & consider subsequent numbers | Optional -- print values that were not considered
        Assertions.assertEquals(4, sc.getResult("2,1,5%$ddc,three,1"));
    }

    @Test
    void getResultThrowExceptionForNegativeNumberAndADDTheValueInExceptionMessage(){

        //Not allowing negative numbers, should throw an exception
        //Added lambda expression as assert-Throws expects executable
        Exception e = Assertions.assertThrows(ArithmeticException.class, ()->{sc.getResult("-3,5,-6,3");});

        //Displaying -ve values in exception-message
        Assertions.assertTrue(e.getMessage().contains("[-3, -6]"));
    }

    @Test
    void getResultAllowingCustomDelimiterAndHaveDefaultDelimiterValueWhenNotSpecified(){

        //Adding custom Delimiter at the beginning with rule that it should be seperated with ~ & the
        //entire string should contain single ~ if not mentioned then default is ','
        Assertions.assertEquals(9,sc.getResult(";~3;6"));

        Exception exception = Assertions.assertThrows(ArithmeticException.class, ()->{sc.getResult(";~3;6;-2");});
        Assertions.assertTrue(exception.getMessage().contains("[-2]"));
    }


}
