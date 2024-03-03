import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        if (args.length < 3) {
            System.err.println("Usage: java Main <a> <b> <k>. We need at least 3 arguments");
            System.exit(1);
        }
        try {
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int k = Integer.parseInt(args[2]);

            if (a > b) {
                System.err.println("Error: The value of a should be less than or equal to b.");
                System.exit(1);
            }

            StringBuilder numbers = getStringBuilder(a, b, k);
            System.out.println("The identified numbers are: " + numbers);
            long endTime = System.nanoTime();
            long executionTime = endTime - startTime;
            System.out.println("The application ran for " + executionTime + " nanoseconds");
        } catch (NumberFormatException e) {
            System.err.println("Error: Arguments must be integers.");
            System.exit(1);
        }
    }

    private static StringBuilder getStringBuilder(int a, int b, int k) {
        StringBuilder numbers = new StringBuilder();
        for (int i = a; i <= b; i++) {
            if (isKReducible(i, k)) {
                numbers.append(i).append(" ");
            }
        }
        return numbers;
    }

    private static boolean isKReducible(int number, int k) {
        Set<Integer> seenNumbers = new HashSet<>();
        while (number != k) {
            if (seenNumbers.contains(number)) {
                return false; // Detected a cycle
            }
            seenNumbers.add(number);
            number = squareAndSumDigits(number);
        }
        return true;
    }

    private static int squareAndSumDigits(int number) {
        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }
}