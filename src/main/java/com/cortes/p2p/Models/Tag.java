package com.cortes.p2p.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tag")
@Getter
@Setter
@ToString
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Long id;

    private String name;
    @Column(columnDefinition = "int default 0")
    private int hits;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "tags_projects", joinColumns = { @JoinColumn(name = "tag_id") }, inverseJoinColumns = {@JoinColumn(name = "project_id") })
    private Set<Project> projects = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "user_tags", joinColumns = { @JoinColumn(name = "tag_id") }, inverseJoinColumns = {@JoinColumn(name = "user_id") })
    private Set<User> users = new HashSet<>();


    public void incrementHits()
    {
        this.hits += 1;
    }
}

