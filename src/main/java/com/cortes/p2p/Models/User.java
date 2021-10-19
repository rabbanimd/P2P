package com.cortes.p2p.Models;

import com.cortes.p2p.utils.Name;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
public class User {

    @Id
    @Column(name = "uid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Name name;
    private String username;
    private String hash;
    private String roll;
    private String studentId;
    private String bio;
    @Column(length = 500)
    private String profilePic;
    @Column(length = 1000)
    private String link;

//    Timeline
    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;

    @OneToMany(mappedBy = "author")
    private Set<Project> myProjects = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(mappedBy = "requestedUsers")
    private Set<Project> requestedProjects = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "approved_request", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {@JoinColumn(name = "project_id") })
    private Set<Project> approvedProjects = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<Tag> tags = new HashSet<>();


    @PrePersist
    void onCreate()
    {
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate()
    {
        this.modified = LocalDateTime.now();
    }
}
