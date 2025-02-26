public class Day11 {
    public static void main(String[] args) {
        char[] input = "hxbxwxba".toCharArray();
        System.out.print("new password: "); System.out.println(nextPassword(input));        // Part 1
        System.out.print("next new password: "); System.out.println(nextPassword(input));   // Part 2
    }

    public static char[] nextPassword(char[] input) {
        do {
            increment(input);
            removeInvalidChar(input);
        } while (!threeStraight(input) || !checkDoubles(input));

        return input;
    }

    private static void increment(char[] input) {
        boolean incremented = false;
        int i = input.length - 1;

        while (!incremented && i > 0) {
            if (input[i] >= 'z') {
                input[i] = 'a';
                i--;
            } else {
                input[i] += 1;
                incremented = true;
            }
        }
        if (input[0] > 'z')   // Will loop around
            input[0] = 'a';
    }

    public static boolean checkDoubles(char[] input) {
        boolean double1 = false, double2 = false;
        char dupChar = 0;

        for (int i = 0; i < input.length - 1; i++) {
            if (input[i] == input[i + 1]) {
                if (!double1) {
                    dupChar = input[i];
                    double1 = true;
                } else if (input[i] != dupChar) {
                    double2 = true;
                }
            }
        }
        return double1 && double2;
    }

    public static boolean threeStraight(char[] input) {
        for (int i = 0; i < input.length - 2; i++) {
            if (input[i] + 1 == input[i + 1] && input[i] + 2 == input[i + 2]) {
                return true;
            }
        }
        return false;
    }

    public static void removeInvalidChar(char[] input) {
        boolean valid = true;

        for (int i = 0; i < input.length; i++) {
            if (!valid)
                input[i] = 'a';   // All elements later than index c will be 'a'

            if (input[i] == 'i' || input[i] == 'l' || input[i] == 'o') {
                input[i] = (char) (input[i] + 1);   // Replace with next letter
                valid = false;
            }
        }
    }
}
