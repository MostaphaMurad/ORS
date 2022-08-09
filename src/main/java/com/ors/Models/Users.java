package com.ors.Models;
import com.sun.istack.NotNull;

import javax.persistence.*;

@MappedSuperclass
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;
    @Column(unique = true,length = 45,nullable = false)
    private String email;
    @Column(unique = false,length = 100,nullable = false)
    private String password;

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
