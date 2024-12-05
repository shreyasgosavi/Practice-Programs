package com.tdd;

import java.util.ArrayList;

public class StringCalculator {

    // Goal : To take input as a string which will tell what operation to perform with subsequent  numbers separated by delimiter mentioned in the input itself.

    public int getResult(String input) {

        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter=",";
        String inputString = input;

        if(input.contains("~")) {
            //Introducing delimiter
            String[] seperatedContent = input.split("~");
            delimiter = seperatedContent[0];
            inputString = seperatedContent[1];
        }

        String[] numbers = inputString.split(delimiter + "+", 0);
        ArrayList<String> ignoredValue = new ArrayList<>();
        ArrayList<Integer> negativeValue = new ArrayList<>();

        int sum = 0;
        for (String s : numbers) {
            //Adding exceptional-handling to take care of values other than integers
            try {
                int number = Integer.parseInt(s.trim());
                if (number < 0) {
                    negativeValue.add(number);
                }
                sum += number;
            } catch (Exception e) {
                ignoredValue.add(s);
            }
        }

        if(!negativeValue.isEmpty()){
            throw new ArithmeticException("Negative numbers involved in the list : "+negativeValue);
        }

        return sum;
    }

}
