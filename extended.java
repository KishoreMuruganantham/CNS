import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt(), m = s.nextInt();
        int m0 = m, x0 = 0, x1 = 1;
        while (a > 1) {
            int q = a / m, t = m;
            m = a % m; a = t;
            t = x0; x0 = x1 - q * x0; x1 = t;
        }
        if (x1 < 0) x1 += m0;
        System.out.println(x1);
        s.close();
    }
}
