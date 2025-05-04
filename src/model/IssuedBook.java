package model;

import java.time.LocalDate;

public class IssuedBook {
    private int issueId;
    private Book book;
    private User user;
    private LocalDate issueDate;
    private LocalDate returnDate;
    private static int generate_id = 0;

    public IssuedBook(Book book, User user, LocalDate issueDate){
        generate_id++;
        this.issueId = generate_id;
        this.book = book;
        this.user = user;
        this.issueDate = issueDate;
        this.returnDate = null;
    }

    public int getId() { return issueId; }
    public Book getBook() { return book; }
    public User getUser() { return user; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return String.format("Book ID: %d | User ID: %d | Issued: %s | Returned: %s",
                book.getId(), user.getId(), issueDate, (returnDate == null ? "Not returned" : returnDate));
    }
}