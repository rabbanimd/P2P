package com.cortes.p2p.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

/**
 * class UserCredentials :
 * All user data will be stored here for backend use.
 *   =>  for personalisation filter
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(indexes = {@Index(name = "authToken", columnList = "auth_token")})
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    @Column(name = "auth_token", unique = true, nullable = false)
    private String authToken;
    private Instant expireAt;
    @OneToOne(mappedBy = "userCredentials")
    private User user;
}
