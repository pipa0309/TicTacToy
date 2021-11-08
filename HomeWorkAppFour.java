//forCheck
package HomeTaskFour;

import java.util.Random;
import java.util.Scanner;

public class HomeWorkAppFour {

    private static final int sizeOfFieldX = 9;
    private static final int sizeOfFieldY = 9;

    public static char[][] field;

    private static final char DOT_X = 'X';
    private static final char DOT_0 = '0';
    private static final char DOT_EMPTY = '.';

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победа игрока!");
                break;
            }
            if (checkDraw()) {
                System.out.println("Ничья");
                break;
            }

            computerTurn();
            printMap();
            if (checkWin(DOT_0)) {
                System.out.println("Победа компьютера!");
                break;
            }
            if (checkDraw()) {
                System.out.println("Ничья");
                break;
            }
        }
    }


    public static void initMap() {
        field = new char[sizeOfFieldY][sizeOfFieldX];
        for (int i = 0; i < sizeOfFieldY; i++) {
            for (int j = 0; j < sizeOfFieldX; j++) {
                field[j][i] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("*");
        for (int i = 0; i < sizeOfFieldY * 2 + 1; i++) {
            System.out.print((i % 2 == 0) ? " " : i / 2 + 1);
        }
        System.out.println();
        for (int i = 0; i < sizeOfFieldY; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < sizeOfFieldY; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты 'x' и 'y': ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

        } while (!checkValidTurn(x, y) && !checkValidEmptyPlace(x, y));
        field[y][x] = DOT_X;
    }

    public static void computerTurn() {
        int x, y;
        do {
            x = random.nextInt(sizeOfFieldX);
            y = random.nextInt(sizeOfFieldY);

        } while (!checkValidEmptyPlace(x, y));
        field[y][x] = DOT_0;
    }

    public static boolean checkValidTurn(int x, int y) {
        return x > 0 && y > 0 && x < sizeOfFieldX + 1 && y < sizeOfFieldY + 1;
    }

    public static boolean checkValidEmptyPlace(int x, int y) {
        return field[y][x] == DOT_EMPTY;
    }

    public static boolean checkDraw() {
        for (int i = 0; i < sizeOfFieldY; i++) {
            for (int j = 0; j < sizeOfFieldX; j++) {
                if (field[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean checkWin(char symbol) {
        for (int i = 0; i < sizeOfFieldY; i++) {
            int count1 = 0;
            for (int j = 0; j < sizeOfFieldY; j++) {
                if (field[i][j] == symbol) {
                    count1++;
                }
                if (count1 == sizeOfFieldY) return true;
            }
        }

        for (int i = 0; i < sizeOfFieldY; i++) {
            int count2 = 0;
            for (int j = 0; j < sizeOfFieldY; j++) {
                if (field[j][i] == symbol) {
                    count2++;
                }
                if (count2 == sizeOfFieldY) return true;
            }
        }

        int count3 = 0;
        for (int i = 0; i < sizeOfFieldY; i++) {
            for (int j = 0; j < sizeOfFieldY; j++) {
                if (i == j && field[j][i] == symbol) {
                    count3++;
                }
                if (count3 == sizeOfFieldY) return true;
            }
        }

        int count4 = 0;
        for (int i = 0; i < sizeOfFieldY; i++) {
            for (int j = sizeOfFieldX - 1; j >= 0 ; j--) {
                if (((i + j) == (sizeOfFieldY - 1)) && (field[i][j] == symbol)) {
                    count4++;
                }
                if (count4 == sizeOfFieldY) return true;
            }
        }
        return false;
    }
}
