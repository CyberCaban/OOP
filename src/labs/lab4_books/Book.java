package labs.lab4_books;

public class Book {
    private String title;
    private String author;
    private int day;
    private int month;

    public Book() {
        this.title = "";
        this.author = "";
        this.day = 0;
        this.month = 0;
    }

    /**
     * Constructor with parameters
     * if day/month is invalid, set to 0
     * 
     * @param title
     * @param author
     * @param day
     * @param month
     */
    public Book(String title, String author, int day, int month) {
        if (day < 0 || day > 31 || month < 0 || month > 12) {
            this.title = "";
            this.author = "";
            this.day = 0;
            this.month = 0;
            return;
        }
        this.title = title;
        this.author = author;
        this.day = day;
        this.month = month;
    }

    public Book(Book book) {
        if (book == null) {
            this.title = "";
            this.author = "";
            this.day = 0;
            this.month = 0;
            return;
        }
        this.title = book.title;
        this.author = book.author;
        this.day = book.day;
        this.month = book.month;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day < 0 || day > 31) {
            this.day = 1;
            return;
        } else if (month == 2 && day > 28) {
            this.day = 1;
            return;
        } else if (month == 4 || month == 6 || month == 9 || month == 11 && day > 30) {
            this.day = 1;
            return;
        }
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 0 || month > 12) {
            this.month = 1;
            return;
        }
        this.month = month;
    }

    @Override
    public String toString() {
        return "Book [title=" + title + ", author=" + author + ", day=" + day + ", month=" + month
                + "]";
    }
}