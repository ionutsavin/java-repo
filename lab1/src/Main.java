//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Hello world!");
        String[] languages = new String[]{"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n *= 3;
        n += 0b10101;
        n += 0xFF;
        n *= 6;
        System.out.println("Result: " + n);
        int result = 0;
        while(n > 0)
        {
            //System.out.println("Digits: " + n % 10);
            result += n % 10;
            n /= 10;
            if(n == 0 && result > 9)
            {
                n = result;
                result = 0;
            }
        }
        System.out.println("Sum: " + result);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }
}