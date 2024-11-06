package task5;

public class Main {

    public static void main(String[] args) {
        String text = "abc123!@#456def789";
        String result = findLongestNonAlphabeticSubstring(text);
        System.out.println("Самая длинная непострочная подстрока: " + result);
    }

    public static String findLongestNonAlphabeticSubstring(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        int maxLength = 0;
        int maxStart = 0;
        int currentStart = 0;
        int currentLength = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetter(c)) {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    maxStart = currentStart;
                }
                currentLength = 0;
            } else {
                if (currentLength == 0) {
                    currentStart = i;
                }
                currentLength++;
            }
        }

        if (currentLength > maxLength) {
            maxLength = currentLength;
            maxStart = currentStart;
        }

        return text.substring(maxStart, maxStart + maxLength);
    }
}
