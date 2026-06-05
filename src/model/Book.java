package model;

public class Book {
    private static int generate_id = 0;
    private int id;
    private String title;
    private String author;
    private String publisher;
    private int totalQuantity;
    private int currentQuantity;

    public Book(String title, String author, String publisher, int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        generate_id++;
        this.id = generate_id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.totalQuantity = quantity;
        this.currentQuantity = quantity;
    }

    public int getId(){ return id; }
    public int getTotalQuantity(){ return totalQuantity; }
    public int getCurrentQuantity(){ return currentQuantity; }
    public String getTitle(){ return title; }
    public String getAuthor(){ return author; }
    public String getPublisher(){ return publisher; }

    public void increaseQuantity(int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.totalQuantity += quantity;
        this.currentQuantity += quantity;
    }

    public void decreaseCurrentQuantityByOne(){
        if(currentQuantity <= 0){
            throw new IllegalStateException("No available copies to issue.");
        }
        currentQuantity--;
    }

    public void increaseCurrentQuantityByOne(){
        if(currentQuantity >= totalQuantity){
            throw new IllegalStateException("Available copies cannot exceed total copies.");
        }
        currentQuantity++;
    }

    @Override
    public String toString() {
        return String.format(
            "ID: %d | %s by %s | Publisher: %s | Total: %d | Available: %d",
            id, title, author, publisher, totalQuantity, currentQuantity
        );
    }

}
