package calculator;

import camp.nextstep.edu.missionutils.Console;
public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();
        String delimiter = classifyDelimiter(input);
        System.out.println(delimiter);

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
}

//   //;\n1;2;3