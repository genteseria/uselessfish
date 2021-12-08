package core;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Uselessfish {

    private static String padLeftZeros(String input, int len) {
        if (input.length() >= len) {
            return input;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < len - input.length()) {
            sb.append('0');
        }
        sb.append(input);
        return sb.toString();
    }

    private static int[] stringToIntArray(String s) {
        int[] arr = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = (int) s.charAt(i);
        }
        return arr;
    }

    private static String intArrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < arr.length; i++) {
            sb.append((char) arr[i]);
        }
        return sb.toString();
    }

    private static int[] getStringArray(String s) {
        if (s.length() % 2 != 0) {
            return stringToIntArray(padLeftZeros(s, s.length() + 1));
        } else {
            return stringToIntArray(s);
        }
    }

    private static String getString(int[] left, int[] right) {
        int[] both = new int[left.length + right.length];

        for (int i=0; i < left.length; i++) {
            both[i] = left[i];
        }

        for (int i=0; i < right.length; i++) {
            both[i+left.length] = right[i];
        }
        return intArrayToString(both);
    }

    public static String encrypt(String plainText, String key) {
        int arr[], tmp[];

        if (plainText.length() % 2 != 0) {
            arr = stringToIntArray(padLeftZeros(plainText, plainText.length() + 1));
        } else {
            arr = stringToIntArray(plainText);
        }

        int[] left = Arrays.copyOfRange(arr, 0, (arr.length + 1)/2);
        int[] right = Arrays.copyOfRange(arr, (arr.length + 1)/2, arr.length);
        int keys[] = stringToIntArray(key);


        for(int r = 0; r < 10; r++) {
            for(int i = 0; i < right.length; i++){
                right[i] = left[i] ^ (right[i] ^ keys[r]);
            }
            tmp = left;
            left = right;
            right = tmp;
        }

        return getString(left, right);
    }

    public static String decrypt(String cipherText, String key) {
        int tmp[];
        int arr[] = getStringArray(cipherText);

        int[] left = Arrays.copyOfRange(arr, 0, (arr.length + 1)/2);
        int[] right = Arrays.copyOfRange(arr, (arr.length + 1)/2, arr.length);

        int keysRegular[] = stringToIntArray(key);
        int keys[] = IntStream.rangeClosed(1, keysRegular.length).map(i -> keysRegular[keysRegular.length-i]).toArray();

        for(int r = 0; r < 10; r++) {
            tmp = left;
            left = right;
            right = tmp;
            for(int i = 0; i < right.length; i++){
                right[i] = left[i] ^ (right[i] ^ keys[r]);
            }
        }

        return getString(left, right);
    }
}
