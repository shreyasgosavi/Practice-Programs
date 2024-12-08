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

        //OLD       -- If there are characters other than numbers ignore/skip it & consider subsequent numbers | Optional -- print values that were not considered
//        Assertions.assertEquals(4, sc.getResult("2,1,5%$ddc,three,1"));

        //NEW

        Assertions.assertThrows(ArithmeticException.class, ()->{sc.getResult("2,1,5%$ddc,three,1");});

    }


    @Test
    void throwAnExceptionForNegativeNumberAndADDTheValueInExceptionMessage(){

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
        Assertions.assertEquals(9,sc.getResult("[;]~3;6"));
        Exception exception = Assertions.assertThrows(ArithmeticException.class, ()->{sc.getResult("[;]~3;6;-2");});
        Assertions.assertTrue(exception.getMessage().contains("[-2]"));
    }

    @Test
    void getResultAndHandleWhereThereAreConsecutiveDelimiters(){

        // Even single delimiter can handle multiple occurrence of that delimiter
        Assertions.assertEquals(15,sc.getResult("[;]~3;;;6;;6"));
    }

    @Test
    void addMultipleDelimiter(){
        // Add multiple delimiter separated by [d1][d2] before ~  And delimiter should occur once
        Assertions.assertEquals(18,sc.getResult("[;][:][.][%]~3;;;6;;:6:3"));
    }



    @Test
    void ifNoProperPatternIsPresentThenThrowException(){

        //OLD
        // Here no ~ symbol is there so default delimiter is ',' but the string contains characters other than
        //',' so it is a valid string, hence return 0 as the answer.
//        Assertions.assertEquals(0,sc.getResult("3;;;6;;:6:3"));

        //NEW
        // Here no ~ symbol is there so default delimiter is ',' but the string contains characters other than',' so it is not a valid string, hence throw exception.
//        Assertions.assertEquals(0,sc.getResult("3;;;6;;:6:3"));

        Assertions.assertThrows(ArithmeticException.class, ()->{sc.getResult("3;;;6;;:6:3");});
    }

    @Test
    void checkValidDelimiterListValue(){
        Assertions.assertTrue(sc.checkValidDelimiterList("[;][:][\"]"));
    }

    @Test
    void checkInvalidDelimiterListValue(){
        Assertions.assertFalse(sc.checkValidDelimiterList("[;]:][{][}]"));
    }

    @Test
    void checkDelimiterOfLengthMoreThanOneInBetweenSquareBrackets(){
        //Here Delimiter of more than one length is added eg ";;","{}"
        Assertions.assertTrue(sc.checkValidDelimiterList("[;;][{}][}]"));
    }

    @Test
    void checkCorrectAnswer(){
        // Add multiple delimiter separated by [d1][d2] before ~  And delimiter should occur once
        Assertions.assertEquals(75,sc.getResult("[{#][%%][+]~3{####{#6%%{#63+3"));
    }


    @Test
    void ignoreValueGreaterThan1000(){
        Assertions.assertEquals(5,sc.getResult("3,1,10002,1"));
    }

    @Test
    void throwExceptionOfWhenDelimiterListIsInvalidIfSpecifiedUsingTilde(){

        // Add invalid-delimiter before '~', it should throw exception
        Assertions.assertThrows(ArithmeticException.class,()->{sc.getResult("wfwesd~76");});
    }



}