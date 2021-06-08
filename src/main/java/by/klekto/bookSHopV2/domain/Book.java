package by.klekto.bookSHopV2.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, author, category;
    private int year, pages;
    private float cost;

    @OneToOne(mappedBy = "book") //Одна вещь может находиться в одном BookInOrder
    BookInOrder bookInOrders;

    public Book() {

    }

    public Book(String name, String author, String category, int year, int pages, float cost) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.year = year;
        this.pages = pages;
        this.cost = cost;

    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

}
