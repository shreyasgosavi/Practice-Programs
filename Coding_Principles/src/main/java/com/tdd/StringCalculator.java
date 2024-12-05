package com.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

    // Goal : To take input as a string which will tell what operation to perform with subsequent  numbers separated by delimiter mentioned in the input itself.

    public int getResult(String input) {

        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter=",+";
        String inputString = input;

        if(input.contains("~")) {

            String[] seperatedContent = input.split("~");

            inputString = seperatedContent[1];

            //Checking if delimiter-list is correct or not
            if(checkValidDelimiterList(seperatedContent[0])){

                StringBuffer newDelimiter = new StringBuffer();

                delimiter = seperatedContent[0];
                int delimiterLength = delimiter.length();
                int iterate = 1;

                while(iterate < delimiterLength){
                    newDelimiter.append(Pattern.quote(""+delimiter.charAt(iterate))+"+|");
                    iterate+=3;
                }

                delimiter = newDelimiter.toString();
                System.out.println("Delimiter List "+delimiter);
            }

        }

        String[] numbers = inputString.split(delimiter, 0);

        List<String> finalNumber = Arrays.stream(numbers).filter((number)->{ return number!="";}).collect(Collectors.toList());

        System.out.println("Numbers after splitting "+finalNumber);
        ArrayList<String> ignoredValue = new ArrayList<>();
        ArrayList<Integer> negativeValue = new ArrayList<>();

        int sum = 0;
        for (String s : finalNumber) {
            //Adding exceptional-handling to take care of values other than integers
            try {
                int number = Integer.parseInt(s.trim());
                System.out.println("Number "+number);
                if (number < 0) {
                    negativeValue.add(number);
                }
                sum += number;
            } catch (Exception e) {
                System.out.println(s);
                ignoredValue.add(s);
            }
        }

        if(!negativeValue.isEmpty()){
            throw new ArithmeticException("Negative numbers involved in the list : "+negativeValue);
        }

        return sum;
    }


    public boolean checkValidDelimiterList(String delimiterList){
        Pattern pattern = Pattern.compile("(\\[[^\\]0-9]\\])+");
        Matcher matcher = pattern.matcher(delimiterList);
        return matcher.matches();
    }

}
