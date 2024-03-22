import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day5 {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("Puzzle_5/input.txt");
        BufferedReader bReader = new BufferedReader(file);

        int nice = 0;
        int naughty = 0;
        String currentLine;
        while ((currentLine = bReader.readLine()) != null) {
            if (naughtyOrNice2(currentLine))
                nice++;
            else
                naughty++;
        }

        System.out.println(nice + " nice strings");
        System.out.println(naughty + " naughty strings");
    }

    // Check if string naughty or nice pt. 2
    public static boolean naughtyOrNice2 (String text){

        return checkTwice(text) && checkBetween(text);
    }

    // Checks if two adjacent characters repeat later in the string
    public static boolean checkTwice(String text) {
        boolean strTwice = false;
        char[] textChar = text.toCharArray();

        for (int i = 0; i < textChar.length - 3; i++) {
            String compare = String.valueOf(textChar[i]).concat(String.valueOf(textChar[i+1]));
            for (int j = i+2; j < textChar.length - 1; j++){
                String compareTo = String.valueOf(textChar[j]).concat(String.valueOf(textChar[j+1]));
                if (compare.equals(compareTo)) {
                    strTwice = true;
                    i = textChar.length;
                    break;
                }
            }
        }
        return strTwice;
    }

    // Checks for same letter 2 characters down
    public static boolean checkBetween(String text) {
        boolean charBetween = false;
        char[] textChar = text.toCharArray();

        for (int i = 0; i < textChar.length - 2; i++) {
            if (textChar[i] == textChar[i + 2]) {
                charBetween = true;
                break;
            }
        }
        return charBetween;
    }


    // Check if string naught or nice
    public static boolean naughtyOrNice (String text) {

        return checkDouble(text) && checkVowel(text) && checkString(text);
    }

    // Checks for double letter
    public static boolean checkDouble(String text) {
        boolean doubleChar = false;
        char[] textChar = text.toCharArray();

        for (int i = 0; i < textChar.length - 1; i++) {
            if (textChar[i] == textChar[i + 1]) {
                doubleChar = true;
                break;
            }
        }
        return doubleChar;
    }

    // Checks for 3 vowels
    public static boolean checkVowel(String text) {
        int vowels = 0;
        char[] textChar = text.toCharArray();

        for(char c : textChar){
            switch (c) {
                case 'a', 'e', 'i', 'o', 'u':
                    vowels++;
                    break;
            }
        }
        return vowels >= 3;
    }

    // Check for invalid strings
    public static boolean checkString(String text) {
        boolean validStr = true;
        char[] textChar = text.toCharArray();

        for (int i = 0; i < textChar.length - 1; i++) {
            if (textChar[i] == 'a' && textChar[i + 1] == 'b') {
                validStr = false;
                break;
            }
            if (textChar[i] == 'c' && textChar[i + 1] == 'd') {
                validStr = false;
                break;
            }
            if (textChar[i] == 'p' && textChar[i + 1] == 'q') {
                validStr = false;
                break;
            }
            if (textChar[i] == 'x' && textChar[i + 1] == 'y') {
                validStr = false;
                break;
            }
        }
        return validStr;
    }
}
