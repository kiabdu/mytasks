package com.kiabdu.mytasks.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "email", length = 100)
    private String email;

    /*
    20 byte-werte für salt + ca. 20 * 3 byte-werte bei erlaubter
    passwortlänge von 20 zeichen + 20 byte-werte overhead falls
    ich bei der berechnung was vergessen haben sollte = länge von 100 zeichen
    */
    @Column(name = "hash", length = 100)
    private String hash;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "tasks", joinColumns = @JoinColumn(name = "userId"))
    private List<Task> tasks = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String user_email) {
        this.email = user_email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
