import java.util.*;

public class Main {
    static String encrypt(String msg, String key) {
        msg = msg.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            int c = (msg.charAt(i) - 'A' + key.charAt(i) - 'A') % 26;
            res.append((char) (c + 'A'));
        }
        return res.toString();
    }

    static String decrypt(String msg, String key) {
        msg = msg.toUpperCase().replaceAll("[^A-Z]", "");
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            int c = (msg.charAt(i) - key.charAt(i) + 26) % 26;
            res.append((char) (c + 'A'));
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String key = s.nextLine();
        String msg = s.nextLine();
        if (key.length() < msg.length()) {
            System.out.println("Key must be at least as long as message.");
            return;
        }
        String enc = encrypt(msg, key);
        System.out.println("Encrypted: " + enc);
        String dec = decrypt(enc, key);
        System.out.println("Decrypted: " + dec);
        s.close();
    }
}


//XMCKL
//HELLO
