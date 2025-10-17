package calculator;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        String delimiter = classifyDelimiter(input);

        if(input.contains("\\n")) {
            input = deleteCustomDelimiter(input);
        }

        int[] delimiterIndexes = getDelimiterIndexes(delimiter, input);

        String integerArray = getIntegerArray(input, delimiterIndexes);

        int sum = 0;
        for (int i = 0; i < integerArray.length(); ++i) {
            sum += Integer.parseInt(integerArray.substring(i, i + 1));
        }
        System.out.println("결과 : " + sum);
    }

    private static String classifyDelimiter(String input) {
        if (input.contains("//") && input.contains("\\n")) {
            return getCustomDelimiter(input);
        } else if (input.contains(",")) {
            return ",";
        } else if (input.contains(":")) {
            return ":";
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static String getCustomDelimiter(String input) {
        int start = input.indexOf("//") + 2;
        int end = input.indexOf("\\n");

        if (start == end - 1) {
            return input.substring(start, end);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private static int[] getDelimiterIndexes(String delimeter, String input) {
        int[] indexes = new int[input.length()];
        Arrays.fill(indexes, -1);

        int count = 0;

        int pos = input.indexOf(delimeter);
        while (pos != -1) {
            indexes[count++] = pos;
            pos = input.indexOf(delimeter, pos + delimeter.length());
        }
        return indexes;
    }

    private static String getIntegerArray(String input, int[] delimiterIndexes) {
        if (delimiterIndexes[0] == -1) {
            return input;
        }

        String integerArray;

        int count = 0;
        integerArray = input.substring(0, delimiterIndexes[count]);
        while ((count + 1) < delimiterIndexes.length && delimiterIndexes[count + 1] != -1) {
            integerArray = integerArray.concat(input.substring(delimiterIndexes[count] + 1, delimiterIndexes[count + 1]));
            count++;
        }
        integerArray = integerArray.concat(input.substring(delimiterIndexes[count] + 1));

        return integerArray;
    }

    private static String deleteCustomDelimiter(String input) {
        int startIndex = input.indexOf("//");
        String head = input.substring(0, startIndex);

        int tailIndex = input.indexOf("\\n") + 2;
        String tail = input.substring(tailIndex);

        return head.concat(tail);
    }

}

//   //;\n1;2;3