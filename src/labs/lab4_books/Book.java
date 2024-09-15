package labs.lab4_books;

public class Book {
    private String title;
    private String author;
    private Day day;
    private Month month;

    public Book() {
        this.title = "";
        this.author = "";
        this.day = Day.ONE;
        this.month = Month.JANUARY;
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
    public Book(String title, String author, Day day, Month month) {
        this.title = title;
        this.author = author;
        this.day = day;
        this.month = month;
    }

    public Book(Book book) {
        if (book == null) {
            this.title = "";
            this.author = "";
            this.day = Day.ONE;
            this.month = Month.JANUARY;
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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        if (day == null) {
            this.day = Day.ONE;
            return;
        } else if (Month.isEqual(this.month, Month.FEBRUARY) && day.getValue() > 28) {
            this.day = Day.ONE;
            return;
        } else if ((Month.isEqual(this.month, Month.APRIL) || Month.isEqual(this.month, Month.JUNE)
                || Month.isEqual(this.month, Month.SEPTEMBER) || Month.isEqual(this.month, Month.NOVEMBER))
                && Day.isGreaterThan(day, Day.TWENTY_EIGHT)) {
            this.day = Day.ONE;
            return;
        }
        this.day = day;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        if (month == null) {
            this.month = Month.JANUARY;
            return;
        } else if (Month.isEqual(this.month, Month.FEBRUARY) && day.getValue() > 28) {
            this.month = Month.JANUARY;
            return;
        } else if ((Month.isEqual(this.month, Month.APRIL) || Month.isEqual(this.month, Month.JUNE)
                || Month.isEqual(this.month, Month.SEPTEMBER) || Month.isEqual(this.month, Month.NOVEMBER))
                && Day.isGreaterThan(day, Day.TWENTY_NINE)) {
            this.month = Month.JANUARY;
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
