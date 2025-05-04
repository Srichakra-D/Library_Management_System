import service.*;
import model.*;
import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library();
        User superAdmin = new Admin("DefaultAdmin", "default123@gmail.com", "demo123");
        Scanner scanner = new Scanner(System.in);

        library.registerUser(superAdmin);
        boolean exit = false;

        while(!exit){
            System.out.println("===== Welcome to the Library Management System =====");
            System.out.println("1. Admin Menu");
            System.out.println("2. User Menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int mainChoice;
            try{
                mainChoice = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            switch(mainChoice) {
                case 1:
                    Admin admin = authenticateAdmin(library, scanner);
                    if(admin != null) adminMenu(admin, library, scanner);
                    break;
                case 2:
                    Member member = authenticateMember(library, scanner);
                    if(member != null)memberMenu(member, library, scanner);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Enter correct choice");
                    break;
            }
        }
        scanner.close();
    }

    public static Admin authenticateAdmin(Library library, Scanner scanner){
        int aId;
        System.out.print("Enter Admin Id: ");
        try {
            aId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Returning to main menu.");
            return null;
        }
        
        User user = library.getUser(aId);
        if(!(user instanceof Admin)){
            System.out.println("No admin found with this ID.");
            System.out.println("Returning to main menu.");
            return null;
        }

        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        if(!user.checkPassword(pass)){
            System.out.println("Incorrect Password");
            System.out.println("Returning to main menu");
            return null;
        }

        return (Admin) user;
    }
    
    public static Member authenticateMember(Library library, Scanner scanner){
        int mId;
        System.out.print("Enter Member Id: ");
        try {
            mId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Returning to main menu.");
            return null;
        }
        
        User user = library.getUser(mId);
        if(!(user instanceof Member)){
            System.out.println("No member found with this ID.");
            System.out.println("Returning to main menu.");
            return null;
        }

        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();

        if(!user.checkPassword(pass)){
            System.out.println("Incorrect Password");
            System.out.println("Returning to main menu");
            return null;
        }
        return (Member) user;
    }

    public static void adminMenu(Admin admin, Library library, Scanner scanner){
        boolean exit = false;
        
        while(!exit){
            System.out.println("--- Welcome to Admin Menu(Id: " + admin.getId() + ") ---");
            System.out.println("1: Add Member");
            System.out.println("2: Add Book");
            System.out.println("3: Remove Book");
            System.out.println("4: View all Books");
            System.out.println("5: View Available Books");
            System.out.println("6: Search Book");
            System.out.println("7: Accept Return From Member");
            System.out.println("8: Logout");
            System.out.print("Enter your choice: ");

            int choice;
            try{
                choice = Integer.parseInt(scanner.nextLine());
            } catch(NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String mName = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String mEmail = scanner.nextLine();
                    System.out.print("Choose Password: ");
                    String mPass = scanner.nextLine();
                    User newUser = new Member(mName, mEmail, mPass);
                    library.registerUser(newUser);
                    System.out.println("Member registered successfully with ID: " + newUser.getId());
                    break;
                case 2:
                    System.out.print("Enter Title: ");
                    String bTitle = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String bAuthor = scanner.nextLine();
                    System.out.print("Enter Publisher: ");
                    String bPublisher = scanner.nextLine();
                    System.out.print("Enter Quantity: ");

                    int bQuantity;
                    try {
                        bQuantity = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity. Operation aborted.");
                        break;
                    }

                    Book newBook = new Book(bTitle, bAuthor, bPublisher, bQuantity);
                    library.addBook(admin, newBook);
                    break;
                case 3:
                    System.out.print("Enter book ID to remove: ");
                    int bId;
                    try {
                        bId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid ID. Operation aborted.");
                        break;
                    }
                    Book cur = library.getBook(bId);
                    if(cur != null){
                        library.removeBook(admin, cur);
                        System.out.println("Book removed Succesfully");
                    }else{
                        System.out.println("Book Not Found");
                    }
                    break;
                case 4:
                    library.viewAllBooks();
                    break;
                case 5:
                    library.viewAvailableBooks();
                    break;
                case 6:
                    System.out.print("Enter Book Title to search: ");
                    String bookTitle = scanner.nextLine();

                    Book found = library.searchBook(bookTitle);
                    if(found != null){
                        System.out.println(found);
                    }else{
                        System.out.println("Book Not Found");
                    }
                    break;
                case 7:
                    System.out.print("Enter Issue Id: ");
                    int issueId;
                    try {
                        issueId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity. Operation aborted.");
                        break;
                    }
                    library.returnBook(issueId);
                    break;
                case 8:
                    System.out.println("Logging out..");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

        
    public static void memberMenu(Member member, Library library, Scanner scanner){
        boolean exit = false;
        while (!exit) {
            System.out.println("--- Welcome to Member Menu(Id: " + member.getId() + ") ---");
            System.out.println("1: View Available Books");
            System.out.println("2: Search Book");
            System.out.println("3: Borrow Book");
            System.out.println("4: Logout");
            System.err.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Operation aborted.");
                break;
            }

            switch (choice) {
                case 1:
                    library.viewAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter Book Title to search: ");
                    String bookTitle = scanner.nextLine();

                    Book found = library.searchBook(bookTitle);
                    if(found != null){
                        System.out.println(found);
                    }else{
                        System.out.println("Book Not Found");
                    }
                    break;
                case 3:
                    int bId;
                    System.out.print("Enter Book id to Borrow: ");
                    try {
                        bId= Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity. Operation aborted.");
                        break;
                    }
                    library.borrowBook(member, bId);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
}