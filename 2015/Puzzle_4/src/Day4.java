import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {
    public static void main(String[] args) {
        String input = "bgvyzdsv";
        String inputNum = "";
        String hash = "";

        boolean leadingZeros = false;
        int i = 1;
        while(!leadingZeros) {
            inputNum = input + i;
            hash = getMd5(inputNum);
            leadingZeros = check5Zeros(hash);
            i++;
        }
        System.out.println(inputNum);
        System.out.println(hash);

        i = 1;
        leadingZeros = false;
        while(!leadingZeros) {
            inputNum = input + i;
            hash = getMd5(inputNum);
            leadingZeros = check6Zeros(hash);
            i++;
        }
        System.out.println(inputNum);
        System.out.println(hash);

    }

    public static boolean check5Zeros(String hash) {
        char[] hashChar = hash.toCharArray();
        String first5 = "";
        for(int c = 0; c < 5; c++) {
            first5 += (Character.toString(hashChar[c]));
        }

        return first5.equals("00000");
    }

    public static boolean check6Zeros(String hash) {
        char[] hashChar = hash.toCharArray();
        String first6 = "";
        for(int c = 0; c < 6; c++) {
            first6 += (Character.toString(hashChar[c]));
        }

        return first6.equals("000000");
    }


    //**Copied**//  https://www.geeksforgeeks.org/md5-hash-in-java/
    // Java program to calculate MD5 hash value
    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}