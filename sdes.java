import java.util.*;

public class Main {
    static int[] P10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6};
    static int[] P8 = {6, 3, 7, 4, 8, 5, 10, 9};
    static int[] IP = {2, 6, 3, 1, 4, 8, 5, 7};
    static int[] IP_INV = {4, 1, 3, 5, 7, 2, 8, 6};
    static int[] EP = {4, 1, 2, 3, 2, 3, 4, 1};
    static int[] P4 = {2, 4, 3, 1};
    static int[][] S0 = {
        {1, 0, 3, 2},
        {3, 2, 1, 0},
        {0, 2, 1, 3},
        {3, 1, 3, 2}};
    static int[][] S1 = {
        {0, 1, 2, 3},
        {2, 0, 1, 3},
        {3, 0, 1, 0},
        {2, 1, 0, 3}};

    static int[] permute(int[] input, int[] p) {
        int[] out = new int[p.length];
        for (int i = 0; i < p.length; i++) out[i] = input[p[i] - 1];
        return out;
    }

    static int[] leftShift(int[] bits, int n) {
        int[] out = new int[bits.length];
        for (int i = 0; i < bits.length; i++) out[i] = bits[(i + n) % bits.length];
        return out;
    }

    static int[] xor(int[] a, int[] b) {
        int[] out = new int[a.length];
        for (int i = 0; i < a.length; i++) out[i] = a[i] ^ b[i];
        return out;
    }

    static int[] sbox(int[] bits, int[][] s) {
        int row = (bits[0] << 1) | bits[3];
        int col = (bits[1] << 1) | bits[2];
        int val = s[row][col];
        return new int[]{(val >> 1) & 1, val & 1};
    }

    static int[] f(int[] right, int[] key) {
        int[] ep = permute(right, EP);
        int[] xorResult = xor(ep, key);
        int[] left = Arrays.copyOfRange(xorResult, 0, 4);
        int[] rightPart = Arrays.copyOfRange(xorResult, 4, 8);
        int[] s0out = sbox(left, S0);
        int[] s1out = sbox(rightPart, S1);
        int[] combined = {s0out[0], s0out[1], s1out[0], s1out[1]};
        return permute(combined, P4);
    }

    static int[] fk(int[] bits, int[] key) {
        int[] left = Arrays.copyOfRange(bits, 0, 4);
        int[] right = Arrays.copyOfRange(bits, 4, 8);
        int[] fOut = f(right, key);
        int[] leftXor = xor(left, fOut);
        int[] result = new int[8];
        System.arraycopy(leftXor, 0, result, 0, 4);
        System.arraycopy(right, 0, result, 4, 4);
        return result;
    }

    static int[] swapHalves(int[] bits) {
        int[] out = new int[8];
        System.arraycopy(bits, 4, out, 0, 4);
        System.arraycopy(bits, 0, out, 4, 4);
        return out;
    }

    static int[] generateKey(int[] key10, int shift) {
        int[] left = Arrays.copyOfRange(key10, 0, 5);
        int[] right = Arrays.copyOfRange(key10, 5, 10);
        left = leftShift(left, shift);
        right = leftShift(right, shift);
        int[] combined = new int[10];
        System.arraycopy(left, 0, combined, 0, 5);
        System.arraycopy(right, 0, combined, 5, 5);
        return permute(combined, P8);
    }

    static int[] encrypt(int[] pt, int[] k1, int[] k2) {
        int[] ip = permute(pt, IP);
        int[] fk1 = fk(ip, k1);
        int[] sw = swapHalves(fk1);
        int[] fk2 = fk(sw, k2);
        return permute(fk2, IP_INV);
    }

    static int[] decrypt(int[] ct, int[] k1, int[] k2) {
        return encrypt(ct, k2, k1);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String keyStr = s.nextLine(), ptStr = s.nextLine();
        int[] key10 = keyStr.chars().map(c -> c - '0').toArray();
        int[] pt = ptStr.chars().map(c -> c - '0').toArray();
        int[] k1 = generateKey(permute(key10, P10), 1);
        int[] k2 = generateKey(permute(key10, P10), 3);
        int[] enc = encrypt(pt, k1, k2);
        int[] dec = decrypt(enc, k1, k2);
        System.out.print("Encrypted: ");
        for (int b : enc) System.out.print(b);
        System.out.print("\nDecrypted: ");
        for (int b : dec) System.out.print(b);
        s.close();
    }
}

//1010000010
//11010111
