import java.time.LocalDate;

public class Loan {
    private Book book;
    private String borrowerName;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public Loan(Book book, String borrowerName) {
        this.book = book;
        this.borrowerName = borrowerName;
        this.loanDate = LocalDate.now();
        this.returnDate = null;
    }

    public Book getBook() {
        return book;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
