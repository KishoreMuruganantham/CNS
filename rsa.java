import java.util.*;

public class Main {
    static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    static int modInverse(int e, int phi) {
        for (int d = 1; d < phi; d++)
            if ((e * d) % phi == 1)
                return d;
        return -1;
    }

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
        int p = s.nextInt(), q = s.nextInt();
        int n = p * q, phi = (p - 1) * (q - 1), e = 2;
        while (e < phi && gcd(e, phi) != 1) e++;
        int d = modInverse(e, phi);
        int msg = s.nextInt();
        int enc = modPow(msg, e, n);
        int dec = modPow(enc, d, n);
        System.out.println("Public Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");
        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
        s.close();
    }
}

//7 11
//9
