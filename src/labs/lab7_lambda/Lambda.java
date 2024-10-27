package labs.lab7_lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalDouble;

public class Lambda {
        public static void main() {
                Integer[] temps = { -2, -5, -2, -4, 3, -6, -2, -1, 5, 1, 1, 0,
                                -1, 0, 3, -1, 2, 5, 2, 4, 4, 0, 6, 1, 4, 6, -1,
                                2, 4, 7, 11 };
                ArrayList<Integer> temperatureList = new ArrayList<>();
                temperatureList.addAll(Arrays.asList(temps));

                int daysWithNegT = temperatureList.stream().reduce(0,
                                (a, b) -> a + (b < 0 ? 1 : 0));
                System.out.println("days with negative temp: " + daysWithNegT);

                boolean isMore = temperatureList.stream()
                                .anyMatch((a) -> a > 10);
                System.out.println("isMOre " + isMore);

                int maxWeekTemp = temperatureList.subList(0, 7).stream()
                                .max(Comparator.naturalOrder()).orElse(0);
                System.out.println("Max Week temp: " + maxWeekTemp);

                OptionalDouble avgTempOpt = temperatureList.stream()
                                .mapToDouble(Integer::doubleValue).average();
                System.out.println("Average temp: " + (avgTempOpt.isPresent()
                                ? avgTempOpt.getAsDouble()
                                : "No data"));

                String sentence = """
                                        They used 233 features including 227
                                        stylometric features and six novel social network-specific features
                                        like character-based ones numbers of alphabets, uppercase
                                        characters, special characters, word-based ones the total number of
                                        words, average word length, the number of words with 1 char,
                                        syntactic ones numbers of punctuation marks and functional
                                        words, the total number of sentences and many others
                                """;

                ArrayList<String> arr = new ArrayList<>();
                arr.addAll(Arrays.asList(sentence.split("\\s")));
                arr.removeIf((a) -> a.isEmpty() || a.isBlank());

                int ESWords = arr.stream()
                                .map(word -> word.endsWith("es") ? 1 : 0)
                                .reduce(0, (a, b) -> a + b);
                System.out.println("Word ending with 'es': " + ESWords);

                ArrayList<String> sortedByLen = new ArrayList<>(arr);
                sortedByLen.sort(new StringSortByLength());
                System.out.println("Sorted by length:\n" + sortedByLen);

                int sum = arr.stream().map((str) -> {
                        try {
                                return Integer.valueOf(str);
                        } catch (NumberFormatException e) {
                                return Integer.valueOf(0);
                        }
                }).reduce(0, (acc, el) -> acc + el);
                System.out.println("Sum of integers: " + sum);
        }
}

/**
 * StringSortByLength
 */
class StringSortByLength implements Comparator<String> {
        public int compare(String o1, String o2) {
                if (o1.length() > o2.length()) {
                        return 1;
                } else if (o1.length() < o2.length()) {
                        return -1;
                } else
                        return 0;
        }

}