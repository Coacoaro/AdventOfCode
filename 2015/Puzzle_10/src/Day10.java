public class Day10 {
    public static void main(String[] args) {
        String input = "1321131112";
        for (int i = 0; i < 40; i++) {
            input = lookAndSay(input);
        }
        System.out.println("length is "+input.length());

        // Part 2
        for (int i = 0; i < 10; i++) {
            input = lookAndSay(input);
        }
        System.out.println("length is "+input.length());
    }

    public static String lookAndSay(String input) {
        StringBuilder result = new StringBuilder();
        char[] c = input.toCharArray();
        int same = 1;

        for(int i = 0; i < c.length; i++) {
            try {
                if (c[i] == c[i + 1]) {
                    same++;
                } else {
                    result.append(same).append(c[i]);
                    same = 1;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                result.append(same).append(c[i]);
                same = 1;
            }
        }
        return String.valueOf(result);
    }
}
