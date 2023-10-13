package com.book.store.app.bookstoreapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",referencedColumnName = "new_user_id")
    private NewUser user;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "cartList")
    private List<Book> addedBooks=new ArrayList<>();
}
