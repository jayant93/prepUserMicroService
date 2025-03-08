package com.interview.prep.entities;


import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Entity
@Table(name="`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> address;

    private String phoneNumber;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId(){
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
