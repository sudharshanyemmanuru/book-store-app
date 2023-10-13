package com.book.store.app.bookstoreapplication.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "address")
@Getter@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "locality")
    private String locality;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "total_amount")
    private int totalAmount;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST,mappedBy = "address")
    private List<Orders> orders;
    @Override
    public String toString(){
        return "House Number : "+this.houseNumber+"\nStreet Name : "+this.streetName+"\nLocality : "+this.locality+"\n";
    }


}
