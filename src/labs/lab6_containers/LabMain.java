package labs.lab6_containers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LabMain {
    final private static String SRC_LOCATION = "src/labs/lab6_containers/";

    public static void readRuFile(ArrayList<String> buf, String filename) {
        try {
            byte[] bytes = Files.readAllBytes(new File(filename).toPath());
            String str = new String(bytes, "UTF-8");
            String[] lines = str.split("\\r?\\n");
            for (String s : lines) {
                buf.add(s);
            }

        } catch (IOException e) {
            System.out.println("Error reading file");
            System.exit(1);
        }
    }

    public static void main() {
        ArrayList<String> buf = new ArrayList<>();
        readRuFile(buf, SRC_LOCATION + "data_school.txt");
        ArrayList<SchoolBoy> schoolBoys = new ArrayList<>();
        for (String s : buf) {
            try {
                ArrayList<String> line = new ArrayList<>(
                        List.of(s.split("\s")));
                String surname = line.get(0);
                String name = line.get(1);
                int grade = Integer.parseInt(line.get(2));
                String subject = line.get(3);
                int mark = Integer.parseInt(line.get(4));
                schoolBoys.add(
                        new SchoolBoy(name, surname, grade, subject, mark));
            } catch (IndexOutOfBoundsException e) {
                System.err.println("Failed to parse line:" + s);
            } catch (NumberFormatException e) {
                System.err.println("Failed to parse number in line:" + s);
            }
        }

        // hash: grade
        HashMap<Integer, MarkJournal> journals = new HashMap<>();
        for (SchoolBoy sb : schoolBoys) {
            int hash = sb.getGrade();
            if (journals.containsKey(hash)) {
                journals.get(hash).addSchoolBoy(sb);
            } else {
                MarkJournal journal = new MarkJournal(sb.getGrade());
                journal.addSchoolBoy(sb);
                journals.put(hash, journal);
            }
        }

        /*
         * Выведите список школьников каждого класса на консоль и в отдельные
         * файлы.
         */
        printAndSave(journals);

        /*
         * Для каждого класса выведите список учеников с заданной оценкой.
         */

        getByGrade(journals);

        /*
         * Отсортируйте классы по средней успеваемости.
         */
        sortByMark(journals);

        /*
         * Для заданного предмета выведите список из учеников всех классов,
         * отсортированный по фамилии.
         */
        subjectStudents(journals);

        /*
         * В файл выведите ведомости заданного класса по каждому предмету
         */
        saveJournals(journals);

        /*
         * По имени и фамилии определите класс, в котором учится ученик.
         */
        getStudentByNameSurname(journals);

        /*
         * Определите предмет с самой высокой средней успеваемостью.
         */
        getBestSubject(journals);
    }

    private static void printAndSave(HashMap<Integer, MarkJournal> journals) {
        for (int k : journals.keySet()) {
            MarkJournal journal = journals.get(k);
            File file = new File(SRC_LOCATION + "output/Grade" + k + ".txt");
            try (BufferedWriter wr = new BufferedWriter(
                    new FileWriter(file, StandardCharsets.UTF_8))) {
                StringBuilder res = new StringBuilder();
                for (SchoolBoy s : journal.getSchoolBoys()) {
                    res.append(s.getSurname() + " " + s.getName() + " "
                            + s.getSubject() + " " + s.getMark() + "\n");
                }
                wr.write(res.toString());

                System.out.println("Grade " + k + ":" + res.toString());
            } catch (IOException e) {
                System.err.println("Error writing file");
                continue;
            }
        }

    }

    private static void getByGrade(HashMap<Integer, MarkJournal> journals) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter mark: ");
        int targetMark;
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Wrong mark");
                sc.nextLine();
            } else {
                targetMark = sc.nextInt();
                if (targetMark < 1 || targetMark > 5) {
                    System.out.println("Wrong mark");
                    sc.nextLine();
                } else {
                    break;
                }
            }
        }
        sc.close();

        for (int k : journals.keySet()) {
            MarkJournal journal = journals.get(k);
            System.out.println("Grade " + k + ":");
            for (SchoolBoy s : journal.getSchoolBoys()) {
                if (s.getMark() == targetMark) {
                    System.out.println(s.getSurname() + " " + s.getName());
                }
            }
            System.out.println();
        }
    }

    private static void sortByMark(HashMap<Integer, MarkJournal> journals) {
        ArrayList<MarkJournal> journalsList = new ArrayList<>(
                journals.values());
        Collections.sort(journalsList, new MarkJournalAvgComparator());
        System.out.println("Sorted:");
        for (MarkJournal m : journalsList) {
            System.out.println(m);
        }
    }

    private static void subjectStudents(
            HashMap<Integer, MarkJournal> journals) {
        Scanner sc = new Scanner(System.in, "Cp866");
        System.out.println("Enter subject: ");
        String targetSubject = sc.nextLine();
        sc.close();
        ArrayList<SchoolBoy> schoolBoysBySubject = new ArrayList<>();
        for (int k : journals.keySet()) {
            MarkJournal journal = journals.get(k);
            System.out.println("Grade " + k + ":");
            for (SchoolBoy s : journal.getSchoolBoys()) {
                if (s.getSubject().equals(targetSubject)) {
                    schoolBoysBySubject.add(s);
                }
            }
        }
        Collections.sort(schoolBoysBySubject);
        for (SchoolBoy s : schoolBoysBySubject) {
            System.out.println(s);
        }
    }

    private static void saveJournals(HashMap<Integer, MarkJournal> journals) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter grade: ");
        int targetGrade;
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Wrong grade");
                sc.nextLine();
            } else {
                targetGrade = sc.nextInt();
                if (targetGrade < 5 || targetGrade > 11) {
                    System.out.println("Wrong grade");
                    sc.nextLine();
                } else {
                    break;
                }
            }
        }
        sc.close();
        Comparator<SchoolBoy> comparator = new SchoolBoySubjectComparator();
        ArrayList<SchoolBoy> schoolBoysByGrade = new ArrayList<>(
                journals.get(targetGrade).getSchoolBoys());
        Collections.sort(schoolBoysByGrade, comparator);
        File file = new File(
                SRC_LOCATION + "output/Journal" + targetGrade + ".txt");
        try (BufferedWriter wr = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            StringBuilder res = new StringBuilder();
            for (SchoolBoy s : schoolBoysByGrade) {
                res.append(s.getSurname() + " " + s.getName() + " "
                        + s.getSubject() + " " + s.getMark() + "\n");
            }
            wr.write(res.toString());
        } catch (IOException e) {
            System.err.println("Error writing file");
            return;
        }
    }

    private static void getStudentByNameSurname(
            HashMap<Integer, MarkJournal> journals) {

        Scanner sc = new Scanner(System.in, "Cp866");
        System.out.println("Enter name: ");
        String targetName = sc.nextLine();
        System.out.println("Enter surname: ");
        String targetSurname = sc.nextLine();
        sc.close();
        for (int k : journals.keySet()) {
            MarkJournal journal = journals.get(k);
            for (SchoolBoy s : journal.getSchoolBoys()) {
                if (s.getName().toLowerCase().equals(targetName.toLowerCase())
                        && s.getSurname().toLowerCase()
                                .equals(targetSurname.toLowerCase())) {
                    System.out.println(s);
                }
            }
        }
    }

    private static void getBestSubject(HashMap<Integer, MarkJournal> journals) {
        HashMap<String, Integer[]> bestSubject = new HashMap<>();
        for (int k : journals.keySet()) {
            MarkJournal journal = journals.get(k);
            for (SchoolBoy s : journal.getSchoolBoys()) {
                if (bestSubject.containsKey(s.getSubject())) {
                    Integer[] arr = bestSubject.get(s.getSubject());
                    arr[0] += s.getMark();
                    arr[1]++;
                } else {
                    bestSubject.put(s.getSubject(),
                            new Integer[] { s.getMark(), 1 });
                }
            }
        }
        HashMap<String, Double> res = new HashMap<>();
        for (String key : bestSubject.keySet()) {
            Integer[] arr = bestSubject.get(key);
            res.put(key, (double) arr[0] / (double) arr[1]);
        }

        double max = 0;
        String maxKey = null;
        for (String key : res.keySet()) {
            if (res.get(key) > max) {
                max = res.get(key);
                maxKey = key;
            }
        }
        System.out.println(
                "Best subject: " + maxKey + '\n' + "Average mark: " + max);
    }

}
