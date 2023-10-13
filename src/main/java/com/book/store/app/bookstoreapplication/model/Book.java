package com.book.store.app.bookstoreapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "book_summary")
    private String bookSummary;
    @Column(name = "issued_at")
    private String issudeAt;
    @Column(name = "book_cover_image_data")
    private byte[] bookCoverImage;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "price")
    private double price;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categeory_id",referencedColumnName = "categeory_id")
    private Categeory categeory;
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id",referencedColumnName = "author_id")
    private Author author;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(name = "cart_book",
    joinColumns = {
            @JoinColumn(name = "book_id",referencedColumnName = "book_id")},
    inverseJoinColumns = {
            @JoinColumn(name = "cart_id",referencedColumnName = "cart_id")}
    )
    private List<Cart> cartList;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "book")
    private List<Review> reviews;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "orderedBooks")
    private List<Orders> ordersList=new ArrayList<>();
    public boolean contains(String param){
        if(this.bookName.contains(param)||this.bookName.equalsIgnoreCase(param))
            return true;
        else if(this.categeory.getCategeoryName().contains(param)||this.categeory.getCategeoryName().equalsIgnoreCase(param))
            return true;
        else if(this.author.getAuthorName().contains(param)||this.author.getAuthorName().equalsIgnoreCase(param))
            return true;
        return false;
    }
}
