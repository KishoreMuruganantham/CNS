import java.util.*;

public class Main {
    static String encrypt(String msg, int[] key) {
        msg = msg.replaceAll("\\s+", "").toUpperCase();
        int cols = key.length;
        int rows = (int) Math.ceil((double) msg.length() / cols);
        char[][] mat = new char[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                mat[i][j] = k < msg.length() ? msg.charAt(k++) : 'X';
        StringBuilder res = new StringBuilder();
        for (int i : key)
            for (int j = 0; j < rows; j++)
                res.append(mat[j][i]);
        return res.toString();
    }

    static String decrypt(String msg, int[] key) {
        int cols = key.length;
        int rows = (int) Math.ceil((double) msg.length() / cols);
        char[][] mat = new char[rows][cols];
        int k = 0;
        for (int i : key)
            for (int j = 0; j < rows; j++)
                mat[j][i] = msg.charAt(k++);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                res.append(mat[i][j]);
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] keyStr = s.nextLine().split(" ");
        int[] key = new int[keyStr.length];
        for (int i = 0; i < keyStr.length; i++) key[i] = Integer.parseInt(keyStr[i]);
        String msg = s.nextLine();
        String enc = encrypt(msg, key);
        System.out.println("Encrypted: " + enc);
        String dec = decrypt(enc, key);
        System.out.println("Decrypted: " + dec);
        s.close();
    }
}

//2 0 3 1
//HELLO WORLD
