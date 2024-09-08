import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        multTable();;;;;;;;;;;;;;;;;;
    }

    static String centerString(String str, int len) {
        int diff = len - str.length();
        int left = diff / 2;
        int right = (diff + 1) / 2;
        return len > str.length() ? " ".repeat(left) + str + " ".repeat(right) : str;
    }

    public static void ATMProblem() {
        final int[] notes = { 10_000, 1_000, 100, 50 };
        System.out.println("Enter amount: ");
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Please enter valid number");
            scanner.close();
            return;
        }

        int amount = scanner.nextInt();
        scanner.close();

        if (amount < 0 || amount < notes[notes.length - 1]) {
            System.out.println("Can't retrieve sum");
            return;
        }

        int[] result = new int[notes.length];
        int rem = 0;
        Arrays.fill(result, 0);

        int notesIndex = 0;
        while (true) {
            int times = amount / notes[notesIndex];
            if (times > 0) {
                result[notesIndex] += times;
                amount -= notes[notesIndex] * times;
            }
            notesIndex++;
            if (notesIndex >= notes.length) {
                rem = amount % notes[notesIndex - 1];
                break;
            }
        }
        for (int i = 0; i < result.length; i++) {
            System.out.println("note[" + notes[i] + "] = " + result[i]);
        }
        System.out.println("Remainder = " + rem);
    }

    static boolean isLuckyTicket(int number) {
        int rSum = 0;
        int lSum = 0;
        int[] digits = { 0, 0, 0, 0, 0, 0 };
        for (int i = 0; i < 6; i++) {
            digits[i] = number % 10;
            number /= 10;
        }
        for (int i = 0; i < 3; i++) {
            rSum += digits[i];
        }
        for (int i = 3; i < 6; i++) {
            lSum += digits[i];
        }
        return rSum == lSum;
    }

    public static void ticketProblem() {
        final int MAX_TICKET_VALUE = 999999;
        final int MIN_TICKET_VALUE = 0;
        System.out.println("Enter start number: ");
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.println("Please enter valid integer");
            scanner.close();
            return;
        }
        int start = scanner.nextInt();
        System.out.println("Enter end number: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Please enter valid integer");
            scanner.close();
            return;
        }

        int end = scanner.nextInt();
        scanner.close();
        int luckyTickets = 0;

        if (start > MAX_TICKET_VALUE || end > MAX_TICKET_VALUE || start < MIN_TICKET_VALUE
                || end < MIN_TICKET_VALUE) {
            System.out.println("Invalid input");
            return;
        }

        if (start < end) {
            for (int i = start; i <= end; i++) {
                luckyTickets += isLuckyTicket(i) ? 1 : 0;
            }
        } else {
            for (int i = start; i >= end; i--) {
                luckyTickets += isLuckyTicket(i) ? 1 : 0;
            }
        }
        System.out.println(luckyTickets);
    }

    public static void multTable() {
        System.out.println("Enter Number: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            scanner.close();

            final int maxLen = String.format("%d", number * number).length() + 1;
            final String FORMAT_STRING = String.format("|%%%dd", maxLen);
            System.out.printf(FORMAT_STRING, 0);
            for (int i = 1; i <= number; i++) {
                System.out.printf(FORMAT_STRING, i);
                if (i == number) {
                    System.out.print("|");
                }
            }
            System.out.println();
            for (int i = 1; i <= number; i++) {
                System.out.printf(FORMAT_STRING, i);
                for (int j = 1; j <= number; j++) {
                    System.out.printf(FORMAT_STRING, j * i);
                    if (j == number) {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
        }
    }

    public static void japCalendar() {
        final int STARTING_YEAR = 1996;
        final String[] cycle = {
                "Rat", "Cow", "Tiger", "Hare", "Dragon", "Snake", "Horse", "Sheep", "Monkey", "Chicken", "Dog", "Pig"
        };
        final String STOP_PATTERN = "stop";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter stop to exit program");
        System.out.println("Enter year: ");
        while (true) {
            if (scanner.hasNextInt()) {
                int year = scanner.nextInt();
                int cycleIndex = Math.abs(STARTING_YEAR - year) % 12;
                String res = cycle[cycleIndex];
                System.out.println("Year " + year + " is " + res);
            } else if (scanner.hasNext(STOP_PATTERN)) {
                break;
            } else {
                System.out.println("Invalid year");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}