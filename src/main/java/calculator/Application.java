package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        String changeInput = changeDelimiter(input);

        String[] splitInput =  changeInput.split(",");

        for (String token : splitInput) {
            checkException(token);
        }

        int sum = 0;
        for (int i = 0; i < splitInput.length; ++i) {
            sum += Integer.parseInt(splitInput[i]);
        }
        System.out.println("결과 : " + sum);
    }

    private static void checkException(String token) {
        if (token.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (token.startsWith("-")) {
            throw new IllegalArgumentException();
        }
        if (!token.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException();
        }
    }


    //수정
    private static String changeDelimiter(String input) {
        if (input.contains("//") && input.contains("\\n")) {
            return changeCustomDelimiter(input);
        } else if (input.contains(":")) {
            return input.replaceAll(":", ",");
        } else if (input.contains(",")) {
            return input;
        } else {
            throw new IllegalArgumentException();
        }
    }

    //수정
    private static String changeCustomDelimiter(String input) {
        int start = input.indexOf("//") + 2;
        int end = input.indexOf("\\n");

        if (start == end - 1) {
            String delimiter = input.substring(start, end);
            input = deleteCustomDelimiter(input);
            return input.replaceAll(delimiter, ",");
        } else {
            throw new IllegalArgumentException();
        }
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