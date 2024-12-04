package com.tdd;

public class StringCalculator {

    // Goal : To take input as a string which will tell what operation to perform with subsequent  numbers separated by delimiter mentioned in the input itself.

    public int getResult(String input){

        if(input.isEmpty() || input.isBlank()){
            return 0;
        }
        return Integer.valueOf(input);
    }

}
