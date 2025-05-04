package service;

import model.*;

import java.time.LocalDate;
import java.util.*;


public class Library {
    private Map<Integer, User> users;
    private Map<Integer, Book> books;
    private Map<Integer, IssuedBook> borrowedBooks;


    public Library(){
        books = new HashMap<>();
        users = new HashMap<>();
        borrowedBooks = new HashMap<>();
    }

    public void registerUser(User user){ users.put(user.getId(), user); }

    public User getUser(int id){ return users.getOrDefault(id, null); }

    public void addBook(User user, Book book){
        if(user.getRole() == Role.ADMIN){
            books.put(book.getId(), book);
        }else{
            System.out.println("permission Denied, Only Admin can add books");
        }
    }
    
    public void removeBook(User user, Book book){ 
        if(user.getRole() == Role.ADMIN){
            books.remove(book.getId());
        }else{  
            System.out.println("permission Denied, Only Admin can remove books");
        }
    }
    
    public Book getBook(int id){ return books.getOrDefault(id, null); }

    public void viewAllBooks(){
        books.values().forEach(System.out :: println);
    }
    
    public void viewAvailableBooks(){
        books.values().stream().filter(book -> book.getCurrentQuantity() > 0).forEach(System.out :: println);
    }

    public Book searchBook(String match){
        return books.values().stream().filter(book -> book.getTitle().equalsIgnoreCase(match)).findFirst().orElse(null);
    }


    public Boolean validateUser(int userId){
        return getUser(userId) != null;
    }
    
    public Boolean validateBook(int bookId){
        return getBook(bookId) != null;
    }

    public void borrowBook(User user, int bookId){
        if(!validateBook(bookId)){
            System.out.println("Invalid book");
            return;
        }
        Book book = getBook(bookId);
        if(book.getCurrentQuantity() <= 0){
            System.out.println("Currently Book id not available, Please try later");
            return;
        }
        IssuedBook issue = new IssuedBook(book, user, LocalDate.now());
        int issueId = issue.getId();
        book.decreaseCurrentQuantityByOne();
        borrowedBooks.put(issueId, issue);
        System.out.println("Book Issued Successfully with id as: " + issueId);
    }

    public void returnBook(int issueId){
        IssuedBook issue = borrowedBooks.getOrDefault(issueId, null);
        if (issue == null){
            System.out.println("Inavlid Issue id");
            return;
        }
        issue.setReturnDate(LocalDate.now());
        issue.getBook().increaseCurrentQuantityByOne();
    }

}