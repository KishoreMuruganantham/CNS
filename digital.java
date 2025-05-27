import java.util.*;

public class Main {
    static int modPow(int b, int e, int m) {
        int r = 1;
        b %= m;
        while (e > 0) {
            if ((e & 1) == 1) r = (r * b) % m;
            b = (b * b) % m;
            e >>= 1;
        }
        return r;
    }

    static int modInverse(int a, int m) {
        for (int i = 1; i < m; i++)
            if ((a * i) % m == 1) return i;
        return -1;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int p = s.nextInt();
        int g = s.nextInt();
        int x = s.nextInt();
        int k = s.nextInt();
        int m = s.nextInt();
        int y = modPow(g, x, p);
        int r = modPow(g, k, p);
        int kinv = modInverse(k, p - 1);
        int s1 = ((m - x * r) % (p - 1) + (p - 1)) % (p - 1);
        int s2 = (kinv * s1) % (p - 1);
        int v1 = (modPow(y, r, p) * modPow(r, s2, p)) % p;
        int v2 = modPow(g, m, p);
        System.out.println(r + " " + s2);
        System.out.println(v1 == v2 ? "Verified" : "Not Verified");
        s.close();
    }
}

//23 5 6 7 9
