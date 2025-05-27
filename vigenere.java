import java.util.*;

public class Main {
    static String formatKey(String msg, String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) res.append(key.charAt(i % key.length()));
        return res.toString();
    }

    static String encrypt(String msg, String key) {
        msg = msg.toUpperCase().replaceAll("[^A-Z]", "");
        key = formatKey(msg, key);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msg.length(); i++)
            res.append((char) ((msg.charAt(i) + key.charAt(i) - 2 * 'A') % 26 + 'A'));
        return res.toString();
    }

    static String decrypt(String msg, String key) {
        msg = msg.toUpperCase().replaceAll("[^A-Z]", "");
        key = formatKey(msg, key);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msg.length(); i++)
            res.append((char) ((msg.charAt(i) - key.charAt(i) + 26) % 26 + 'A'));
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String key = s.nextLine();
        String msg = s.nextLine();
        String enc = encrypt(msg, key);
        System.out.println("Encrypted: " + enc);
        String dec = decrypt(enc, key);
        System.out.println("Decrypted: " + dec);
        s.close();
    }
}
