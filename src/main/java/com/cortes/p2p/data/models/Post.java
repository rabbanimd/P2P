package com.cortes.p2p.data.models;

import com.cortes.p2p.data.common.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(columnDefinition="LONGTEXT")
    private String description;

    private String imageLink;

    @Enumerated(EnumType.ORDINAL)
    private PostStatus postStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User author = new User();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(name = "posts_interests",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "interest_id")}
    )
    private Set<Interest> interests = new HashSet<>();

}
