package com.book.store.app.bookstoreapplication.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "new_user_profile_picture")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePicture {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(name = "user_profile_pic_id")
    private String profilePicId;
    @Column(name = "profile_image_data")
    private byte[] img;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",referencedColumnName = "new_user_id")
    private NewUser newUser;
}
