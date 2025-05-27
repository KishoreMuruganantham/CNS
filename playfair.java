import java.util.*;

class Main {
    static char[][] generateMatrix(String key) {
        key = key.replaceAll("[^A-Za-z]", "").toUpperCase().replace("J", "I");
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (char c : key.toCharArray()) set.add(c);
        for (char c = 'A'; c <= 'Z'; c++) if (c != 'J') set.add(c);
        char[][] m = new char[5][5];
        Iterator<Character> it = set.iterator();
        for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++) m[i][j] = it.next();
        return m;
    }

    static int[] pos(char[][] m, char c) {
        for (int i = 0; i < 5; i++) for (int j = 0; j < 5; j++)
            if (m[i][j] == c) return new int[]{i, j};
        return null;
    }

    static String prepare(String msg) {
        msg = msg.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            char a = msg.charAt(i), b = (i + 1 < msg.length()) ? msg.charAt(i + 1) : 'X';
            sb.append(a);
            if (a == b) sb.append('X');
            else if (++i < msg.length()) sb.append(b);
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String key = s.nextLine();
        String msg = s.nextLine();
        char[][] m = generateMatrix(key);
        msg = prepare(msg);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < msg.length(); i += 2) {
            char a = msg.charAt(i), b = msg.charAt(i + 1);
            int[] p1 = pos(m, a), p2 = pos(m, b);
            if (p1[0] == p2[0])
                res.append(m[p1[0]][(p1[1] + 1) % 5]).append(m[p2[0]][(p2[1] + 1) % 5]);
            else if (p1[1] == p2[1])
                res.append(m[(p1[0] + 1) % 5][p1[1]]).append(m[(p2[0] + 1) % 5][p2[1]]);
            else
                res.append(m[p1[0]][p2[1]]).append(m[p2[0]][p1[1]]);
        }
        System.out.println(res);
        s.close();
    }
}
