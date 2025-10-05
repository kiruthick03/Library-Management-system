package com.example.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Table(name = "users")
public class User {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String name;

   
    private String email;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
