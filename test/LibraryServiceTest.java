import model.Admin;
import model.Book;
import model.Member;
import service.Library;

import java.util.List;

public class LibraryServiceTest {
    private static int testsRun = 0;
    private static int nextIssueId = 1;

    public static void main(String[] args) {
        testBookRegistration();
        testQuantityValidation();
        testBorrowAndReturn();
        testBorrowedBookCannotBeRemoved();
        testFlexibleBookSearch();

        System.out.println("All " + testsRun + " library service tests passed.");
    }

    private static void testBookRegistration() {
        Library library = new Library();
        Admin admin = new Admin("Admin", "admin@test.com", "password");
        Book book = new Book("Clean Code", "Robert C. Martin", "Prentice Hall", 3);

        library.addBook(admin, book);

        assertSame(book, library.getBook(book.getId()), "Added book should be retrievable");
        assertEquals(3, book.getTotalQuantity(), "Total quantity should be stored");
        assertEquals(3, book.getCurrentQuantity(), "All copies should initially be available");
    }

    private static void testQuantityValidation() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Book("Invalid", "Author", "Publisher", 0),
                "A book must have at least one copy"
        );

        Book book = new Book("Refactoring", "Martin Fowler", "Addison-Wesley", 1);
        assertThrows(
                IllegalArgumentException.class,
                () -> book.increaseQuantity(-1),
                "Inventory cannot be increased by a negative amount"
        );
    }

    private static void testBorrowAndReturn() {
        Library library = new Library();
        Admin admin = new Admin("Admin", "admin@test.com", "password");
        Member member = new Member("Member", "member@test.com", "password");
        Book book = new Book("Effective Java", "Joshua Bloch", "Addison-Wesley", 1);
        library.addBook(admin, book);

        library.borrowBook(member, book.getId());
        int issueId = nextIssueId++;
        assertEquals(0, book.getCurrentQuantity(), "Borrowing should reduce available copies");

        library.returnBook(issueId);
        assertEquals(1, book.getCurrentQuantity(), "Returning should restore an available copy");

        library.returnBook(issueId);
        assertEquals(1, book.getCurrentQuantity(), "A duplicate return must not change inventory");
    }

    private static void testBorrowedBookCannotBeRemoved() {
        Library library = new Library();
        Admin admin = new Admin("Admin", "admin@test.com", "password");
        Member member = new Member("Member", "member@test.com", "password");
        Book book = new Book("Domain-Driven Design", "Eric Evans", "Addison-Wesley", 1);
        library.addBook(admin, book);

        library.borrowBook(member, book.getId());
        int issueId = nextIssueId++;
        assertFalse(
                library.removeBook(admin, book),
                "A book with an active issue must not be removed"
        );

        library.returnBook(issueId);
        assertTrue(
                library.removeBook(admin, book),
                "A returned book should be removable"
        );
        assertEquals(null, library.getBook(book.getId()), "Removed book should not be retrievable");
    }

    private static void testFlexibleBookSearch() {
        Library library = new Library();
        Admin admin = new Admin("Admin", "admin@test.com", "password");
        Book cleanArchitecture = new Book(
                "Clean Architecture",
                "Robert C. Martin",
                "Prentice Hall",
                1
        );
        Book cleanCode = new Book("Clean Code", "Robert C. Martin", "Prentice Hall", 1);
        Book effectiveJava = new Book("Effective Java", "Joshua Bloch", "Addison-Wesley", 1);
        library.addBook(admin, effectiveJava);
        library.addBook(admin, cleanCode);
        library.addBook(admin, cleanArchitecture);

        List<Book> partialMatches = library.searchBooks("  cLeAn  ");
        assertEquals(2, partialMatches.size(), "Partial search should return every title match");
        assertSame(
                cleanArchitecture,
                partialMatches.get(0),
                "Search results should be sorted by title"
        );
        assertSame(cleanCode, partialMatches.get(1), "All matching titles should be returned");

        List<Book> exactMatches = library.searchBooks("effective java");
        assertEquals(1, exactMatches.size(), "Exact titles should still match");
        assertSame(effectiveJava, exactMatches.get(0), "Search should ignore letter case");

        assertTrue(library.searchBooks("missing").isEmpty(), "Unknown titles should return no results");
        assertTrue(library.searchBooks("   ").isEmpty(), "Blank searches should return no results");
    }

    private static void assertEquals(Object expected, Object actual, String message) {
        testsRun++;
        if (expected == null ? actual != null : !expected.equals(actual)) {
            throw new AssertionError(message + ": expected " + expected + ", got " + actual);
        }
    }

    private static void assertSame(Object expected, Object actual, String message) {
        testsRun++;
        if (expected != actual) {
            throw new AssertionError(message);
        }
    }

    private static void assertTrue(boolean condition, String message) {
        testsRun++;
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    private static void assertFalse(boolean condition, String message) {
        assertTrue(!condition, message);
    }

    private static void assertThrows(
            Class<? extends Throwable> expectedType,
            Runnable action,
            String message
    ) {
        testsRun++;
        try {
            action.run();
        } catch (Throwable error) {
            if (expectedType.isInstance(error)) {
                return;
            }
            throw new AssertionError(
                    message + ": expected " + expectedType.getSimpleName()
                            + ", got " + error.getClass().getSimpleName()
            );
        }
        throw new AssertionError(message + ": expected " + expectedType.getSimpleName());
    }
}
