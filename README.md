# 📚 String Calculator 🧮

**Addition** is a fundamental task where we combine quantities to arrive at a result. The **String Calculator** is a popular 🧗‍♂️ TDD exercise that adds a unique twist to the everyday addition task. While the ultimate goal is simple—get the sum—the way inputs are provided makes this challenge exciting! 🚀

In the **String Calculator**, the input is given as a string (alphanumeric characters). First, we specify **list(s) of delimiters**, followed by numbers separated by those delimiters. The numbers are identified and added to compute the final result.

This project showcases the implementation of the String Calculator using Java 🖥️ while adhering to the **Test-Driven Development (TDD)** principle:
✨ Write the expectations step-by-step as test cases,
✨ Then implement logic to meet those expectations.

> To ensure simplicity and consistency **custom rules** haven been added for input validation and operations.

## Rules:

1️⃣ Input is always a String 📝

2️⃣ Specify multiple delimiters enclosed in **[]**, followed by a separator **~** and then the **numbers-delimiters** pattern.

        ✅ Example: `[:][;]~2:2:5;2 → Output: 11`	
         
3️⃣ Do not use **~** as a delimiter, and include it only once as the separator.

        ❌ `"[~][:]~2:2:2" → Invalid`
        ❌ `"[;][#]~2#2~4" → Invalid`
          
4️⃣ If no delimiter is provided, the default delimiter is **,**

        ✅ Example: "4,5,6" → Output: 15
        
5️⃣ Delimiters can be of any length!

        ✅ Example: [::][,]~2::2,3 → Output: 7
        
6️⃣ Ignore numbers greater than 1000.

        ✅ Example: [::][,]~2::1002,3 → Output: 5
        
7️⃣ Newline characters (\n) are valid between numbers.

        ✅ Example: [::][,]~2\n2\n3::3 → Output: 10
        
8️⃣ Negative numbers are not allowed and throw an exception 📛 with the invalid numbers mentioned in the message.


9️⃣ Invalid numbers also result in an exception, detailing the problematic values.

---

## 🌟 Special Features

✨ Delimiter validation: Custom InvalidDelimiterException is thrown if delimiters don't meet expectations.

✨ Error handling: ArithmeticException for invalid or negative numbers.

✨ Flexibility: Supports multiple newlines **(\n)** between numbers.

---

That's a wrap on the **String Calculator exercise!** 🎉
This project isn't just about addition—it's about building robust, *testable code* while mastering **TDD principles**. 

Have fun coding!!




