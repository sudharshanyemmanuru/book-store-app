package com.book.store.app.bookstoreapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "new_user")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "new_user_id")
    private int userId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "is_verified")
    private String isVerified;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "user")
    private List<Orders> orders;
    @OneToOne(fetch = FetchType.EAGER,mappedBy = "user")
    private Cart cart;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "user")
    private List<Review> postedReviews;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "newUser")
    private ProfilePicture profilePicture;
}
