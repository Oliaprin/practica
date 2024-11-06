package task7_8_9;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// Задача 1: Рисование бабочки
class ButterflyDrawing extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] wingColors = {Color.RED, Color.ORANGE};
        g2d.setColor(Color.BLACK);
        g2d.fillRect(175, 175, 20, 100);
        Shape leftTopWing = createWingShape(140, 175, -1, -1);
        g2d.setColor(wingColors[0]);
        g2d.fill(leftTopWing);
        Shape rightTopWing = createWingShape(220, 175, 1, -1);
        g2d.setColor(wingColors[0]);
        g2d.fill(rightTopWing);
        Shape leftBottomWing = createWingShape(140, 275, -1, 1);
        g2d.setColor(wingColors[1]);
        g2d.fill(leftBottomWing);
        Shape rightBottomWing = createWingShape(220, 275, 1, 1);
        g2d.setColor(wingColors[1]);
        g2d.fill(rightBottomWing);
        g2d.drawLine(185, 175, 155, 125);
        g2d.drawLine(185, 175, 215, 125);
    }

    private Shape createWingShape(int x, int y, int xDirection, int yDirection) {
        GeneralPath wing = new GeneralPath();
        wing.moveTo(x, y);
        wing.quadTo(x + 30 * xDirection, y - 50 * yDirection, x + 60 * xDirection, y);
        wing.quadTo(x + 30 * xDirection, y + 50 * yDirection, x, y + 100 * yDirection);
        wing.closePath();
        return wing;
    }
}

// Задача 2: Матрица
class TMatr {
    private int[][] matrix;
    private int rows;
    private int cols;

    public TMatr(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new int[rows][cols];
    }

    public void resize(int newRows, int newCols) {
        int[][] newMatrix = new int[newRows][newCols];
        for (int i = 0; i < Math.min(rows, newRows); i++) {
            for (int j = 0; j < Math.min(cols, newCols); j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }
        this.rows = newRows;
        this.cols = newCols;
        this.matrix = newMatrix;
    }

    public void set(int row, int col, int value) {
        matrix[row][col] = value;
    }

    public void printMatrix() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int value : row) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Матрица", JOptionPane.INFORMATION_MESSAGE);
    }

    public void fillMatrix() {
        int value = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                set(i, j, value++);
            }
        }
    }
}

// Задача 3: Игра с картами
class MathGame {
    private List<Card> cards;
    private int[][] board;

    public MathGame() {
        cards = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                cards.add(new Card(i));
            }
        }
        Collections.shuffle(cards);
        board = new int[5][5];
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        int filledCells = 0;
        while (filledCells < 25) {
            if (cards.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Больше нет карт!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            Card drawnCard = cards.remove(cards.size() - 1);
            String input = JOptionPane.showInputDialog("Вытащенная карта: " + drawnCard + "\nВведите строку (0-4) и столбец (0-4):");
            String[] inputs = input.split(" ");
            int row = Integer.parseInt(inputs[0]);
            int col = Integer.parseInt(inputs[1]);

            if (row < 0 || row >= 5 || col < 0 || col >= 5 || board[row][col] != 0) {
                JOptionPane.showMessageDialog(null, "Неверная позиция или ячейка уже заполнена. Попробуйте снова.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            board[row][col] = drawnCard.getNumber();
            filledCells++;
            printBoard();
        }
        JOptionPane.showMessageDialog(null, "Игра окончена!", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
        printBoard();
    }

    private void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Игровая доска", JOptionPane.INFORMATION_MESSAGE);
    }
}

// Класс карты
class Card {
    private final int number;

    public Card(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}

// Основной класс с меню
public class MainApp {
    public static void main(String[] args) {
        while (true) {
            String[] options = {"Рисование муравья", "Задание 8", "Игра с картами", "Выход"};
            int choice = JOptionPane.showOptionDialog(null, "Выберите задачу:", "Меню", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 3 || choice == -1) {
                break; // Выход из приложения
            }

            switch (choice) {
                case 0:
                    JFrame frame = new JFrame("Butterfly Drawing");
                    ButterflyDrawing panel = new ButterflyDrawing();
                    frame.add(panel);
                    frame.setSize(400, 400);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    break;
                case 1:
                    TMatr matrix = new TMatr(3, 3);
                    matrix.fillMatrix();
                    matrix.printMatrix();
                    matrix.resize(4, 4);
                    matrix.printMatrix();
                    break;
                case 2:
                    MathGame game = new MathGame();
                    game.playGame();
                    break;
            }
        }
    }
}
