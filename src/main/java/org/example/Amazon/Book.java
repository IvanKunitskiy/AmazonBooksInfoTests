package org.example.Amazon;

public class Book {
    private final String title;
    private final String author;
    private double price;
    private final boolean isBestSeller;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public boolean getBestSeller() {
        return isBestSeller;
    }

    public Book(String title, String author, double price, boolean isBestSeller) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isBestSeller = isBestSeller;
    }

    public Book(String title, String author, boolean isBestSeller) {
        this.title = title;
        this.author = author;
        this.isBestSeller = isBestSeller;
    }

    @Override
    public String toString() {
        return title + " " + author + " " + price + " " + isBestSeller;
    }
}
