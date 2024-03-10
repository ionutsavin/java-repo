public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java Main <n>. We need the size of the Wheel Graph");
            System.exit(1);
        }
        try {
            int check = Integer.parseInt(args[0]);
            if (check < 4) {
                System.err.println("The size of the Wheel Graph must be at least 4.");
                System.exit(1);
            } else {
                System.out.println(args[0] + " is a valid integer");
            }
        } catch (NumberFormatException e) {
            System.out.println(args[0] + " is not a valid integer");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        int[][] adjacencyMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (i == 0) {
                adjacencyMatrix[i][i + 1] = 1;
                adjacencyMatrix[i][n - 2] = 1;
                adjacencyMatrix[i][n - 1] = 1;
            } else if (i == n - 2) {
                adjacencyMatrix[i][0] = 1;
                adjacencyMatrix[i][i - 1] = 1;
                adjacencyMatrix[i][n - 1] = 1;
            } else {
                adjacencyMatrix[i][i + 1] = 1;
                adjacencyMatrix[i][i - 1] = 1;
                adjacencyMatrix[i][n - 1] = 1;
            }
        }

        for (int i = 0; i < n - 1; i++) {
            adjacencyMatrix[n - 1][i] = 1;
        }
        System.out.println("Adjacency Matrix of the Wheel Graph with n = " + n + ":");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
        int countCycles = 0;
        System.out.println("The cycles are: ");
        for (int i = 0; i < n - 1; i++) {
            StringBuilder cycle = new StringBuilder();
            cycle.append(i).append("->");
            for (int j = 0; j < n - 2; j++) {
                cycle.append(((i + j + 1) % (n - 1))).append("->");
                countCycles++;
                System.out.println("Cycle " + countCycles + " is: " + cycle + (n - 1) + "->" + i);
            }
        }
        countCycles++;
        System.out.print("Cycle " + countCycles + " is: ");
        for (int i = 0; i < n - 1; i++) {
            System.out.print(i + "->");
        }
        System.out.println(0);
        if (n * n - 3 * n + 3 == countCycles) {
            System.out.println("We have printed all the cycles!");
        } else {
            System.out.println("There are still cycles left to print");
        }
    }
}