package labs;

import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class LabArrays {
    public static void task1() {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        System.out.println("Initial array: " + Arrays.toString(arr));

        int countSymmetric = 0;
        int sumSymmetric = 0;
        for (int i = 0; i < arr.length; i++) {
            int el = arr[i];
            if (el / 100 > 0 && el / 100 == el % 10) {
                countSymmetric++;
                sumSymmetric += el;
            }
        }

        System.out.println("Amount of symmetric numbers: " + countSymmetric);
        System.out.println("Sum of symmetric numbers: " + sumSymmetric);
    }

    public static void task2() {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        System.out.println("Initial array: " + Arrays.toString(arr));
        int res = -1;
        for (int i : arr) {
            if (i % 2 == 0 && i > res) {
                res = i;
            }
        }
        if (res == -1) {
            System.out.println("No even number in array");
        } else {
            System.out.println("Max even number in array: " + res);
        }
    }

    public static void task3() {
        double[] minMaxStep = new double[3];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter min, max, step: ");
        int idx = 0;
        while (true) {
            if (sc.hasNextDouble()) {
                minMaxStep[idx] = sc.nextDouble();
                idx++;
            } else {
                System.out.println("Invalid input");
                sc.nextLine();
            }
            if (idx == 3) {
                break;
            }
        }
        sc.close();
        if (minMaxStep[0] > minMaxStep[1]) {
            System.out.println("Min must be less than max");
            return;
        }

        double min = minMaxStep[0], max = minMaxStep[1], step = minMaxStep[2];
        int argAmount = (int) ((max - min) / step + 1);
        double[][] arr = new double[4][argAmount];
        int arrIdx = 0;
        for (double i = min; i <= max; i += step) {
            arr[0][arrIdx] = i;
            arr[1][arrIdx] = fn1(i);
            arr[2][arrIdx] = fn2(i);
            arr[3][arrIdx] = fn3(i);
            arrIdx++;
        }

        for (int i = 0; i < argAmount; i++) {
            System.out.printf("X: %7.2f, F1: %7.2f, F2: %7.2f, F3: %7.2f\n", arr[0][i], arr[1][i], arr[2][i],
                    arr[3][i]);
        }
    }

    static double fn1(double x) {
        return Math.pow(x, 2) - 10 * x + 15;
    }

    static double fn2(double x) {
        return 2 * Math.sin(x + Math.PI / 3);
    }

    static double fn3(double x) {
        return Math.pow(Math.E, Math.sqrt(x));
    }

    public static void task4() {
        final int size = 20;
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }

        int[] tmp = new int[size];
        int newSize = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 10 == 3) {
                tmp[i] = arr[i];
                newSize++;
            }
        }

        reverseSort(tmp);
        int[] res = Arrays.copyOfRange(tmp, 0, newSize);
        System.out.println(Arrays.toString(res));
    }

    static void reverseSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = -array[i];
        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            array[i] = -array[i];
        }
    }

    public static void task5() {
        int[][] m = new int[8][8];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = (int) (Math.random() * 20 - 10);
            }
        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%5d", m[i][j]);
            }
            System.out.println();
        }

        int locMinCount = 0;
        for (int i = 0; i < m.length; i++) {
            int[] row = m[i];
            for (int j = 0; j < row.length; j++) {
                int el = row[j];
                boolean isLocMin = true;
                if (i - 1 >= 0 && el > m[i - 1][j])
                    isLocMin = false;
                if (i + 1 < m.length && el > m[i + 1][j])
                    isLocMin = false;
                if (j - 1 >= 0 && el > m[i][j - 1])
                    isLocMin = false;
                if (j + 1 < m[i].length && el > m[i][j + 1])
                    isLocMin = false;

                if (isLocMin) {
                    locMinCount++;
                    System.out.printf("localMin: matrix[%d][%d] = %d\n", i, j, el);
                }
            }
        }
        System.out.printf("Amount of local mins: %d", locMinCount);
    }

    public static void task6() {
        final int mil = 1_000_000;
        final int rolls = 1_000;
        String[] original = new String[mil];
        String[] copied = new String[mil];
        Arrays.fill(original, "Stroka");

        long start;
        long end;
        long totalTime = 0;
        for (int i = 0; i < rolls; i++) {
            start = System.currentTimeMillis();
            for (int j = 0; j < original.length; j++) {
                copied[j] = original[j];
            }
            end = System.currentTimeMillis();
            totalTime += end - start;
        }
        System.out.println(((double) totalTime / (double) rolls) + " hand copy");

        totalTime = 0;
        for (int i = 0; i < rolls; i++) {
            start = System.currentTimeMillis();
            Arrays.copyOf(original, mil);
            end = System.currentTimeMillis();
            totalTime += end - start;
        }
        System.out.println(((double) totalTime / (double) rolls) + " Arrays method");

        totalTime = 0;
        for (int i = 0; i < rolls; i++) {
            start = System.currentTimeMillis();
            System.arraycopy(original, 0, copied, 0, mil);
            end = System.currentTimeMillis();
            totalTime += end - start;
        }

        System.out.println(((double) totalTime / (double) rolls) + " System method");
    }

    static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public static void dealingCards() {
        int plrsCount = 0;
        System.out.println("Enter number of players: ");
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            System.err.println("Wrong number of players");
            sc.close();
            return;
        }
        plrsCount = sc.nextInt();
        sc.close();
        if (plrsCount < 2 || plrsCount > 10) {
            System.err.println("Wrong number of players");
            return;
        }
        Deck deck = new Deck();

        for (int i = 0; i < plrsCount; i++) {
            Card[] hand = new Card[5];
            for (int j = 0; j < hand.length; j++) {
                hand[j] = deck.dealCard();
            }
            System.out.println("Player " + (i + 1) + ": " + Arrays.toString(hand));
        }

        System.out.println("Deck: ");
        deck.prettyPrint();
    }
}
