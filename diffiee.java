import java.util.*;

public class Main {
    static int modPow(int base, int exp, int mod) {
        int res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int p = s.nextInt();
        int g = s.nextInt();
        int a = s.nextInt();
        int b = s.nextInt();
        int A = modPow(g, a, p);
        int B = modPow(g, b, p);
        int keyA = modPow(B, a, p);
        int keyB = modPow(A, b, p);
        System.out.println(keyA + " " + keyB);
        s.close();
    }
}

//23 5 6 15
