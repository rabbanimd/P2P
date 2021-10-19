package com.cortes.p2p.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "project")
@Getter
@Setter
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private Long id;
    private String title;

    @Column(length = 3000)
    private String description;

    @Column(length = 700)
    private String imageLink;

    @Column(length = 700)
    private String projectLink;

    @Column(columnDefinition = "int default 1")
    private int hits;
    @Column(columnDefinition = "int default 0")
    private int request;
    @Column(columnDefinition = "int default 0")
    private int upvote;

    private String deadLine;

//    Timeline
    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User author;

    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Tag> tags = new HashSet<>();

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_request", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {@JoinColumn(name = "project_id") })
    private Set<User> requestedUsers = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "approvedProjects")
    private Set<User> approvedUsers = new HashSet<>();

    @PrePersist
    void onCreate()
    {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate()
    {
        this.modified = LocalDateTime.now();
    }


}
