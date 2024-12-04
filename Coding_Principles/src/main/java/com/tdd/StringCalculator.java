package com.tdd;

import java.util.ArrayList;

public class StringCalculator {

    // Goal : To take input as a string which will tell what operation to perform with subsequent  numbers separated by delimiter mentioned in the input itself.

    public int getResult(String input){

        if(input == null || input.isEmpty()){
            return 0;
        }

        String[] numbers = input.split(",+",0);
        ArrayList<String> ignoredValue = new ArrayList<>();

        int sum = 0;
        for(String s : numbers){
            //Adding exceptional-handling to take care of values other than integers
            try {

                int number = Integer.parseInt(s.trim());
                if(number < 0){
                    throw new ArithmeticException("Negative numbers not allowed");
                }
                sum += Integer.valueOf(s.trim());
            }
            catch(ArithmeticException arithmeticException){
                throw new ArithmeticException("Negative numbers not allowed");
            }
            catch (Exception e){
                ignoredValue.add(s);
            }
        }

        return sum;
    }

}
