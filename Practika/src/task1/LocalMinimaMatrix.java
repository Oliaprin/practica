package task1;

import java.util.Random;
import java.util.Scanner;

public class LocalMinimaMatrix {

    private static void fillMatrixWithRandomValues(int[][] matrix, int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextInt(2 * n + 1) - n;
            }
        }
    }

    private static boolean isLocalMinimum(int[][] matrix, int row, int col, int n) {
        int current = matrix[row][col];

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < n && j >= 0 && j < n && (i != row || j != col)) {
                    if (matrix[i][j] <= current) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static int countLocalMinima(int[][] matrix, int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isLocalMinimum(matrix, i, j, n)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размерность матрицы n: ");
        int n = scanner.nextInt();

        int[][] matrix = new int[n][n];

        fillMatrixWithRandomValues(matrix, n);

        System.out.println("Сгенерированная матрица:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%4d", matrix[i][j]);
            }
            System.out.println();
        }

        int localMinimaCount = countLocalMinima(matrix, n);
        System.out.println("Количество локальных минимумов: " + localMinimaCount);

        scanner.close();
    }
}
