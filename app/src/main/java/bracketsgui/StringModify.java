package bracketsgui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StringModify {
    private StringModify () {};

    public static String replaceBrackets(String input, ArrayList<String> replaceBrackets, String newBracket) {
        String output = input;
        String leftRegex = "[";
        String rightRegex = "[";
        List<String> left = Arrays.asList("{", "[", "(");
        List<String> right = Arrays.asList("}", "]", ")");
        for (int i = 0; i < replaceBrackets.size(); i++) {
            String bracket = replaceBrackets.get(i);
            if (left.contains(bracket)) {
                if (bracket == "[") {
                    bracket = "\\" + bracket;
                }
                leftRegex += bracket;
            } else if (right.contains(bracket)) {
                if (bracket == "]") {
                    bracket = "\\" + bracket;
                }
                rightRegex += bracket;
            }
            
        }
        leftRegex += "]";
        rightRegex += "]";
        String leftBracket = newBracket.substring(0, 1);
        if (!leftRegex.equals("[]")) output = output.replaceAll(leftRegex, leftBracket);
        String rightBracket = newBracket.substring(2, 3);
        if (!rightRegex.equals("[]")) output = output.replaceAll(rightRegex, rightBracket);
        return output;
    }
}