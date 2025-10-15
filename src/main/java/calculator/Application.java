package calculator;

import camp.nextstep.edu.missionutils.Console;
public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        String delimiter = classifyDelimiter(input);

        int[] delimiterIndexes = getDelimiterIndexes(delimiter, input);

        String IntegerArray = getIntegerArray(input, delimiterIndexes);
        System.out.println(IntegerArray);
    }

    private static String classifyDelimiter(String input) {
        if (input.contains(":")) {
            return ":";
        } else if (input.contains(",")) {
            return ",";
        } else if (input.contains("//") && input.contains("\\n")) {
            return getCustomDelimiter(input);
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

        int count = 0;

        int pos = input.indexOf(delimeter);
        while (pos != -1) {
            indexes[count++] = pos;
            pos = input.indexOf(delimeter, pos + delimeter.length());
        }
        return indexes;
    }

    private static String getIntegerArray(String input, int[] delimiterIndexes) {
        String integerArray;
        int count = 0;

        int startIndex = 0;
        if(input.contains("\\n")) {
            startIndex = input.indexOf("\\n") + 2;
            count++;
        }

        integerArray = input.substring(startIndex, delimiterIndexes[count]);
        while (delimiterIndexes[count+1] != 0) {
            integerArray = integerArray.concat(input.substring(delimiterIndexes[count] + 1, delimiterIndexes[count + 1]));
            count++;
        }
        integerArray = integerArray.concat(input.substring(delimiterIndexes[count] + 1));

        return integerArray;
    }

}

//   //;\n1;2;3