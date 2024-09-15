package labs.lab4_books;

import java.util.ArrayList;

public class BookReader {
    private String name;
    private ArrayList<Book> books;

    public BookReader() {
        this.name = "";
        this.books = new ArrayList<>();
    }

    public BookReader(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public BookReader(String name, ArrayList<Book> books) {
        this.name = name;
        this.books = books;
    }

    public BookReader(BookReader bookReader) {
        this.name = bookReader.name;
        this.books = bookReader.books;
    }

    public BookReader(String name, Book... books) {
        this.name = name;
        this.books = new ArrayList<>();
        for (Book book : books) {
            this.books.add(book);
        }
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean handoverBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public void setBooks(Book... books) {
        this.books = new ArrayList<>();
        for (Book book : books) {
            this.books.add(book);
        }
    }

    public boolean isLate(Book book, Day handoverDay, Month handoverMonth) {
        if (Day.isGreaterThan(book.getDay(), handoverDay) || Month.isGreaterThan(book.getMonth(), handoverMonth)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "BookReader [name=" + name + ", books=" + books + "]";
    }
}
