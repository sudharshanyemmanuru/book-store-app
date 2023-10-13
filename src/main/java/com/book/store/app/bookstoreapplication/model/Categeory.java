package com.book.store.app.bookstoreapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categeory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categeory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categeory_id")
    private int categeoryId;
    @Column(name = "categeory_name")
    private String categeoryName;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "categeory")
    private List<Book> books;
}
