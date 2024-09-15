package labs.lab4_books;

import java.util.ArrayList;
import java.util.Arrays;

public class BookWorms {
    public static void main(final String[] args) {
        final ArrayList<Book> booksInLibrary = new ArrayList<>(Arrays.asList(
                new Book("Java 8 full course", "G. Shield", 1, 4),
                new Book("Kreutzer Sonata", "Leo Tolstoy", 25, 3)));

        BookReader reader = new BookReader("Peter",
                booksInLibrary.get(0));
        BookReader reader2 = new BookReader("Vasiliy",
                booksInLibrary.get(1));

        final int handoverDay = 29;
        final int handoverMonth = 3;

        final String name = reader.getName();
        final String name2 = reader2.getName();

        if (reader.isLate(reader.getBooks().get(0), handoverDay, handoverMonth)) {
            System.out.println(name + " is late.");
        } else {
            System.out.println(name + " handed over the book on time.");
        }
        reader.handoverBook(reader.getBooks().get(0));

        if (reader2.isLate(reader2.getBooks().get(0), handoverDay, handoverMonth)) {
            System.out.println(name2 + " is late.");
        } else {
            System.out.println(name2 + " handed over the book on time.");
        }
        reader2.handoverBook(reader2.getBooks().get(0));
    }

}
