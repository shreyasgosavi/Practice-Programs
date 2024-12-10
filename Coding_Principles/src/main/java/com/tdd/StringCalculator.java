package com.tdd;

import com.tdd.constants.ConstantValues;
import com.tdd.customexception.InvalidDelimiterException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class StringCalculator {

    // Goal : To take input as a string which will have delimiters specified between [] followed by a boundary '~' after that there will be subsequent numbers separated by those delimiters.

   /*
    Points :
        1. We need to find addition of numbers separated by those delimiters.
        2. If incorrect value is present between delimiters then raise an exception & add those values in the exception message.
           Eg. If delimiter is '{}' & string is '3{}4{}$5'

           Input String --> "[{}]~3{}4{}$5"

           --> Raise exception and add that value in the message.


        3. If no delimiter is specified then consider ','  as default delimiter.
        3. Don't allow negative numbers, if present add that as well in the exception message
        4. If number is greater than 1000 then ignore that number and add other values.
    */

    public int getResult(String input) {

        if (Objects.isNull(input) || input.isEmpty()) {
            return 0;
        }

        String delimiter = ",+|\n+";
        String inputString = input;

        if (input.contains("~")) {

            String[] seperatedContent = input.split("~");
            //Checking if delimiter-list is correct or not
            if (checkValidDelimiterList(seperatedContent[0])) {
                delimiter = generateDelimiterList(seperatedContent[0]);
            } else {
                throw new InvalidDelimiterException(ConstantValues.INVALID_DELIMITER_EXCEPTION_MESSAGE);
            }

            if(seperatedContent.length <= 2)
                inputString = seperatedContent[1];
            else{
                throw new InvalidDelimiterException(ConstantValues.TILDE_AS_DELIMITER_EXCEPTION_MESSAGE);
            }



        }

        String[] numbers = inputString.split(delimiter, 0);

        List<String> finalNumber = Stream.of(numbers).filter(number -> !number.isEmpty()).toList();

//        System.out.println("Numbers after splitting " + finalNumber);
        ArrayList<String> ignoredValue = new ArrayList<>();
        ArrayList<Integer> negativeValue = new ArrayList<>();

        int sum = 0;

        //Adding numbers from the generated list
        for (String s : finalNumber) {
            //Adding exceptional-handling to take care of values other than integers
            try {
                int number = Integer.parseInt(s.trim());
//                System.out.println("Number " + number);
                if (number < 0) {
                    negativeValue.add(number);
                }
                if (number < 1000)
                    sum += number;
            } catch (Exception e) {
                System.out.println(s);
                ignoredValue.add(s);
            }
        }

        StringBuilder exceptionMessages = new StringBuilder();
        if (!negativeValue.isEmpty()) {
            exceptionMessages.append("Negative numbers involved in the list : " + negativeValue);
            exceptionMessages.append("\n");
        }
        if (!ignoredValue.isEmpty()) {
            exceptionMessages.append("Delimiters may not be specified correctly between numbers or Value specified is not a valid number. List of invalid values : " + ignoredValue);
        }

        if (!negativeValue.isEmpty() || !ignoredValue.isEmpty()) {
            throw new ArithmeticException(exceptionMessages.toString());
        }

        return sum;
    }


    public boolean checkValidDelimiterList(String delimiterList) {
        Pattern pattern = Pattern.compile("(\\[[^\\]0-9]+])+");
        Matcher matcher = pattern.matcher(delimiterList);
        return matcher.matches();
    }

    public String generateDelimiterList(String inputDelimiterString) {


            int iterate = 0;
            int delimiterLength = inputDelimiterString.length();

            StringBuilder tempStringBuider = new StringBuilder();
            StringBuilder finalString = new StringBuilder();

            while (iterate < delimiterLength) {

                char tempChar = inputDelimiterString.charAt(iterate);
                iterate++;

                if (tempChar != ']') {
                    tempStringBuider.append(tempChar);
                } else {
                    tempStringBuider.deleteCharAt(0);
                    finalString.append(Pattern.quote(tempStringBuider.toString()) + "+");
                    finalString.append("|");

                    tempStringBuider.setLength(0);
                }
            }
            finalString.append(Pattern.quote("\n") + "+");
            return finalString.toString();

    }

    public static void main(String[] args) {

        boolean running = true;
        int count = 0;

        StringCalculator stringCalculator = new StringCalculator();
        String input = "";
        char decision = 'N';

        Scanner sc  = new Scanner(System.in);

        System.out.println("Welcome to String Calculator \uD83E\uDDEE !!");

        System.out.println("Do read README.MD file to understand unique ways to give string input");

        System.out.println("---------------------------------------------------------------------");

        while(running){


            System.out.println("Take 🎬: "+(count+1));
            System.out.println("Enter input string : ");
            System.out.println("Eg : Without delimiters --> '3,4' ----  Output --> 7");
            System.out.println("Eg : With delimiters --> '[:][#]~3:4#4' ----  Output --> 11");

            input=sc.next();

            try{
                int ans = stringCalculator.getResult(input);
                System.out.println("Result after calculation is "+ans);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println("Want to run more calculations ? Y or N");
            decision = sc.next().toUpperCase().charAt(0);

            running= (decision=='Y')?true:false;
            count++;
        }

        if(count < 5){
            System.out.println("Exiting soon!! Fewer calculations made.. ");
        }

        else if(count >= 5 && count < 10){
            System.out.println("That was a good warm-up ");
        }
        else{
            System.out.println("Too many results to handle !! Hope you have kept track of all of them");
        }

    }

}
