import java.util.Scanner;
import java.util.Random;

class Main {
    static boolean isPrime(int n, int k) {
        if (n <= 1 || n == 4) return false;
        if (n <= 3) return true;
        int d = n - 1;
        while (d % 2 == 0) d /= 2;
        for (int i = 0; i < k; i++)
            if (!test(d, n)) return false;
        return true;
    }

    static boolean test(int d, int n) {
        Random r = new Random();
        int a = 2 + r.nextInt(n - 4);
        int x = powMod(a, d, n);
        if (x == 1 || x == n - 1) return true;
        while (d != n - 1) {
            x = (int)((1L * x * x) % n);
            d *= 2;
            if (x == 1) return false;
            if (x == n - 1) return true;
        }
        return false;
    }

    static int powMod(int base, int exp, int mod) {
        int res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                res = (int)((1L * res * base) % mod);
            base = (int)((1L * base * base) % mod);
            exp >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        System.out.println(isPrime(n, 5) ? "Prime" : "Not Prime");
        s.close();
    }
}

