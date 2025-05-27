import java.util.*;

public class Main {
    static String encrypt(String msg, int rails) {
        msg = msg.replaceAll("\\s+", "");
        StringBuilder[] fence = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) fence[i] = new StringBuilder();
        int dir = 1, row = 0;
        for (char c : msg.toCharArray()) {
            fence[row].append(c);
            row += dir;
            if (row == 0 || row == rails - 1) dir *= -1;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : fence) res.append(sb);
        return res.toString();
    }

    static String decrypt(String msg, int rails) {
        int len = msg.length();
        boolean[][] mark = new boolean[rails][len];
        int dir = 1, row = 0;
        for (int i = 0; i < len; i++) {
            mark[row][i] = true;
            row += dir;
            if (row == 0 || row == rails - 1) dir *= -1;
        }
        char[][] rail = new char[rails][len];
        int idx = 0;
        for (int i = 0; i < rails; i++)
            for (int j = 0; j < len; j++)
                if (mark[i][j]) rail[i][j] = msg.charAt(idx++);
        StringBuilder res = new StringBuilder();
        row = 0; dir = 1;
        for (int i = 0; i < len; i++) {
            res.append(rail[row][i]);
            row += dir;
            if (row == 0 || row == rails - 1) dir *= -1;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int rails = Integer.parseInt(s.nextLine());
        String msg = s.nextLine();
        String enc = encrypt(msg, rails);
        System.out.println("Encrypted: " + enc);
        String dec = decrypt(enc, rails);
        System.out.println("Decrypted: " + dec);
        s.close();
    }
}


//3
//WE ARE DISCOVERED RUN
