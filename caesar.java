import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String text = s.nextLine();
        int key = s.nextInt();
        StringBuilder res = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c))
                res.append((char)('A' + (c - 'A' + key) % 26));
            else if (Character.isLowerCase(c))
                res.append((char)('a' + (c - 'a' + key) % 26));
            else
                res.append(c);
        }
        System.out.println(res);
        s.close();
    }
}
