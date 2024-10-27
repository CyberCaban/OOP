package labs.lab4strings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stroki {

    public static void main() {
        telegram();
    }

    static void deleteUpperCase() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string: ");
        String str = sc.nextLine().trim();
        sc.close();
        System.out.println(str.replaceAll("[A-Z]", ""));
    }

    static void decypher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string: ");
        String str = sc.nextLine().trim();
        sc.close();
        String arr[] = str.split(" ");
        for (String s : arr) {
            System.out.println(new StringBuilder(s).reverse().toString());
        }
    }

    static void threeCharsLong() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string: ");
        String str = sc.nextLine().trim();
        sc.close();
        String arr[] = str.split("[ ,]");
        int c = 0;
        for (String s : arr) {
            if (s.length() == 3) {
                c++;
            }
        }
        System.out.println(c);
    }

    static void goodPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter password: ");
        String str = sc.nextLine().trim();
        sc.close();
        if (str.length() < 8 && str.length() > 12) {
            System.out.println("password length must be between 8 and 12");
            return;
        }
        if (!str.matches(".*[0-9].*")) {
            System.out.println("password must contain number");
            return;
        }
        if (!str.matches(".*[a-z].*")) {
            System.out.println("password must contain lowercase letters");
            return;
        }
        if (!str.matches(".*[A-Z].*")) {
            System.out.println("password must contain uppercase letters");
            return;
        }
        if (!str.matches(".*[#@$%^&*<>].*")) {
            System.out.println("password must contain special character");
            return;
        }

        System.out.println("Good password");
    }

    static void integersInText() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter string: ");
        String str = sc.nextLine().trim();
        sc.close();
        int sum = 0;
        str = str.replaceAll("[^0-9]", " ");
        String arr[] = str.split(" ");
        for (String s : arr) {
            try {
                sum += Integer.parseInt(s);
            } catch (NumberFormatException e) {
                continue;
            }
        }
        System.out.println(sum);
    }

    static void grep() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter from console(C) or file(F): ");
        String str = sc.nextLine().trim();
        if (str.equals("C")) {
            grepC();
        } else if (str.equals("F")) {
            grepF();
        } else {
            System.out.println("Wrong input");
        }
        sc.close();
    }

    static boolean readFile(ArrayList<String> buf, String filename) {
        try (BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                buf.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        } catch (IOException e) {
            System.out.println("Error reading file");
            return false;
        }
        return true;
    }

    static void grepC() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter sentences: ");
        ArrayList<String> arr = new ArrayList<>();
        while (sc.hasNextLine()) {
            String str = sc.nextLine().trim();
            if (!str.isEmpty()) {
                arr.add(str);
            } else if (!str.matches(".*[\\.!\\?]$")) {
                System.out.println("Sentence must end with ., ! or ?");
                break;
            } else {
                break;
            }
        }
        /*
         * Welcome to SoftUni!
         * You will learn programming, algorithms, problem solving and software
         * technologies.
         * You need to allocate for study 20-30 hours weekly.
         * Good luck!
         * I am fan of Motorhead.
         * To be or not to be — that is the question.
         * TO DO OR NOT?
         * 
         */
        System.out.println("Enter word to find: ");
        String word = sc.nextLine().trim();
        sc.close();
        for (String s : arr) {
            if (s.matches(".*\s" + word + "\s.*")) {
                System.out.println(s);
            }
        }
    }

    static void grepF() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file path: ");
        System.out.println("Current directory: ");
        System.out.println(System.getProperty("user.dir"));
        String path = sc.nextLine().trim();
        System.out.println("Enter word to find: ");
        String word = sc.nextLine().trim();
        sc.close();
        ArrayList<String> buf = new ArrayList<>();
        if (!readFile(buf, path)) {
            System.out.println("Failed to read the file");
            return;
        }
        for (String s : buf) {
            if (s.matches(".*\s" + word + "\s.*")) {
                System.out.println(s);
            }
        }
    }

    static void telegram() {
        final int WORD_PRICE = 10;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter from console(C) or file(F): ");
        String str = sc.nextLine().trim();
        ArrayList<String> buf;
        if (str.equals("C")) {
            buf = telegramC();
        } else if (str.equals("F")) {
            buf = telegramF();
        } else {
            System.out.println("Wrong input");
            sc.close();
            return;
        }
        sc.close();
        if (buf == null) {
            return;
        }
        ArrayList<String> telegram = new ArrayList<>();
        int count = 0;
        for (String s : buf) {
            String[] arr = s.split(" ");
            for (String a : arr) {
                if (!a.isEmpty() && a.length() > 2) {
                    count++;
                    telegram.add(a.replaceAll(",", "zpt"));
                }
                count += a.split(",", -1).length - 1;
            }
        }
        System.err.println("Total price: " + count * WORD_PRICE + "\nTelegram:\n");
        for (String s : telegram) {
            System.out.printf("%s ", s);
        }
    }

    static ArrayList<String> telegramF() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter file path: ");
        String path = sc.nextLine().trim();
        ArrayList<String> buf = new ArrayList<>();
        if (!readFile(buf, path)) {
            System.out.println("Failed to read the file");
            sc.close();
            return null;
        }
        sc.close();
        return buf;
    }

    static ArrayList<String> telegramC() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter telegram: ");
        ArrayList<String> buf = new ArrayList<>();

        while (sc.hasNextLine()) {
            String s = sc.nextLine().trim();
            if (!s.isEmpty()) {
                buf.add(s);
            } else {
                break;
            }
        }
        sc.close();
        return buf;
    }
}
