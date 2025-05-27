import java.util.*;

public class Main {
    static int[][] getKeyMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "");
        int[][] k = new int[2][2];
        for (int i = 0; i < 4; i++) k[i / 2][i % 2] = key.charAt(i) - 'A';
        return k;
    }

    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) if ((a * x) % m == 1) return x;
        return -1;
    }

    static int[][] inverseKey(int[][] k) {
        int a = k[0][0], b = k[0][1], c = k[1][0], d = k[1][1];
        int det = (a * d - b * c) % 26;
        if (det < 0) det += 26;
        int invDet = modInverse(det, 26);
        int[][] inv = {
            {(d * invDet) % 26, (-b * invDet) % 26},
            {(-c * invDet) % 26, (a * invDet) % 26}
        };
        for (int i = 0; i < 2; i++) for (int j = 0; j < 2; j++) {
            if (inv[i][j] < 0) inv[i][j] += 26;
        }
        return inv;
    }

    static String process(String msg, int[][] k) {
        msg = msg.toUpperCase().replaceAll("[^A-Z]", "");
        if (msg.length() % 2 != 0) msg += "X";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msg.length(); i += 2) {
            int[] v = {msg.charAt(i) - 'A', msg.charAt(i + 1) - 'A'};
            for (int r = 0; r < 2; r++) {
                int val = k[r][0] * v[0] + k[r][1] * v[1];
                res.append((char) ('A' + (val % 26)));
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String key = s.nextLine();
        String msg = s.nextLine();
        int[][] k = getKeyMatrix(key);
        String enc = process(msg, k);
        System.out.println("Encrypted: " + enc);
        int[][] inv = inverseKey(k);
        String dec = process(enc, inv);
        System.out.println("Decrypted: " + dec);
        s.close();
    }
}

//GYBN
//HELP
