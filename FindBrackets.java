package forGit.Brackets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FindBrackets {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        getNumber(Integer.parseInt(bufferedReader.readLine()));
    }
    private static void getNumber(int number) {

        if(number > 0) {
            StringBuilder sb = new StringBuilder();
            int brackets = number * 2; //the number of brackets
            for (int i = 0; i < brackets; i++) {
                sb.append("1");
            }
            calculation(sb, brackets);
        } else {
            System.out.println("The number can't be negative");
        }

    }

    private static void calculation(StringBuilder sb, int brackets) {
        List<String> answers = new ArrayList<>();
        int cases = toDecimal(sb.toString()); //gets all possible sequences of parentheses.

        for (int j = 0; j <= cases; j++) {
            String[] numberBrackets = toBinary(j, brackets).split("");
            String answer = calculationBrackets(numberBrackets, brackets);
            if (answer != null) {
                answers.add(answer);
            }
        }

        for (String s : answers) {
            System.out.println(s);
        }
        System.out.println(answers.size());

    }
    //Finds all the correct sequence
    private static String calculationBrackets(String[] numberBrackets, int brackets) {
        StringBuilder answer = new StringBuilder();
        int count = 0;

        for (String numberBracket1 : numberBrackets) {
            if (count >= 0 && count <= brackets / 2) {
                if (numberBracket1.equals("0")) {
                    count++;
                } else if (numberBracket1.equals("1")) {
                    count--;
                }
            } else {
                return null;
            }
        }

        if (count != 0) {
            return null;
        }

        for (String numberBracket : numberBrackets) {
            if (numberBracket.equals("0")) {
                answer.append("(");
            } else {
                answer.append(")");
            }
        }
        return answer.toString();
    }
    //Converts from binary system to decimal system and returns decimal number which is all possible cases
    private static int toDecimal(String number) {
        String[] numbers = number.split("");
        int decimalNumber = 0;
        for (int i = 0; i < numbers.length; i++) {
            decimalNumber += Integer.parseInt(numbers[i]) * Math.pow(2, (numbers.length - 1 - i));
        }
        return decimalNumber;

    }
    //Converts from decimal system to binary system and adds the missing number.
    private static String toBinary(int number, int brackets) {
        StringBuilder allBinaryCases = new StringBuilder(Integer.toBinaryString(number));
        int lengthNum = allBinaryCases.length();
        for (int i = 0; i < brackets - lengthNum; i++) {
            allBinaryCases.insert(0, "0");
        }
        return allBinaryCases.toString();
    }
}
