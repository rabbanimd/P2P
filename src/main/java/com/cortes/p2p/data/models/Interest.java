package com.cortes.p2p.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interest")
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(columnDefinition = "BIGINT default 0")
    private Long totalUsers;
    @Column(columnDefinition = "BIGINT default 0")
    private Long totalPosts;
    @Column(columnDefinition = "BIGINT default 0")
    private Long upvotes;
    @Column(columnDefinition = "BIGINT default 0")
    private Long downvotes;

    @Column(columnDefinition = "BIGINT default 0")
    private Long reports;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "interests")
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    public Interest(String name) {
        this.name = name;
        this.totalPosts = 0L;
        this.totalUsers = 0L;
    }

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }, mappedBy = "interests")
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    public void incrementTotalPosts() {
        this.totalPosts++;
    }
    public void incrementTotalUsers() {
        this.totalUsers++;
    }
    public void incrementUpvotes() {
        this.upvotes++;
    }
    public void incrementDownvotes() {
        this.downvotes++;
    }
    public void incrementReports() {
        this.reports++;
    }
}
