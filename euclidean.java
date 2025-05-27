import java.util.Scanner;
class Euclidean {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt(), b = s.nextInt();
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        System.out.println(a);
        s.close();
    }
}
