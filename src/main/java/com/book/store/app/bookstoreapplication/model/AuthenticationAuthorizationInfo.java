package com.book.store.app.bookstoreapplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_authentication_authorization_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationAuthorizationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private int authId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",referencedColumnName = "new_user_id")
    private NewUser newUser;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name="role_id",referencedColumnName = "role_id")
    private Roles roles;
}
